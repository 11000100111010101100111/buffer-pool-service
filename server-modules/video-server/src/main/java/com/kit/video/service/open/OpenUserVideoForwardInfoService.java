package com.kit.video.service.open;

import com.kit.video.domain.UserVideoForwardInfo;

import java.util.List;

/**
 * 视频的转发信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoForwardInfoService {
    /**
     * 查询视频的转发信息
     *
     * @param id 视频的转发信息主键
     * @return 视频的转发信息
     */
    public UserVideoForwardInfo selectUserVideoForwardInfoById(String id);

    /**
     * 查询视频的转发信息列表
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 视频的转发信息集合
     */
    public List<UserVideoForwardInfo> selectUserVideoForwardInfoList(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 新增视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    public int insertUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 修改视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    public int updateUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 批量删除视频的转发信息
     *
     * @param ids 需要删除的视频的转发信息主键集合
     * @return 结果
     */
    public int deleteUserVideoForwardInfoByIds(String[] ids);

    /**
     * 删除视频的转发信息信息
     *
     * @param id 视频的转发信息主键
     * @return 结果
     */
    public int deleteUserVideoForwardInfoById(String id);
}
