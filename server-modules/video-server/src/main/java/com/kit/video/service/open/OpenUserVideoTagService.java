package com.kit.video.service.open;

import com.kit.video.domain.UserVideoTag;

import java.util.List;

/**
 * 视频相关的话题（标签）信息Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoTagService {
    /**
     * 查询视频相关的话题（标签）信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 视频相关的话题（标签）信息
     */
    public UserVideoTag selectUserVideoTagById(String id);

    /**
     * 查询视频相关的话题（标签）信息列表
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 视频相关的话题（标签）信息集合
     */
    public List<UserVideoTag> selectUserVideoTagList(UserVideoTag userVideoTag);

    /**
     * 新增视频相关的话题（标签）信息
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 结果
     */
    public int insertUserVideoTag(UserVideoTag userVideoTag);

    /**
     * 修改视频相关的话题（标签）信息
     *
     * @param userVideoTag 视频相关的话题（标签）信息
     * @return 结果
     */
    public int updateUserVideoTag(UserVideoTag userVideoTag);

    /**
     * 批量删除视频相关的话题（标签）信息
     *
     * @param ids 需要删除的视频相关的话题（标签）信息主键集合
     * @return 结果
     */
    public int deleteUserVideoTagByIds(String[] ids);

    /**
     * 删除视频相关的话题（标签）信息信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 结果
     */
    public int deleteUserVideoTagById(String id);
}
