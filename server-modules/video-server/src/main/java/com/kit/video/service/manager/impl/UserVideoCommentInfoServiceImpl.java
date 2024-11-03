package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoCommentInfoMapper;
import com.kit.video.domain.UserVideoCommentInfo;
import com.kit.video.service.manager.IUserVideoCommentInfoService;

/**
 * 视频的评论信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoCommentInfoServiceImpl implements IUserVideoCommentInfoService {
    @Autowired
    private UserVideoCommentInfoMapper userVideoCommentInfoMapper;

    /**
     * 查询视频的评论信息
     *
     * @param id 视频的评论信息主键
     * @return 视频的评论信息
     */
    @Override
    public UserVideoCommentInfo selectUserVideoCommentInfoById(String id) {
        return userVideoCommentInfoMapper.selectUserVideoCommentInfoById(id);
    }

    /**
     * 查询视频的评论信息列表
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 视频的评论信息
     */
    @Override
    public List<UserVideoCommentInfo> selectUserVideoCommentInfoList(UserVideoCommentInfo userVideoCommentInfo) {
        return userVideoCommentInfoMapper.selectUserVideoCommentInfoList(userVideoCommentInfo);
    }

    /**
     * 新增视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    @Override
    public int insertUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo) {
                userVideoCommentInfo.setCreateTime(LocalDateTime.now());
            return userVideoCommentInfoMapper.insertUserVideoCommentInfo(userVideoCommentInfo);
    }

    /**
     * 修改视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    @Override
    public int updateUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo) {
                userVideoCommentInfo.setUpdateTime(LocalDateTime.now());
        return userVideoCommentInfoMapper.updateUserVideoCommentInfo(userVideoCommentInfo);
    }

    /**
     * 批量删除视频的评论信息
     *
     * @param ids 需要删除的视频的评论信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoCommentInfoByIds(String[] ids) {
        return userVideoCommentInfoMapper.deleteUserVideoCommentInfoByIds(ids);
    }

    /**
     * 删除视频的评论信息信息
     *
     * @param id 视频的评论信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoCommentInfoById(String id) {
        return userVideoCommentInfoMapper.deleteUserVideoCommentInfoById(id);
    }
}
