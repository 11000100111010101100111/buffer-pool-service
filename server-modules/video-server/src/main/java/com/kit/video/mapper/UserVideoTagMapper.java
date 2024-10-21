package com.kit.video.mapper;

import java.util.List;

import com.kit.video.domain.UserVideoTag;

/**
 * 视频相关的话题（标签）信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface UserVideoTagMapper {
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
     * 删除视频相关的话题（标签）信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 结果
     */
    public int deleteUserVideoTagById(String id);

    /**
     * 批量删除视频相关的话题（标签）信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoTagByIds(String[] ids);
}
