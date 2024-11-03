package com.kit.video.service.open.query;

import cn.hutool.core.collection.CollUtil;
import com.kit.video.domain.UserVideoBlockInfo;
import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.domain.UserVideoMetadataInfo;
import com.kit.video.domain.bo.VideoQueryBaseBo;
import com.kit.video.domain.vo.open.QueryVideoInfoVo;
import com.kit.video.domain.vo.open.VideoUserInfoVo;
import com.kit.video.domain.vo.open.VideoWithTagInfoVo;
import com.kit.video.mapper.UserVideoBlockInfoMapper;
import com.kit.video.mapper.UserVideoDataInfoMapper;
import com.kit.video.mapper.UserVideoMetadataInfoMapper;
import com.kit.video.mapper.UserVideoTagMapper;
import com.kit.video.service.manager.IUserVideoBrowseInfoService;
import com.kit.video.service.open.OpenUserVideoDataInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 推荐方式获取视频列表
 *
 * */
@Service
@Slf4j
public class QueryByRecommended implements VideoQueryStage {
    /**
     * 推荐的方式：
     * 1. 按视频积分
     * 2.积分规则
     *        - 被分享一次加5积分
     *        - 被点赞一次加10积分
     *        - 被收藏一次加10积分
     *        - 被举报一次（扣5积分，举报通过后再扣除全部积分并且永远不能加分，解除举报后重新计算积分（不继承过去的积分））
     *        - @todo (付费视频加积分，待开发，付费购买的积分被别人浏览一次，减少1积分)
     *        - 新发布的视频默认100积分，每过一天扣1积分，扣到0为止
     *        - 完善个人信息后，每完善一项信息，发布视频额外加10积分（用户名称，性别，头像，绑定手机号或者邮箱等）
     *        - 经常登陆的用户发布视频时，每个视频额外加5积分，连续登陆越多额外加得越多，每过10天多加1分
     *        - 浏览破千加5积分，破万加10积分，破10万加100积分，破100万加1000积分，每个视频只能加一次
     *
     * */

    @Autowired
    private IUserVideoBrowseInfoService videoBrowseInfoService;

    @Autowired
    UserVideoDataInfoMapper userVideoDataInfoMapper;

    @Autowired
    private OpenUserVideoDataInfoService openUserVideoDataInfoService;

    @Autowired
    private UserVideoMetadataInfoMapper userVideoMetadataInfoMapper;

    @Autowired
    private UserVideoBlockInfoMapper userVideoBlockInfoMapper;

    @Autowired
    private UserVideoTagMapper userVideoTagMapper;

    @Override
    public List<QueryVideoInfoVo> query(VideoQueryBaseBo queryBo) {
        final Integer queryCount = Optional.ofNullable(queryBo.getCount()).orElse(10);
        final List<String> videoIds = videoBrowseInfoService.queryVideoIdsByRecommended(queryBo.getFilterVideoIds(), queryCount);
        if (videoIds.isEmpty()) {
            return new ArrayList<>();
        }
        final List<UserVideoDataInfo> userVideoDataInfos = openUserVideoDataInfoService.selectUserVideoDataInfoById(videoIds);
        if (userVideoDataInfos.isEmpty()) {
            return new ArrayList<>();
        }

        final List<UserVideoMetadataInfo> userVideoMetadataInfos = userVideoMetadataInfoMapper.selectUserVideoMetadataInfoByIds(videoIds);
        if (userVideoMetadataInfos.isEmpty()) {
            return new ArrayList<>();
        }
        final Map<String, UserVideoMetadataInfo> videoMetadataInfoMap = userVideoMetadataInfos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(UserVideoMetadataInfo::getId, m -> m, (m1, m2) -> m1));

        final List<UserVideoBlockInfo> userVideoBlockInfo = userVideoBlockInfoMapper.selectVideoBlockByVideoIds(videoIds);
        if (userVideoBlockInfo.isEmpty()) {
            return new ArrayList<>();
        }
        final Map<String, List<UserVideoBlockInfo>> videoBlockMap = userVideoBlockInfo.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(UserVideoBlockInfo::getVideoId));

        final List<VideoWithTagInfoVo> videoWithTagInfoVos = userVideoTagMapper.selectVideoTagsByVideoIds(videoIds);

        final Map<String, List<VideoWithTagInfoVo>> videoTagsMap = videoWithTagInfoVos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(VideoWithTagInfoVo::getVideoId));

        final List<String> videoAuthIds = userVideoMetadataInfos.stream()
                .filter(Objects::nonNull)
                .map(UserVideoMetadataInfo::getUserId)
                .collect(Collectors.toList());
        final List<VideoUserInfoVo> authInfos = userVideoDataInfoMapper.getUserInfoByUserIds(videoAuthIds);
        final Map<String, VideoUserInfoVo> authInfosMap = authInfos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(VideoUserInfoVo::getUserId, info -> info, (u1, u2) -> u1));
        return userVideoDataInfos.stream()
                .filter(Objects::nonNull)
                .map(info -> {
                    final String videoId = info.getVideoId();
                    final UserVideoMetadataInfo metadataInfo = videoMetadataInfoMap.get(videoId);
                    if (null == metadataInfo) return null;
                    final String videoAuthId = info.getBelong();
                    final VideoUserInfoVo videoUserInfoVo = authInfosMap.get(videoAuthId);
                    if (null == videoUserInfoVo) return null;
                    final QueryVideoInfoVo infoVo = new QueryVideoInfoVo();
                    final List<UserVideoBlockInfo> blockList = videoBlockMap.get(videoId);
                    if (CollUtil.isEmpty(blockList)) return null;
                    final List<QueryVideoInfoVo.VideoSrcInfoVo> blocks = blockList.stream()
                            .sorted(Comparator.comparing(UserVideoBlockInfo::getBlockIndex))
                            .map(block -> {
                                QueryVideoInfoVo.VideoSrcInfoVo blockInfo = new QueryVideoInfoVo.VideoSrcInfoVo();
                                blockInfo.setSrc(block.getSrc());
                                blockInfo.setId(block.getId());
                                blockInfo.setType(block.getFileType());
                                blockInfo.setIndex(block.getBlockIndex());
                                return blockInfo;
                            }).collect(Collectors.toList());
                    final List<VideoWithTagInfoVo> tagInfoVos = videoTagsMap.get(videoId);

                    //视频封面地址
                    infoVo.setPageSrc(metadataInfo.getSrc());
                    infoVo.setNeedShowVideoMenu(true);
                    infoVo.setSrcMatch("");
                    infoVo.setCurrentVideoIndex(videoId);
                    infoVo.setTransitionDuration(3000);
                    infoVo.setFadeOpacity(1);
                    infoVo.setDuration(1000);
                    infoVo.setCreateTime(info.getCreateTime());
                    infoVo.setPublishTime(info.getCreateTime());
                    infoVo.setAuthName(videoUserInfoVo.getNickName());
                    infoVo.setUserLink(videoUserInfoVo.getAvatar());
                    infoVo.setUserAvatar(videoUserInfoVo.getAvatar());

                    infoVo.setIpLocal(info.getLocation());
                    infoVo.setTitle(info.getTitle());
                    infoVo.setDescription(info.getDescription());
                    infoVo.setAbout(QueryVideoInfoVo.VideoAboutInfoVo.genericVideoAboutInfo(
                            Optional.ofNullable(info.getLikeTimes()).orElse(0),
                            Optional.ofNullable(info.getDislikeTimes()).orElse(0),
                            Optional.ofNullable(info.getBrowseTimes()).orElse(0),
                            Optional.ofNullable(info.getLikeTimes()).orElse(0),
                            Optional.ofNullable(info.getLikeTimes()).orElse(0),
                            videoUserInfoVo.getAvatar()));
                    infoVo.setVideoSrc(blocks);
                    Optional.ofNullable(tagInfoVos)
                            .ifPresent(tags -> tags.stream()
                                    .filter(Objects::nonNull)
                                    .forEach(tag -> infoVo.addTag(
                                        QueryVideoInfoVo.VideoTagInfoVo.genericTag(
                                            tag.getTagId(),
                                            tag.getTagName(),
                                            Optional.ofNullable(tag.getUseTimes()).orElse(0))
                                    )));
                    return infoVo;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        STAGE.put(Type.RECOMMENDED, this);
    }

    /**
     * org.springframework.jdbc.BadSqlGrammarException: ### Error updating database. Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'is, video_id, examine_message ) VALUES ( '0e811a8a-1d2b-4790-bdc9-a9d1b36dfd8' at line 1 ### The error may exist in com/kit/video/mapper/UserVideoExamineMapper.java (best guess) ### The error may involve com.kit.video.mapper.UserVideoExamineMapper.insert-Inline ### The error occurred while setting parameters ### SQL: INSERT INTO user_video_examine_info ( is, video_id, examine_message ) VALUES ( ?, ?, ? ) ### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'is, video_id, examine_message ) VALUES ( '0e811a8a-1d2b-4790-bdc9-a9d1b36dfd8' at line 1 ; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'is, video_id, examine_message ) VALUES ( '0e811a8a-1d2b-4790-bdc9-a9d1b36dfd8' at line 1
     * */
}
