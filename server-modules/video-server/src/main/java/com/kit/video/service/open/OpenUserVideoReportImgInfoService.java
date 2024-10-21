package com.kit.video.service.open;

import com.kit.video.domain.UserVideoReportImgInfo;

import java.util.List;

/**
 * 视频的举报信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoReportImgInfoService {
    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    public UserVideoReportImgInfo selectUserVideoReportImgInfoById(String id);

    /**
     * 查询视频的举报信息列表
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 视频的举报信息集合
     */
    public List<UserVideoReportImgInfo> selectUserVideoReportImgInfoList(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 新增视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    public int insertUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 修改视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    public int updateUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的视频的举报信息主键集合
     * @return 结果
     */
    public int deleteUserVideoReportImgInfoByIds(String[] ids);

    /**
     * 删除视频的举报信息信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    public int deleteUserVideoReportImgInfoById(String id);
}
