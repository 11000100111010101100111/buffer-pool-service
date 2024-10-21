package com.kit.video.service.open.impl;

import com.kit.video.domain.UserVideoTag;
import com.kit.video.service.open.OpenUserVideoTagService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoTagServiceImpl implements OpenUserVideoTagService {
    @Override
    public UserVideoTag selectUserVideoTagById(String id) {
        return null;
    }

    @Override
    public List<UserVideoTag> selectUserVideoTagList(UserVideoTag userVideoTag) {
        return null;
    }

    @Override
    public int insertUserVideoTag(UserVideoTag userVideoTag) {
        return 0;
    }

    @Override
    public int updateUserVideoTag(UserVideoTag userVideoTag) {
        return 0;
    }

    @Override
    public int deleteUserVideoTagByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoTagById(String id) {
        return 0;
    }
}
