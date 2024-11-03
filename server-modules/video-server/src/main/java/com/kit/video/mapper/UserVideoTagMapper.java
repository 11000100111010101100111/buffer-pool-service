package com.kit.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoTag;
import com.kit.video.domain.vo.open.VideoWithTagInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频相关的话题（标签）信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoTagMapper extends BaseMapper<UserVideoTag> {
    /**
     * 查询视频相关的话题（标签）信息
     *
     * @param id 视频相关的话题（标签）信息主键
     * @return 视频相关的话题（标签）信息
     */
    public UserVideoTag selectUserVideoTagById(@Param("id")String id);

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
    public int deleteUserVideoTagById(@Param("id")String id);

    /**
     * 批量删除视频相关的话题（标签）信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoTagByIds(@Param("ids")String[] ids);

    /**
     * 根据视频IDs查询视频的标签列表
     *
     * @param videoIds 需要删除的数据主键集合
     * @return 结果
     */
    public List<VideoWithTagInfoVo> selectVideoTagsByVideoIds(@Param("videoIds")List<String> videoIds);

    @Insert({
            "<script>",
            "INSERT INTO user_video_tag (name, use_times) VALUES ",
            "<foreach collection='entities' item='item' separator=','>",
            "(#{item.name}, 0)",
            "</foreach>",
            " ON DUPLICATE KEY UPDATE use_times = use_times + 1",
            "</script>"
    })
    public void batchSaveOrUpdate(@Param("entities")List<UserVideoTag> entities);
}
