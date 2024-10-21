package com.kit.video.service.manager.impl;

import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoLikeInfoMapper;
import com.kit.video.domain.UserVideoLikeInfo;
import com.kit.video.service.manager.IUserVideoLikeInfoService;

/**
 * 视频的点赞信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoLikeInfoServiceImpl implements IUserVideoLikeInfoService {
    @Autowired
    private UserVideoLikeInfoMapper userVideoLikeInfoMapper;

    /**
     * 查询视频的点赞信息
     *
     * @param id 视频的点赞信息主键
     * @return 视频的点赞信息
     */
    @Override
    public UserVideoLikeInfo selectUserVideoLikeInfoById(String id) {
        return userVideoLikeInfoMapper.selectUserVideoLikeInfoById(id);
    }

    /**
     * 查询视频的点赞信息列表
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 视频的点赞信息
     */
    @Override
    public List<UserVideoLikeInfo> selectUserVideoLikeInfoList(UserVideoLikeInfo userVideoLikeInfo) {
        return userVideoLikeInfoMapper.selectUserVideoLikeInfoList(userVideoLikeInfo);
    }

    /**
     * 新增视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    @Override
    public int insertUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo) {
                userVideoLikeInfo.setCreateTime(DateUtils.getNowDate());
            return userVideoLikeInfoMapper.insertUserVideoLikeInfo(userVideoLikeInfo);
    }

    /**
     * 修改视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    @Override
    public int updateUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo) {
                userVideoLikeInfo.setUpdateTime(DateUtils.getNowDate());
        return userVideoLikeInfoMapper.updateUserVideoLikeInfo(userVideoLikeInfo);
    }

    /**
     * 批量删除视频的点赞信息
     *
     * @param ids 需要删除的视频的点赞信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoLikeInfoByIds(String[] ids) {
        return userVideoLikeInfoMapper.deleteUserVideoLikeInfoByIds(ids);
    }

    /**
     * 删除视频的点赞信息信息
     *
     * @param id 视频的点赞信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoLikeInfoById(String id) {
        return userVideoLikeInfoMapper.deleteUserVideoLikeInfoById(id);
    }
}
