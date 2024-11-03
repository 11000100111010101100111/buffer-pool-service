package com.kit.video.mapper;

import java.util.List;

import com.kit.video.domain.UserVideoBrowseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 视频的浏览信息Mapper接口
 *
 * @author xjh
 * @date 2024-10-20
 */
@Mapper
public interface UserVideoBrowseInfoMapper {
    /**
     * 查询视频的浏览信息
     *
     * @param id 视频的浏览信息主键
     * @return 视频的浏览信息
     */
    public UserVideoBrowseInfo selectUserVideoBrowseInfoById(@Param("id")String id);

    /**
     * 查询视频的浏览信息列表
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 视频的浏览信息集合
     */
    public List<UserVideoBrowseInfo> selectUserVideoBrowseInfoList(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 新增视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    public int insertUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 修改视频的浏览信息
     *
     * @param userVideoBrowseInfo 视频的浏览信息
     * @return 结果
     */
    public int updateUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo);

    /**
     * 删除视频的浏览信息
     *
     * @param id 视频的浏览信息主键
     * @return 结果
     */
    public int deleteUserVideoBrowseInfoById(@Param("id")String id);

    /**
     * 批量删除视频的浏览信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserVideoBrowseInfoByIds(@Param("ids")String[] ids);


    /**
     * 查询推荐视频列表，根据视频积分排序，过滤已经看过的视频，未登录不需要过滤
     *
     * @param userId 用户ID,未登录未null
     * @param count 查询数目
     * @return 结果 视频ID列表
     */
    public List<String> queryVideoIdsByRecommended(
            @Param("userId") String userId,
            @Param("filterVideoIds") List<String> filterVideoIds,
            @Param("count") int count);
}
