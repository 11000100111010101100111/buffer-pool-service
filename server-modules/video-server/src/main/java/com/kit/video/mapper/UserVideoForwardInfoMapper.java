package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoForwardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的转发信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoForwardInfoMapper extends BaseMapper<UserVideoForwardInfo> {
    /**
     * 查询视频的转发信息
     *
     * @param id 视频的转发信息主键
     * @return 视频的转发信息
     */
    public UserVideoForwardInfo selectUserVideoForwardInfoById(@Param("id")String id);

    /**
     * 查询视频的转发信息列表
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 视频的转发信息集合
     */
    public List<UserVideoForwardInfo> selectUserVideoForwardInfoList(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 新增视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    public int insertUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 修改视频的转发信息
     *
     * @param userVideoForwardInfo 视频的转发信息
     * @return 结果
     */
    public int updateUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo);

    /**
     * 删除视频的转发信息
     *
     * @param id 视频的转发信息主键
     * @return 结果
     */
    public int deleteUserVideoForwardInfoById(@Param("id")String id);

    /**
     * 批量删除视频的转发信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoForwardInfoByIds(@Param("ids")String[] ids);
}
