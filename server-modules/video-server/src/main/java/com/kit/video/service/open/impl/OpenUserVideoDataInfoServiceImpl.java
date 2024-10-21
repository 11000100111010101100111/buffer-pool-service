package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.service.open.OpenUserVideoDataInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoDataInfoServiceImpl implements OpenUserVideoDataInfoService {
    @Override
    public UserVideoDataInfo selectUserVideoDataInfoById(Long id) {
        return null;
    }

    @Override
    public List<UserVideoDataInfo> selectUserVideoDataInfoList(UserVideoDataInfo userVideoDataInfo) {
        return null;
    }

    @Override
    public int insertUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoDataInfo(UserVideoDataInfo userVideoDataInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoDataInfoByIds(Long[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoDataInfoById(Long id) {
        return 0;
    }
}
