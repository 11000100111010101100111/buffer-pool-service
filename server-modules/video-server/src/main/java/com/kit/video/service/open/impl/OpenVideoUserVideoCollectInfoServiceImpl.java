package com.kit.video.service.open.impl;

import com.kit.video.domain.VideoUserVideoCollectInfo;
import com.kit.video.service.open.OpenVideoUserVideoCollectInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenVideoUserVideoCollectInfoServiceImpl implements OpenVideoUserVideoCollectInfoService {
    @Override
    public VideoUserVideoCollectInfo selectVideoUserVideoCollectInfoById(String id) {
        return null;
    }

    @Override
    public List<VideoUserVideoCollectInfo> selectVideoUserVideoCollectInfoList(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return null;
    }

    @Override
    public int insertVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return 0;
    }

    @Override
    public int updateVideoUserVideoCollectInfo(VideoUserVideoCollectInfo videoUserVideoCollectInfo) {
        return 0;
    }

    @Override
    public int deleteVideoUserVideoCollectInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteVideoUserVideoCollectInfoById(String id) {
        return 0;
    }
}
