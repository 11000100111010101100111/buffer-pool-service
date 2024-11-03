package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoTagMapper;
import com.kit.video.domain.UserVideoTag;
import com.kit.video.service.manager.IUserVideoTagService;

/**
 * 视频相关的话题（标签）信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoTagServiceImpl implements IUserVideoTagService {
    @Autowired
    private UserVideoTagMapper userVideoTagMapper;

    /**
     * 查询视频相关的话题（标签）信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 视频相关的话题（标签）信息
     */
    @Override
    public UserVideoTag selectUserVideoTagById(String id) {
        return userVideoTagMapper.selectUserVideoTagById(id);
    }

    /**
     * 查询视频相关的话题（标签）信息列表
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 视频相关的话题（标签）信息
     */
    @Override
    public List<UserVideoTag> selectUserVideoTagList(UserVideoTag userVideoTag) {
        return userVideoTagMapper.selectUserVideoTagList(userVideoTag);
    }

    /**
     * 新增视频相关的话题（标签）信息
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 结果
     */
    @Override
    public int insertUserVideoTag(UserVideoTag userVideoTag) {
                userVideoTag.setCreateTime(LocalDateTime.now());
            return userVideoTagMapper.insertUserVideoTag(userVideoTag);
    }

    /**
     * 修改视频相关的话题（标签）信息
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 结果
     */
    @Override
    public int updateUserVideoTag(UserVideoTag userVideoTag) {
                userVideoTag.setUpdateTime(LocalDateTime.now());
        return userVideoTagMapper.updateUserVideoTag(userVideoTag);
    }

    /**
     * 批量删除视频相关的话题（标签）信息
     *
     * @param ids 需要删除的视频相关的话题（标签）信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoTagByIds(String[] ids) {
        return userVideoTagMapper.deleteUserVideoTagByIds(ids);
    }

    /**
     * 删除视频相关的话题（标签）信息信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoTagById(String id) {
        return userVideoTagMapper.deleteUserVideoTagById(id);
    }
}
