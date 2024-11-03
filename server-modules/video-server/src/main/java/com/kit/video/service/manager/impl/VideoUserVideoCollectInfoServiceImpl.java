package com.kit.video.service.manager.impl;

import java.time.LocalDateTime;
import java.util.List;
        import com.kit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kit.video.mapper.VideoUserVideoCollectInfoMapper;
import com.kit.video.domain.VideoUserVideoCollectInfo;
import com.kit.video.service.manager.IVideoUserVideoCollectInfoService;

/**
 * 视频的被收藏信息Service业务层处理
 *
 * @author xjh
 * @date 2024-10-20
 */
@Service
public class VideoUserVideoCollectInfoServiceImpl implements IVideoUserVideoCollectInfoService {
    @Autowired
    private VideoUserVideoCollectInfoMapper videoUserVideoCollectInfoMapper;

    /**
     * 查询视频的被收藏信息
     *
     * @param id 视频的被收藏信息主键
     * @return 视频的被收藏信息
     */
    @Override
    public VideoUserVideoCollectInfo selectVideoUserVideoCollectInfoById(String id) {
        return videoUserVideoCollectInfoMapper.selectVideoUserVideoCollectInfoById(id);
    }

    /**
     * 查询视频的被收藏信息列表
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 视频的被收藏信息
     */
    @Override
    public List<VideoUserVideoCollectInfo> selectVideoUserVideoCollectInfoList(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return videoUserVideoCollectInfoMapper.selectVideoUserVideoCollectInfoList(videoUserVideoCollectInfo);
    }

    /**
     * 新增视频的被收藏信息
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 结果
     */
    @Override
    public int insertVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
                videoUserVideoCollectInfo.setCreateTime(LocalDateTime.now());
            return videoUserVideoCollectInfoMapper.insertVideoUserVideoCollectInfo(videoUserVideoCollectInfo);
    }

    /**
     * 修改视频的被收藏信息
     *
     * @param videoUserVideoCollectInfo 视频的被收藏信息
     * @return 结果
     */
    @Override
    public int updateVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
                videoUserVideoCollectInfo.setUpdateTime(LocalDateTime.now());
        return videoUserVideoCollectInfoMapper.updateVideoUserVideoCollectInfo(videoUserVideoCollectInfo);
    }

    /**
     * 批量删除视频的被收藏信息
     *
     * @param ids 需要删除的视频的被收藏信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoUserVideoCollectInfoByIds(String[] ids) {
        return videoUserVideoCollectInfoMapper.deleteVideoUserVideoCollectInfoByIds(ids);
    }

    /**
     * 删除视频的被收藏信息信息
     *
     * @param id 视频的被收藏信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoUserVideoCollectInfoById(String id) {
        return videoUserVideoCollectInfoMapper.deleteVideoUserVideoCollectInfoById(id);
    }
}
