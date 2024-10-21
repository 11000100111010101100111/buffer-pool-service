package com.kit.video.service.open;

import com.kit.video.domain.UserVideoReportInfo;

import java.util.List;

/**
 * 视频的举报信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoReportInfoService {
    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    public UserVideoReportInfo selectUserVideoReportInfoById(String id);

    /**
     * 查询视频的举报信息列表
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 视频的举报信息集合
     */
    public List<UserVideoReportInfo> selectUserVideoReportInfoList(UserVideoReportInfo userVideoReportInfo);

    /**
     * 新增视频的举报信息
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 结果
     */
    public int insertUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo);

    /**
     * 修改视频的举报信息
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 结果
     */
    public int updateUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo);

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的视频的举报信息主键集合
     * @return 结果
     */
    public int deleteUserVideoReportInfoByIds(String[] ids);

    /**
     * 删除视频的举报信息信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    public int deleteUserVideoReportInfoById(String id);
}
