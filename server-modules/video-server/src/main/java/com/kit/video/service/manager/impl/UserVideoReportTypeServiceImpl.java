package com.kit.video.service.manager.impl;

import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoReportTypeMapper;
import com.kit.video.domain.UserVideoReportType;
import com.kit.video.service.manager.IUserVideoReportTypeService;

/**
 * 举报类型信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoReportTypeServiceImpl implements IUserVideoReportTypeService {
    @Autowired
    private UserVideoReportTypeMapper userVideoReportTypeMapper;

    /**
     * 查询举报类型信息
     *
     * @param id 举报类型信息主键
     * @return 举报类型信息
     */
    @Override
    public UserVideoReportType selectUserVideoReportTypeById(String id) {
        return userVideoReportTypeMapper.selectUserVideoReportTypeById(id);
    }

    /**
     * 查询举报类型信息列表
     *
     * @param userVideoReportType 举报类型信息
     * @return 举报类型信息
     */
    @Override
    public List<UserVideoReportType> selectUserVideoReportTypeList(UserVideoReportType userVideoReportType) {
        return userVideoReportTypeMapper.selectUserVideoReportTypeList(userVideoReportType);
    }

    /**
     * 新增举报类型信息
     *
     * @param userVideoReportType 举报类型信息
     * @return 结果
     */
    @Override
    public int insertUserVideoReportType(UserVideoReportType userVideoReportType) {
                userVideoReportType.setCreateTime(DateUtils.getNowDate());
            return userVideoReportTypeMapper.insertUserVideoReportType(userVideoReportType);
    }

    /**
     * 修改举报类型信息
     *
     * @param userVideoReportType 举报类型信息
     * @return 结果
     */
    @Override
    public int updateUserVideoReportType(UserVideoReportType userVideoReportType) {
                userVideoReportType.setUpdateTime(DateUtils.getNowDate());
        return userVideoReportTypeMapper.updateUserVideoReportType(userVideoReportType);
    }

    /**
     * 批量删除举报类型信息
     *
     * @param ids 需要删除的举报类型信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportTypeByIds(String[] ids) {
        return userVideoReportTypeMapper.deleteUserVideoReportTypeByIds(ids);
    }

    /**
     * 删除举报类型信息信息
     *
     * @param id 举报类型信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportTypeById(String id) {
        return userVideoReportTypeMapper.deleteUserVideoReportTypeById(id);
    }
}
