package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoForwardInfo;
import com.kit.video.service.open.OpenUserVideoForwardInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoForwardInfoServiceImpl implements OpenUserVideoForwardInfoService {
    @Override
    public UserVideoForwardInfo selectUserVideoForwardInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoForwardInfo> selectUserVideoForwardInfoList(UserVideoForwardInfo userVideoForwardInfo) {
        return null;
    }

    @Override
    public int insertUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoForwardInfo(UserVideoForwardInfo userVideoForwardInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoForwardInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoForwardInfoById(String id) {
        return 0;
    }
}
