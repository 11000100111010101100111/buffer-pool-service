package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoMetadataInfo;
import com.kit.video.service.open.OpenUserVideoMetadataInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoMetadataInfoServiceImpl implements OpenUserVideoMetadataInfoService {
    @Override
    public UserVideoMetadataInfo selectUserVideoMetadataInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoMetadataInfo> selectUserVideoMetadataInfoList(UserVideoMetadataInfo userVideoMetadataInfo) {
        return null;
    }

    @Override
    public int insertUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoMetadataInfo(UserVideoMetadataInfo userVideoMetadataInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoMetadataInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoMetadataInfoById(String id) {
        return 0;
    }
}
