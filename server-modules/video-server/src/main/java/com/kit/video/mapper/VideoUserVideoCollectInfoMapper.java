package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.VideoUserVideoCollectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的被收藏信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface VideoUserVideoCollectInfoMapper extends BaseMapper<VideoUserVideoCollectInfo> {
    /**
     * 查询视频的被收藏信息
     *
     * @param id 视频的被收藏信息主键
     * @return 视频的被收藏信息
     */
    public VideoUserVideoCollectInfo selectVideoUserVideoCollectInfoById(@Param("id")String id);

    /**
     * 查询视频的被收藏信息列表
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 视频的被收藏信息集合
     */
    public List<VideoUserVideoCollectInfo> selectVideoUserVideoCollectInfoList(VideoUserVideoCollectInfo videoUserVideoCollectInfo);

    /**
     * 新增视频的被收藏信息
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 结果
     */
    public int insertVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo);

    /**
     * 修改视频的被收藏信息
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 结果
     */
    public int updateVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo);

    /**
     * 删除视频的被收藏信息
     *
     * @param id 视频的被收藏信息主键
     * @return 结果
     */
    public int deleteVideoUserVideoCollectInfoById(@Param("id")String id);

    /**
     * 批量删除视频的被收藏信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoUserVideoCollectInfoByIds(@Param("ids")String[] ids);
}
