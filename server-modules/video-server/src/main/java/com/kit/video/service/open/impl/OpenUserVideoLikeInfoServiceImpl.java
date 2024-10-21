package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoLikeInfo;
import com.kit.video.service.open.OpenUserVideoLikeInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoLikeInfoServiceImpl
        implements OpenUserVideoLikeInfoService {
    @Override
    public UserVideoLikeInfo selectUserVideoLikeInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoLikeInfo> selectUserVideoLikeInfoList(UserVideoLikeInfo userVideoLikeInfo) {
        return null;
    }

    @Override
    public int insertUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoLikeInfo(UserVideoLikeInfo userVideoLikeInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoLikeInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoLikeInfoById(String id) {
        return 0;
    }
}
