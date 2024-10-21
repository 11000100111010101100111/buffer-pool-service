package com.kit.video.service.manager;

import java.util.List;

import com.kit.video.domain.UserVideoLikeInfo;

/**
 * 视频的点赞信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface IUserVideoLikeInfoService {
    /**
     * 查询视频的点赞信息
     *
     * @param id 视频的点赞信息主键
     * @return 视频的点赞信息
     */
    public UserVideoLikeInfo selectUserVideoLikeInfoById(String id);

    /**
     * 查询视频的点赞信息列表
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 视频的点赞信息集合
     */
    public List<UserVideoLikeInfo> selectUserVideoLikeInfoList(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 新增视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    public int insertUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 修改视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    public int updateUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 批量删除视频的点赞信息
     *
     * @param ids 需要删除的视频的点赞信息主键集合
     * @return 结果
     */
    public int deleteUserVideoLikeInfoByIds(String[] ids);

    /**
     * 删除视频的点赞信息信息
     *
     * @param id 视频的点赞信息主键
     * @return 结果
     */
    public int deleteUserVideoLikeInfoById(String id);
}
