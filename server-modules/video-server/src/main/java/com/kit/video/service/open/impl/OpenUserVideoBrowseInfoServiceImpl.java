package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoBrowseInfo;
import com.kit.video.service.open.OpenUserVideoBrowseInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoBrowseInfoServiceImpl implements OpenUserVideoBrowseInfoService {
    @Override
    public UserVideoBrowseInfo selectUserVideoBrowseInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoBrowseInfo> selectUserVideoBrowseInfoList(UserVideoBrowseInfo userVideoBrowseInfo) {
        return null;
    }

    @Override
    public int insertUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoBrowseInfo(UserVideoBrowseInfo userVideoBrowseInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoBrowseInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoBrowseInfoById(String id) {
        return 0;
    }
}
