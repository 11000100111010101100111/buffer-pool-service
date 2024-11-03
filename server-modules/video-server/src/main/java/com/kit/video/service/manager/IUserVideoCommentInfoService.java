package com.kit.video.service.manager;

import java.util.List;

import com.kit.video.domain.UserVideoCommentInfo;

/**
 * 视频的评论信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface IUserVideoCommentInfoService {
    /**
     * 查询视频的评论信息
     *
     * @param id 视频的评论信息主键
     * @return 视频的评论信息
     */
    public UserVideoCommentInfo selectUserVideoCommentInfoById(String id);

    /**
     * 查询视频的评论信息列表
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 视频的评论信息集合
     */
    public List<UserVideoCommentInfo> selectUserVideoCommentInfoList(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 新增视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    public int insertUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 修改视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    public int updateUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 批量删除视频的评论信息
     *
     * @param ids 需要删除的视频的评论信息主键集合
     * @return 结果
     */
    public int deleteUserVideoCommentInfoByIds(String[] ids);

    /**
     * 删除视频的评论信息信息
     *
     * @param id 视频的评论信息主键
     * @return 结果
     */
    public int deleteUserVideoCommentInfoById(String id);
}
