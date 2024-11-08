package com.kit.video.service.manager;

import java.util.List;

import com.kit.video.domain.UserVideoBrowseInfo;

/**
 * 视频的浏览信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface IUserVideoBrowseInfoService {
    /**
     * 查询视频的浏览信息
     *
     * @param id 视频的浏览信息主键
     * @return 视频的浏览信息
     */
    public UserVideoBrowseInfo selectUserVideoBrowseInfoById(String id);

    /**
     * 查询视频的浏览信息列表
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 视频的浏览信息集合
     */
    public List<UserVideoBrowseInfo> selectUserVideoBrowseInfoList(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 新增视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    public int insertUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 修改视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    public int updateUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 批量删除视频的浏览信息
     *
     * @param ids 需要删除的视频的浏览信息主键集合
     * @return 结果
     */
    public int deleteUserVideoBrowseInfoByIds(String[] ids);

    /**
     * 删除视频的浏览信息信息
     *
     * @param id 视频的浏览信息主键
     * @return 结果
     */
    public int deleteUserVideoBrowseInfoById(String id);

    /**
     * 查询推荐视频列表，根据视频积分排序，过滤已经看过的视频，未登录不需要过滤
     *
     * @param filterVideoIds 需要过滤的视频ID
     * @param count 查询数目
     * @return 结果 视频ID列表
     */
    public List<String> queryVideoIdsByRecommended(List<String> filterVideoIds, int count);
}
