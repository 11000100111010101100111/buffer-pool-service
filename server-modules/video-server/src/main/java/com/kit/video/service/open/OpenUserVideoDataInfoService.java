package com.kit.video.service.open;

import com.kit.video.domain.UserVideoDataInfo;

import java.util.List;

/**
 * 视频产生的相关数据Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoDataInfoService {
    /**
     * 查询视频产生的相关数据
     *
     * @param id 视频产生的相关数据主键
     * @return 视频产生的相关数据
     */
    public UserVideoDataInfo selectUserVideoDataInfoById(Long id);

    /**
     * 查询视频产生的相关数据列表
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 视频产生的相关数据集合
     */
    public List<UserVideoDataInfo> selectUserVideoDataInfoList(UserVideoDataInfo userVideoDataInfo);

    /**
     * 新增视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    public int insertUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo);

    /**
     * 修改视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    public int updateUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo);

    /**
     * 批量删除视频产生的相关数据
     *
     * @param ids 需要删除的视频产生的相关数据主键集合
     * @return 结果
     */
    public int deleteUserVideoDataInfoByIds(Long[] ids);

    /**
     * 删除视频产生的相关数据信息
     *
     * @param id 视频产生的相关数据主键
     * @return 结果
     */
    public int deleteUserVideoDataInfoById(Long id);
}
