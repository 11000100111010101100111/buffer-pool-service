package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoReportImgInfoMapper;
import com.kit.video.domain.UserVideoReportImgInfo;
import com.kit.video.service.manager.IUserVideoReportImgInfoService;

/**
 * 视频的举报信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoReportImgInfoServiceImpl implements IUserVideoReportImgInfoService {
    @Autowired
    private UserVideoReportImgInfoMapper userVideoReportImgInfoMapper;

    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    @Override
    public UserVideoReportImgInfo selectUserVideoReportImgInfoById(String id) {
        return userVideoReportImgInfoMapper.selectUserVideoReportImgInfoById(id);
    }

    /**
     * 查询视频的举报信息列表
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 视频的举报信息
     */
    @Override
    public List<UserVideoReportImgInfo> selectUserVideoReportImgInfoList(UserVideoReportImgInfo userVideoReportImgInfo) {
        return userVideoReportImgInfoMapper.selectUserVideoReportImgInfoList(userVideoReportImgInfo);
    }

    /**
     * 新增视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    @Override
    public int insertUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo) {
                userVideoReportImgInfo.setCreateTime(LocalDateTime.now());
            return userVideoReportImgInfoMapper.insertUserVideoReportImgInfo(userVideoReportImgInfo);
    }

    /**
     * 修改视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    @Override
    public int updateUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo) {
                userVideoReportImgInfo.setUpdateTime(LocalDateTime.now());
        return userVideoReportImgInfoMapper.updateUserVideoReportImgInfo(userVideoReportImgInfo);
    }

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的视频的举报信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportImgInfoByIds(String[] ids) {
        return userVideoReportImgInfoMapper.deleteUserVideoReportImgInfoByIds(ids);
    }

    /**
     * 删除视频的举报信息信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoReportImgInfoById(String id) {
        return userVideoReportImgInfoMapper.deleteUserVideoReportImgInfoById(id);
    }
}
