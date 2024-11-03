package com.kit.video.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kit.video.domain.UserVideoMetadataInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频原始信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoMetadataInfoMapper extends BaseMapper<UserVideoMetadataInfo> {
    /**
     * 查询视频原始信息
     *
     * @param id 视频原始信息主键
     * @return 视频原始信息
     */
    public UserVideoMetadataInfo selectUserVideoMetadataInfoById(@Param("id") String id);
    /**
     * 查询视频原始信息
     *
     * @param ids 视频原始信息主键
     * @return 视频原始信息
     */
    public List<UserVideoMetadataInfo> selectUserVideoMetadataInfoByIds(@Param("ids") List<String> ids);

    /**
     * 查询视频原始信息列表
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 视频原始信息集合
     */
    public List<UserVideoMetadataInfo> selectUserVideoMetadataInfoList(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 新增视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    public int insertUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 修改视频原始信息
     *
     * @param userVideoMetadataInfo 视频原始信息
     * @return 结果
     */
    public int updateUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo);

    /**
     * 删除视频原始信息
     *
     * @param id 视频原始信息主键
     * @return 结果
     */
    public int deleteUserVideoMetadataInfoById(@Param("id") String id);

    /**
     * 批量删除视频原始信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoMetadataInfoByIds(@Param("ids") String[] ids);
}
