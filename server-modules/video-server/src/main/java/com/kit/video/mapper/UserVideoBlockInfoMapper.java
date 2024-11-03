package com.kit.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoBlockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserVideoBlockInfoMapper extends BaseMapper<UserVideoBlockInfo> {
    /**
     * 查询视频的分块信息
     *
     * @param id 视频的分块信息主键
     * @return 视频的分块信息
     */
    public UserVideoBlockInfo selectVideoBlockById(@Param("id") String id);

    /**
     * 查询视频的分块信息
     *
     * @param videoId 视频的分块信息主键
     * @return 视频的分块信息
     */
    public List<UserVideoBlockInfo> selectVideoBlockByVideoId(@Param("videoId") String videoId);

    /**
     * 查询视频的分块信息
     *
     * @param videoIds 视频的分块信息主键
     * @return 视频的分块信息
     */
    public List<UserVideoBlockInfo> selectVideoBlockByVideoIds(@Param("videoIds") List<String> videoIds);

    /**
     * 查询视频的分块信息列表
     *
     * @param userVideoBrowseInfo 视频的分块信息
     * @return 视频的分块信息集合
     */
    public List<UserVideoBlockInfo> selectUserVideoBlockInfoList(UserVideoBlockInfo userVideoBrowseInfo);

    /**
     * 新增视频的分块信息
     *
     * @param userVideoBrowseInfo 视频的分块信息
     * @return 结果
     */
    public int insertVideoBlockInfo(UserVideoBlockInfo userVideoBrowseInfo);

    /**
     * 修改视频的分块信息
     *
     * @param userVideoBrowseInfo 视频的分块信息
     * @return 结果
     */
    public int updateVideoBlockInfo(UserVideoBlockInfo userVideoBrowseInfo);

    /**
     * 删除视频的分块信息
     *
     * @param id 视频的分块信息主键
     * @return 结果
     */
    public int deleteVideoBlockById(@Param("id")String id);

    /**
     * 批量删除视频的分块信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoBlockByIds(@Param("ids")String[] ids);

    /**
     * 删除视频的分块信息
     *
     * @param id 视频的分块信息主键
     * @return 结果
     */
    public int deleteVideoBlockByVideoId(@Param("ids")String id);

    /**
     * 批量删除视频的分块信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoBlockByVideoIds(@Param("ids")String[] ids);
}
