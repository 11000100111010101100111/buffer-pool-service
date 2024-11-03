package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoReportType;
import com.kit.video.service.open.OpenUserVideoReportTypeService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoReportTypeServiceImpl implements OpenUserVideoReportTypeService {
    @Override
    public UserVideoReportType selectUserVideoReportTypeById(String id) {
        return null;
    }

    @Override
    public List<UserVideoReportType> selectUserVideoReportTypeList(UserVideoReportType userVideoReportType) {
        return null;
    }

    @Override
    public int insertUserVideoReportType(UserVideoReportType userVideoReportType) {
        return 0;
    }

    @Override
    public int updateUserVideoReportType(UserVideoReportType userVideoReportType) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportTypeByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoReportTypeById(String id) {
        return 0;
    }
}
