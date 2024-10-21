package com.kit.video.service.manager.impl;

import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoForwardInfoMapper;
import com.kit.video.domain.UserVideoForwardInfo;
import com.kit.video.service.manager.IUserVideoForwardInfoService;

/**
 * 视频的转发信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoForwardInfoServiceImpl implements IUserVideoForwardInfoService {
    @Autowired
    private UserVideoForwardInfoMapper userVideoForwardInfoMapper;

    /**
     * 查询视频的转发信息
     *
     * @param id 视频的转发信息主键
     * @return 视频的转发信息
     */
    @Override
    public UserVideoForwardInfo selectUserVideoForwardInfoById(String id) {
        return userVideoForwardInfoMapper.selectUserVideoForwardInfoById(id);
    }

    /**
     * 查询视频的转发信息列表
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 视频的转发信息
     */
    @Override
    public List<UserVideoForwardInfo> selectUserVideoForwardInfoList(UserVideoForwardInfo userVideoForwardInfo) {
        return userVideoForwardInfoMapper.selectUserVideoForwardInfoList(userVideoForwardInfo);
    }

    /**
     * 新增视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    @Override
    public int insertUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo) {
                userVideoForwardInfo.setCreateTime(DateUtils.getNowDate());
            return userVideoForwardInfoMapper.insertUserVideoForwardInfo(userVideoForwardInfo);
    }

    /**
     * 修改视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    @Override
    public int updateUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo) {
                userVideoForwardInfo.setUpdateTime(DateUtils.getNowDate());
        return userVideoForwardInfoMapper.updateUserVideoForwardInfo(userVideoForwardInfo);
    }

    /**
     * 批量删除视频的转发信息
     *
     * @param ids 需要删除的视频的转发信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoForwardInfoByIds(String[] ids) {
        return userVideoForwardInfoMapper.deleteUserVideoForwardInfoByIds(ids);
    }

    /**
     * 删除视频的转发信息信息
     *
     * @param id 视频的转发信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoForwardInfoById(String id) {
        return userVideoForwardInfoMapper.deleteUserVideoForwardInfoById(id);
    }
}
