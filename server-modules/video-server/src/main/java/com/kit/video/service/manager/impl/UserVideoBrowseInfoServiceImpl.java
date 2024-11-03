package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kit.common.utils.DateUtils;
import com.kit.video.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoBrowseInfoMapper;
import com.kit.video.domain.UserVideoBrowseInfo;
import com.kit.video.service.manager.IUserVideoBrowseInfoService;

/**
 * 视频的浏览信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoBrowseInfoServiceImpl implements IUserVideoBrowseInfoService {
    @Autowired
    private UserVideoBrowseInfoMapper userVideoBrowseInfoMapper;

    /**
     * 查询视频的浏览信息
     *
     * @param id 视频的浏览信息主键
     * @return 视频的浏览信息
     */
    @Override
    public UserVideoBrowseInfo selectUserVideoBrowseInfoById(String id) {
        return userVideoBrowseInfoMapper.selectUserVideoBrowseInfoById(id);
    }

    /**
     * 查询视频的浏览信息列表
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 视频的浏览信息
     */
    @Override
    public List<UserVideoBrowseInfo> selectUserVideoBrowseInfoList(UserVideoBrowseInfo userVideoBrowseInfo) {
        return userVideoBrowseInfoMapper.selectUserVideoBrowseInfoList(userVideoBrowseInfo);
    }

    /**
     * 新增视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    @Override
    public int insertUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo) {
                userVideoBrowseInfo.setCreateTime(LocalDateTime.now());
            return userVideoBrowseInfoMapper.insertUserVideoBrowseInfo(userVideoBrowseInfo);
    }

    /**
     * 修改视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    @Override
    public int updateUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo) {
                userVideoBrowseInfo.setUpdateTime(LocalDateTime.now());
        return userVideoBrowseInfoMapper.updateUserVideoBrowseInfo(userVideoBrowseInfo);
    }

    /**
     * 批量删除视频的浏览信息
     *
     * @param ids 需要删除的视频的浏览信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoBrowseInfoByIds(String[] ids) {
        return userVideoBrowseInfoMapper.deleteUserVideoBrowseInfoByIds(ids);
    }

    /**
     * 删除视频的浏览信息信息
     *
     * @param id 视频的浏览信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoBrowseInfoById(String id) {
        return userVideoBrowseInfoMapper.deleteUserVideoBrowseInfoById(id);
    }

    @Override
    public List<String> queryVideoIdsByRecommended(List<String> filterVideoIds, int count) {
        final String userId = AuthUtils.getUserId();
        final List<String> ids = userVideoBrowseInfoMapper.queryVideoIdsByRecommended(userId, filterVideoIds, count);
        if (null != userId && ids.isEmpty() || ids.size() < count) {
            if (null == filterVideoIds) {
                filterVideoIds = new ArrayList<>(ids);
            } else {
                filterVideoIds.addAll(ids);
            }
            ids.addAll(userVideoBrowseInfoMapper.queryVideoIdsByRecommended(null, filterVideoIds, count));
        }
        return ids;
    }
}
