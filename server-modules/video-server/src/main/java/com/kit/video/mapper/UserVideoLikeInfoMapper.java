package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoLikeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的点赞信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoLikeInfoMapper extends BaseMapper<UserVideoLikeInfo> {
    /**
     * 查询视频的点赞信息
     *
     * @param id 视频的点赞信息主键
     * @return 视频的点赞信息
     */
    public UserVideoLikeInfo selectUserVideoLikeInfoById(@Param("id")String id);

    /**
     * 查询视频的点赞信息列表
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 视频的点赞信息集合
     */
    public List<UserVideoLikeInfo> selectUserVideoLikeInfoList(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 新增视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    public int insertUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 修改视频的点赞信息
     *
     * @param userVideoLikeInfo 视频的点赞信息
     * @return 结果
     */
    public int updateUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo);

    /**
     * 删除视频的点赞信息
     *
     * @param id 视频的点赞信息主键
     * @return 结果
     */
    public int deleteUserVideoLikeInfoById(@Param("id")String id);

    /**
     * 批量删除视频的点赞信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoLikeInfoByIds(@Param("ids")String[] ids);
}
