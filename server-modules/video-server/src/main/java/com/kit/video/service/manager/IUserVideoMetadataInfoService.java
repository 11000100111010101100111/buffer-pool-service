package com.kit.video.service.manager;

import java.util.List;

import com.kit.video.domain.UserVideoMetadataInfo;

/**
 * 视频原始信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface IUserVideoMetadataInfoService {
    /**
     * 查询视频原始信息
     *
     * @param id 视频原始信息主键
     * @return 视频原始信息
     */
    public UserVideoMetadataInfo selectUserVideoMetadataInfoById(String id);

    /**
     * 查询视频原始信息列表
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 视频原始信息集合
     */
    public List<UserVideoMetadataInfo> selectUserVideoMetadataInfoList(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 新增视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    public int insertUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 修改视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    public int updateUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 批量删除视频原始信息
     *
     * @param ids 需要删除的视频原始信息主键集合
     * @return 结果
     */
    public int deleteUserVideoMetadataInfoByIds(String[] ids);

    /**
     * 删除视频原始信息信息
     *
     * @param id 视频原始信息主键
     * @return 结果
     */
    public int deleteUserVideoMetadataInfoById(String id);
}
