package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.UserVideoWithTagMapper;
import com.kit.video.domain.UserVideoWithTag;
import com.kit.video.service.manager.IUserVideoWithTagService;

/**
 * 视频与话题（标签）信息的关联Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class UserVideoWithTagServiceImpl implements IUserVideoWithTagService {
    @Autowired
    private UserVideoWithTagMapper userVideoWithTagMapper;

    /**
     * 查询视频与话题（标签）信息的关联
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 视频与话题（标签）信息的关联
     */
    @Override
    public UserVideoWithTag selectUserVideoWithTagById(Long id) {
        return userVideoWithTagMapper.selectUserVideoWithTagById(id);
    }

    /**
     * 查询视频与话题（标签）信息的关联列表
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 视频与话题（标签）信息的关联
     */
    @Override
    public List<UserVideoWithTag> selectUserVideoWithTagList(UserVideoWithTag userVideoWithTag) {
        return userVideoWithTagMapper.selectUserVideoWithTagList(userVideoWithTag);
    }

    /**
     * 新增视频与话题（标签）信息的关联
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 结果
     */
    @Override
    public int insertUserVideoWithTag(UserVideoWithTag userVideoWithTag) {
                userVideoWithTag.setCreateTime(LocalDateTime.now());
            return userVideoWithTagMapper.insertUserVideoWithTag(userVideoWithTag);
    }

    /**
     * 修改视频与话题（标签）信息的关联
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 结果
     */
    @Override
    public int updateUserVideoWithTag(UserVideoWithTag userVideoWithTag) {
                userVideoWithTag.setUpdateTime(LocalDateTime.now());
        return userVideoWithTagMapper.updateUserVideoWithTag(userVideoWithTag);
    }

    /**
     * 批量删除视频与话题（标签）信息的关联
     *
     * @param ids 需要删除的视频与话题（标签）信息的关联主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoWithTagByIds(Long[] ids) {
        return userVideoWithTagMapper.deleteUserVideoWithTagByIds(ids);
    }

    /**
     * 删除视频与话题（标签）信息的关联信息
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 结果
     */
    @Override
    public int deleteUserVideoWithTagById(Long id) {
        return userVideoWithTagMapper.deleteUserVideoWithTagById(id);
    }
}
