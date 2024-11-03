package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoReportInfoMapper;
import com.kit.video.domain.UserVideoReportInfo;
import com.kit.video.service.manager.IUserVideoReportInfoService;

/**
 * 视频的举报信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoReportInfoServiceImpl implements IUserVideoReportInfoService {
    @Autowired
    private UserVideoReportInfoMapper userVideoReportInfoMapper;

    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    @Override
    public UserVideoReportInfo selectUserVideoReportInfoById(String id) {
        return userVideoReportInfoMapper.selectUserVideoReportInfoById(id);
    }

    /**
     * 查询视频的举报信息列表
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 视频的举报信息
     */
    @Override
    public List<UserVideoReportInfo> selectUserVideoReportInfoList(UserVideoReportInfo userVideoReportInfo) {
        return userVideoReportInfoMapper.selectUserVideoReportInfoList(userVideoReportInfo);
    }

    /**
     * 新增视频的举报信息
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 结果
     */
    @Override
    public int insertUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo) {
                userVideoReportInfo.setCreateTime(LocalDateTime.now());
            return userVideoReportInfoMapper.insertUserVideoReportInfo(userVideoReportInfo);
    }

    /**
     * 修改视频的举报信息
     *
     * @param userVideoReportInfo 视频的举报信息
     * @return 结果
     */
    @Override
    public int updateUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo) {
                userVideoReportInfo.setUpdateTime(LocalDateTime.now());
        return userVideoReportInfoMapper.updateUserVideoReportInfo(userVideoReportInfo);
    }

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的视频的举报信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportInfoByIds(String[] ids) {
        return userVideoReportInfoMapper.deleteUserVideoReportInfoByIds(ids);
    }

    /**
     * 删除视频的举报信息信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportInfoById(String id) {
        return userVideoReportInfoMapper.deleteUserVideoReportInfoById(id);
    }
}
