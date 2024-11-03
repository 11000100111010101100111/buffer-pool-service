package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoReportImgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的举报信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoReportImgInfoMapper extends BaseMapper<UserVideoReportImgInfo> {
    /**
     * 查询视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 视频的举报信息
     */
    public UserVideoReportImgInfo selectUserVideoReportImgInfoById(@Param("id")String id);

    /**
     * 查询视频的举报信息列表
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 视频的举报信息集合
     */
    public List<UserVideoReportImgInfo> selectUserVideoReportImgInfoList(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 新增视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    public int insertUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 修改视频的举报信息
     *
     * @param userVideoReportImgInfo 视频的举报信息
     * @return 结果
     */
    public int updateUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo);

    /**
     * 删除视频的举报信息
     *
     * @param id 视频的举报信息主键
     * @return 结果
     */
    public int deleteUserVideoReportImgInfoById(@Param("id")String id);

    /**
     * 批量删除视频的举报信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoReportImgInfoByIds(@Param("ids")String[] ids);
}
