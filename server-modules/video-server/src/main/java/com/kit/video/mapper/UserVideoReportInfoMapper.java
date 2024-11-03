package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoReportInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的举报信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoReportInfoMapper extends BaseMapper<UserVideoReportInfo> {
    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    public UserVideoReportInfo selectUserVideoReportInfoById(@Param("id")String id);

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
     * 删除视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    public int deleteUserVideoReportInfoById(@Param("id")String id);

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoReportInfoByIds(@Param("ids")String[] ids);
}
