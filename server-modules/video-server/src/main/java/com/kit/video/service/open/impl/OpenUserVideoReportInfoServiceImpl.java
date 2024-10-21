package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoReportInfo;
import com.kit.video.service.open.OpenUserVideoReportInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoReportInfoServiceImpl implements OpenUserVideoReportInfoService {
    @Override
    public UserVideoReportInfo selectUserVideoReportInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoReportInfo> selectUserVideoReportInfoList(UserVideoReportInfo userVideoReportInfo) {
        return null;
    }

    @Override
    public int insertUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoReportInfo(UserVideoReportInfo userVideoReportInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportInfoById(String id) {
        return 0;
    }
}
