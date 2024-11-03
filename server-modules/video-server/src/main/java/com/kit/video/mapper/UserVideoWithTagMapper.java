package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoWithTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频与话题（标签）信息的关联Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoWithTagMapper extends BaseMapper<UserVideoWithTag> {
    /**
     * 查询视频与话题（标签）信息的关联
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 视频与话题（标签）信息的关联
     */
    public UserVideoWithTag selectUserVideoWithTagById(@Param("id")Long id);

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
     * 删除视频与话题（标签）信息的关联
     *
     * @param id 视频与话题（标签）信息的关联主键
     * @return 结果
     */
    public int deleteUserVideoWithTagById(@Param("id")Long id);

    /**
     * 批量删除视频与话题（标签）信息的关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoWithTagByIds(@Param("ids")Long[] ids);
}
