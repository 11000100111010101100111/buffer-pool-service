package com.kit.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.domain.vo.open.VideoUserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频产生的相关数据Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoDataInfoMapper extends BaseMapper<UserVideoDataInfo> {
    /**
     * 查询视频产生的相关数据
     *
     * @param id 视频产生的相关数据主键
     * @return 视频产生的相关数据
     */
    public UserVideoDataInfo selectUserVideoDataInfoById(@Param("id")String id);

    public List<UserVideoDataInfo> selectUserVideoDataInfoByIds(@Param("ids") List<String> ids);

    /**
     * 查询视频产生的相关数据列表
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 视频产生的相关数据集合
     */
    public List<UserVideoDataInfo> selectUserVideoDataInfoList(UserVideoDataInfo userVideoDataInfo);

    /**
     * 新增视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    public int insertUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo);

    /**
     * 修改视频产生的相关数据
     *
     * @param userVideoDataInfo 视频产生的相关数据
     * @return 结果
     */
    public int updateUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo);

    /**
     * 删除视频产生的相关数据
     *
     * @param id 视频产生的相关数据主键
     * @return 结果
     */
    public int deleteUserVideoDataInfoById(@Param("id")String id);

    /**
     * 批量删除视频产生的相关数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoDataInfoByIds(@Param("ids")String[] ids);

    public List<VideoUserInfoVo> getUserInfoByUserIds(@Param("userIds")List<String> userIds);
}
