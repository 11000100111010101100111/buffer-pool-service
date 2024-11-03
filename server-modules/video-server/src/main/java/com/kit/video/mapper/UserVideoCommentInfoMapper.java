package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoCommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的评论信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoCommentInfoMapper extends BaseMapper<UserVideoCommentInfo> {
    /**
     * 查询视频的评论信息
     *
     * @param id 视频的评论信息主键
     * @return 视频的评论信息
     */
    public UserVideoCommentInfo selectUserVideoCommentInfoById(@Param("id")String id);

    /**
     * 查询视频的评论信息列表
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 视频的评论信息集合
     */
    public List<UserVideoCommentInfo> selectUserVideoCommentInfoList(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 新增视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    public int insertUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 修改视频的评论信息
     *
     * @param userVideoCommentInfo 视频的评论信息
     * @return 结果
     */
    public int updateUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo);

    /**
     * 删除视频的评论信息
     *
     * @param id 视频的评论信息主键
     * @return 结果
     */
    public int deleteUserVideoCommentInfoById(@Param("id")String id);

    /**
     * 批量删除视频的评论信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoCommentInfoByIds(@Param("ids")String[] ids);
}
