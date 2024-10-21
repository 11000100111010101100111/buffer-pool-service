package com.kit.video.mapper;

import java.util.List;

import com.kit.video.domain.UserVideoReportType;

/**
 * 举报类型信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface UserVideoReportTypeMapper {
    /**
     * 查询举报类型信息
     *
     * @param id 举报类型信息主键
     * @return 举报类型信息
     */
    public UserVideoReportType selectUserVideoReportTypeById(String id);

    /**
     * 查询举报类型信息列表
     *
     * @param userVideoReportType 举报类型信息
     * @return 举报类型信息集合
     */
    public List<UserVideoReportType> selectUserVideoReportTypeList(UserVideoReportType userVideoReportType);

    /**
     * 新增举报类型信息
     *
     * @param userVideoReportType 举报类型信息
     * @return 结果
     */
    public int insertUserVideoReportType(UserVideoReportType userVideoReportType);

    /**
     * 修改举报类型信息
     *
     * @param userVideoReportType 举报类型信息
     * @return 结果
     */
    public int updateUserVideoReportType(UserVideoReportType userVideoReportType);

    /**
     * 删除举报类型信息
     *
     * @param id 举报类型信息主键
     * @return 结果
     */
    public int deleteUserVideoReportTypeById(String id);

    /**
     * 批量删除举报类型信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoReportTypeByIds(String[] ids);
}