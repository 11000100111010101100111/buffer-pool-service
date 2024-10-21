package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoCommentInfo;
import com.kit.video.service.open.OpenUserVideoCommentInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoCommentInfoServiceImpl implements OpenUserVideoCommentInfoService {
    @Override
    public UserVideoCommentInfo selectUserVideoCommentInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoCommentInfo> selectUserVideoCommentInfoList(UserVideoCommentInfo userVideoCommentInfo) {
        return null;
    }

    @Override
    public int insertUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo) {
        return 0;
    }

    @Override
    public int updateUserVideoCommentInfo(UserVideoCommentInfo userVideoCommentInfo) {
        return 0;
    }

    @Override
    public int deleteUserVideoCommentInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoCommentInfoById(String id) {
        return 0;
    }
}
