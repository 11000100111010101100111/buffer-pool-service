package com.kit.video.service.open.publish;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kit.common.config.SystemConfig;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.exception.base.BaseException;
import com.kit.common.utils.StringUtils;
import com.kit.common.utils.file.FileUploadUtils;
import com.kit.common.utils.uuid.UUID;
import com.kit.framework.util.CommonUploadUtil;
import com.kit.video.domain.UserVideoBlockInfo;
import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.domain.UserVideoExamineInfo;
import com.kit.video.domain.UserVideoIntegration;
import com.kit.video.domain.UserVideoIntegrationHistory;
import com.kit.video.domain.UserVideoMetadataInfo;
import com.kit.video.domain.UserVideoTag;
import com.kit.video.domain.UserVideoWithTag;
import com.kit.video.domain.bo.VideoOfLocationBo;
import com.kit.video.domain.bo.VideoPublishBo;
import com.kit.video.domain.bo.VideoTagBo;
import com.kit.video.domain.vo.open.QueryVideoInfoVo;
import com.kit.video.service.open.OpenUserVideoBlockInfoService;
import com.kit.video.service.open.OpenUserVideoDataInfoService;
import com.kit.video.service.open.OpenUserVideoMetadataInfoService;
import com.kit.video.service.open.OpenUserVideoTagService;
import com.kit.video.service.open.OpenUserVideoWithTagService;
import com.kit.video.service.open.UserVideoExamineInfoService;
import com.kit.video.service.open.UserVideoIntegrationService;
import com.kit.video.service.open.UserVideoIntegrationServiceHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VideoPublishService {
    @Autowired
    private CommonUploadUtil commonUploadUtil;
    @Autowired
    private OpenUserVideoMetadataInfoService userVideoMetadataInfoService;
    @Autowired
    private OpenUserVideoDataInfoService videoDataInfoService;
    @Autowired
    private OpenUserVideoTagService tagService;
    @Autowired
    private OpenUserVideoBlockInfoService blockInfoService;
    @Autowired
    private OpenUserVideoWithTagService withTagService;
    @Autowired
    private UserVideoExamineInfoService examineInfoService;
    @Autowired
    private UserVideoIntegrationService integrationService;
    @Autowired
    private UserVideoIntegrationServiceHistory integrationServiceHistory;

    @Value("${video.publish.maxSize:100}")
    private int maxSize;
    @Value("${kit.videoDomain}")
    private String videoDomain;

    private void checkBeforePublish(List<MultipartFile> file, VideoPublishBo publishBo) {
        long size = 0;
        for (MultipartFile multipartFile : file) {
            size += multipartFile.getSize();
            if (size > 100 * 1024 * 1024 * 1024L) {
                throw new BaseException("video", "", null, String.format("太大啦！你的文件让我压力山大⊙﹏⊙∥，文件大小超过了%sMB", maxSize));
            }
        }
        if (Objects.isNull(publishBo)) {
            publishBo = new VideoPublishBo();
        }
        String tags = publishBo.getTags();
        while (tags.contains("#")) {
            tags = tags.replace("#", "");
        }
        publishBo.setTags(tags);
    }

    @Transactional
    public QueryVideoInfoVo publish(List<MultipartFile> file,
                                    VideoPublishBo publishBo,
                                    LoginUser loginUser) throws IOException {
        try {
            checkBeforePublish(file, publishBo);
            QueryVideoInfoVo queryVideoInfoVo = new QueryVideoInfoVo();
            //文件压缩

            //视频元信息
            UserVideoMetadataInfo metadataInfo = new UserVideoMetadataInfo();
            final String metadataId = UUID.randomUUID().toString();
            final String userId = String.valueOf(loginUser.getUserId());
            metadataInfo.setId(metadataId);
            metadataInfo.setSrc("");
            metadataInfo.setUserId(userId);
            userVideoMetadataInfoService.save(metadataInfo);

            //文件保存
            final List<String> videoInfo = publishBo.getVideoInfo();
            boolean hasInfo = true;
            if (null == videoInfo || videoInfo.size() != file.size()) {
                log.warn("没有足够完整的视频资源消息标记列表，视频列表长度：{}", file.size());
                hasInfo = false;
            }
            String filePath = SystemConfig.getVideoPath();
            List<UserVideoBlockInfo> blocks = new ArrayList<>();
            for (int index = 0; index < file.size(); index++) {
                //保存视频
                final String blockId = UUID.randomUUID().toString();
                UserVideoBlockInfo blockInfo = new UserVideoBlockInfo();
                final MultipartFile multipartFile = file.get(index);
                final String contentType = multipartFile.getContentType();
                String fileName = FileUploadUtils.extractFilename(multipartFile);
                AjaxResult upload = commonUploadUtil.uploadVideo(multipartFile, filePath);
                String absolutePath = FileUploadUtils.getAbsoluteFile(filePath, fileName).getAbsolutePath();

                blockInfo.setId(blockId);
                blockInfo.setVideoId(metadataId);
                blockInfo.setSrc(videoDomain + String.valueOf(upload.get("uri")));
                blockInfo.setLocalDir(absolutePath);
                blockInfo.setBlockIndex(index);
                blockInfo.setFileType(contentType);
                blockInfo.setCreateBy(userId);
                blockInfo.setCreateTime(LocalDateTime.now());
                blocks.add(blockInfo);
            }
            blockInfoService.saveBatch(blocks);

            //视频信息
            UserVideoDataInfo videoDataInfo = new UserVideoDataInfo();
            videoDataInfo.setVideoId(metadataId);
            videoDataInfo.setLikeTimes(0);
            videoDataInfo.setDislikeTimes(0);
            videoDataInfo.setBrowseTimes(0);
            videoDataInfo.setCommentTimes(0);
            videoDataInfo.setCollectTimes(0);
            videoDataInfo.setForwardTimes(0);
            videoDataInfo.setReportTimes(0);
            videoDataInfo.setDescription(Optional.ofNullable(publishBo.getDescription()).orElse(""));
            videoDataInfo.setBelong(userId);
            videoDataInfo.setExamineStatus("1");
            videoDataInfo.setExamineMessage("待审核");
            videoDataInfo.setExamineUser("");
            videoDataInfo.setTitle(Optional.ofNullable(publishBo.getTitle()).orElse(""));
            final VideoOfLocationBo location = publishBo.getLocation();
            String locationAddr = !StringUtils.isEmpty(location.getName()) ? location.getName() : "未知城市/地区";
            videoDataInfo.setLocation(locationAddr);
            videoDataInfo.setCreateBy(userId);
            videoDataInfo.setCreateTime(LocalDateTime.now());
            videoDataInfoService.save(videoDataInfo);

            Optional.ofNullable(publishBo.getTags()).ifPresent(boTags -> {
                final List<String> tags = new ArrayList<>(Arrays.asList(boTags.split(",")));
                if (!tags.isEmpty()) {
                    final List<UserVideoTag> tagInfos = tags.stream()
                            .filter(StringUtils::isNotEmpty)
                            .map(name -> {
                                UserVideoTag videoTag = new UserVideoTag();
                                videoTag.setName(name.trim());
                                videoTag.setCreateBy(userId);
                                videoTag.setCreateTime(LocalDateTime.now());
                                return videoTag;
                            }).collect(Collectors.toList());
                    if (tagInfos.isEmpty()) {
                        return;
                    }

                    //标签不存在插入，存在修改将被使用数+1
                    //标签信息管理
                    //标签不存在插入，存在修改将被使用数+1
                    //        INSERT INTO test.user_video_tag (id, name, use_times, createBy, createTime, `delete`, updateTime, updateBy, remark, status) VALUES
                    //                ('1', '', 0, null, '2024-10-27 22:51:02', '0', '2024-10-27 22:51:02', null, '', '1');
                    //        INSERT INTO test.user_video_with_tag (id, video_id, tag_id, createBy, createTime, `delete`, updateTime, updateBy, remark, status) VALUES
                    //                (1, '23570999-0b26-4975-a10a-959d2b347c3c', '', null, '2024-10-27 22:51:27', '0', '2024-10-27 22:51:27', null, '', '1');
                    //查出这次使用的标签ID列表，保存标签使用记录
                    tagService.saveOrUpdate(tagInfos);
                    final List<UserVideoTag> videoTags = tagService.list(Wrappers.lambdaQuery(UserVideoTag.class)
                            .eq(UserVideoTag::getDelete, "0")
                            .eq(UserVideoTag::getStatus, "1")
                            .in(UserVideoTag::getName, tags));
                    final List<UserVideoWithTag> collect = videoTags.stream()
                            .filter(Objects::nonNull)
                            .map(tag -> {
                                UserVideoWithTag userVideoWithTag = new UserVideoWithTag();
                                userVideoWithTag.setVideoId(metadataId);
                                userVideoWithTag.setCreateBy(userId);
                                userVideoWithTag.setTagId(tag.getId());
                                userVideoWithTag.setCreateTime(LocalDateTime.now());
                                return userVideoWithTag;
                            }).collect(Collectors.toList());
                    if (!collect.isEmpty()) {
                        withTagService.saveBatch(collect);
                    }
                }
            });

            //添加到待审核，审核通过后添加到积分表
            //管理员上传不需要审核
            final SysUser user = loginUser.getUser();
            UserVideoIntegration integration = new UserVideoIntegration();
            integration.setIntegration(1000L);
            integration.setVideoId(metadataId);
            integration.setCreateBy(userId);
            integration.setUpdateBy(userId);
            integration.setCreateTime(LocalDateTime.now());
            integration.setUpdateTime(LocalDateTime.now());
            integrationService.saveOrUpdate(integration);
            UserVideoIntegrationHistory history = new UserVideoIntegrationHistory();
            history.setId(UUID.randomUUID().toString());
            history.setVideoId(metadataId);
            if (user.isAdmin()) {
                history.setIntegration(1000L);
                integrationServiceHistory.save(history);
                //@表示管理员上传视频，变更积分的类型，
                // - a：被分享一次加5积分，
                // b：被点赞一次加10积分，
                // c：被收藏一次加10积分，
                // d：被举报一次（扣5积分)，
                // e:举报通过后再扣除全部积分并且永远不能加分，
                // f:解除举报后重新计算积分（不继承过去的积分），
                // g：@todo (付费视频加对应购买的积分，待开发，付费购买的积分被别人浏览一次，减少1积分)，
                //  h：新发布的视频默认100积分，每过一天扣1积分，扣到0为止,
                //  i：完善个人信息后，每完善一项信息，发布视频额外加10积分（用户名称，性别，头像，绑定手机号或者邮箱等）,
                //  j：经常登陆的用户发布视频时，每个视频额外加5积分，连续登陆越多额外加得越多，每过10天多加1分，
                //  k：浏览破千加5积分，
                //  l：破万加10积分，
                //  m：破10万加100积分，
                //  n：破100万加1000积分
                history.setType("@");
                return queryVideoInfoVo;
            }
            history.setType("h");
            history.setIntegration(100L);
            integrationServiceHistory.save(history);
            UserVideoExamineInfo examineInfo = new UserVideoExamineInfo();
            examineInfo.setId(UUID.randomUUID().toString());
            examineInfo.setVideoId(metadataId);
            examineInfo.setExamineMessage("publish");
            examineInfoService.save(examineInfo);
            return queryVideoInfoVo;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
