package com.kit.video.service.manager.impl;

import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoMetadataInfoMapper;
import com.kit.video.domain.UserVideoMetadataInfo;
import com.kit.video.service.manager.IUserVideoMetadataInfoService;

/**
 * 视频原始信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoMetadataInfoServiceImpl implements IUserVideoMetadataInfoService {
    @Autowired
    private UserVideoMetadataInfoMapper userVideoMetadataInfoMapper;

    /**
     * 查询视频原始信息
     *
     * @param id 视频原始信息主键
     * @return 视频原始信息
     */
    @Override
    public UserVideoMetadataInfo selectUserVideoMetadataInfoById(String id) {
        return userVideoMetadataInfoMapper.selectUserVideoMetadataInfoById(id);
    }

    /**
     * 查询视频原始信息列表
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 视频原始信息
     */
    @Override
    public List<UserVideoMetadataInfo> selectUserVideoMetadataInfoList(UserVideoMetadataInfo userVideoMetadataInfo) {
        return userVideoMetadataInfoMapper.selectUserVideoMetadataInfoList(userVideoMetadataInfo);
    }

    /**
     * 新增视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    @Override
    public int insertUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo) {
                userVideoMetadataInfo.setCreateTime(DateUtils.getNowDate());
            return userVideoMetadataInfoMapper.insertUserVideoMetadataInfo(userVideoMetadataInfo);
    }

    /**
     * 修改视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    @Override
    public int updateUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo) {
                userVideoMetadataInfo.setUpdateTime(DateUtils.getNowDate());
        return userVideoMetadataInfoMapper.updateUserVideoMetadataInfo(userVideoMetadataInfo);
    }

    /**
     * 批量删除视频原始信息
     *
     * @param ids 需要删除的视频原始信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoMetadataInfoByIds(String[] ids) {
        return userVideoMetadataInfoMapper.deleteUserVideoMetadataInfoByIds(ids);
    }

    /**
     * 删除视频原始信息信息
     *
     * @param id 视频原始信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoMetadataInfoById(String id) {
        return userVideoMetadataInfoMapper.deleteUserVideoMetadataInfoById(id);
    }
}
