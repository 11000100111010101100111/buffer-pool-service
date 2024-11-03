package com.kit.video.service.open;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kit.video.domain.UserVideoWithTag;

import java.util.List;

/**
 * 视频与话题（标签）信息的关联Service接口
 *
 * @author xjh
 * @date 2024-10-20
 */
public interface OpenUserVideoWithTagService extends IService<UserVideoWithTag> {
    /**
     * 查询视频与话题（标签）信息的关联
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 视频与话题（标签）信息的关联
     */
    public UserVideoWithTag selectUserVideoWithTagById(Long id);

    /**
     * 查询视频与话题（标签）信息的关联列表
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 视频与话题（标签）信息的关联集合
     */
    public List<UserVideoWithTag> selectUserVideoWithTagList(UserVideoWithTag userVideoWithTag);

    /**
     * 新增视频与话题（标签）信息的关联
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 结果
     */
    public int insertUserVideoWithTag(UserVideoWithTag userVideoWithTag);

    /**
     * 修改视频与话题（标签）信息的关联
     *
     * @param userVideoWithTag 视频与话题（标签）信息的关联
     * @return 结果
     */
    public int updateUserVideoWithTag(UserVideoWithTag userVideoWithTag);

    /**
     * 批量删除视频与话题（标签）信息的关联
     *
     * @param ids 需要删除的视频与话题（标签）信息的关联主键集合
     * @return 结果
     */
    public int deleteUserVideoWithTagByIds(Long[] ids);

    /**
     * 删除视频与话题（标签）信息的关联信息
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 结果
     */
    public int deleteUserVideoWithTagById(Long id);
}
