package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoReportImgInfo;
import com.kit.video.service.open.OpenUserVideoReportImgInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoReportImgInfoServiceImpl implements OpenUserVideoReportImgInfoService {
    @Override
    public UserVideoReportImgInfo selectUserVideoReportImgInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoReportImgInfo> selectUserVideoReportImgInfoList(UserVideoReportImgInfo userVideoReportImgInfo) {
        return null;
    }

    @Override
    public int insertUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoReportImgInfo(UserVideoReportImgInfo userVideoReportImgInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportImgInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportImgInfoById(String id) {
        return 0;
    }
}
