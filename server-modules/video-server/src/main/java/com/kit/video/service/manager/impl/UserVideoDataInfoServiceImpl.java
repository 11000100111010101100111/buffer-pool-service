package com.kit.video.service.manager.impl;

import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.mapper.UserVideoDataInfoMapper;
import com.kit.video.service.manager.IUserVideoDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 视频产生的相关数据Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoDataInfoServiceImpl implements IUserVideoDataInfoService {
    @Autowired
    private UserVideoDataInfoMapper userVideoDataInfoMapper;

    /**
     * 查询视频产生的相关数据
     *
     * @param id 视频产生的相关数据主键
     * @return 视频产生的相关数据
     */
    @Override
    public UserVideoDataInfo selectUserVideoDataInfoById(String id) {
        return userVideoDataInfoMapper.selectUserVideoDataInfoById(id);
    }

    /**
     * 查询视频产生的相关数据列表
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 视频产生的相关数据
     */
    @Override
    public List<UserVideoDataInfo> selectUserVideoDataInfoList(UserVideoDataInfo userVideoDataInfo) {
        return userVideoDataInfoMapper.selectUserVideoDataInfoList(userVideoDataInfo);
    }

    /**
     * 新增视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    @Override
    public int insertUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo) {
        userVideoDataInfo.setCreateTime(LocalDateTime.now());
        return userVideoDataInfoMapper.insertUserVideoDataInfo(userVideoDataInfo);
    }

    /**
     * 修改视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    @Override
    public int updateUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo) {
        userVideoDataInfo.setUpdateTime(LocalDateTime.now());
        return userVideoDataInfoMapper.updateUserVideoDataInfo(userVideoDataInfo);
    }

    /**
     * 批量删除视频产生的相关数据
     *
     * @param ids 需要删除的视频产生的相关数据主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoDataInfoByIds(String[] ids) {
        return userVideoDataInfoMapper.deleteUserVideoDataInfoByIds(ids);
    }

    /**
     * 删除视频产生的相关数据信息
     *
     * @param id 视频产生的相关数据主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoDataInfoById(String id) {
        return userVideoDataInfoMapper.deleteUserVideoDataInfoById(id);
    }
}
