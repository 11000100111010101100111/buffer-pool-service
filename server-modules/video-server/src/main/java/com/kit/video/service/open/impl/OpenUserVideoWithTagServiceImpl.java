package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoWithTag;
import com.kit.video.mapper.UserVideoWithTagMapper;
import com.kit.video.service.open.OpenUserVideoWithTagService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoWithTagServiceImpl extends ServiceImpl<UserVideoWithTagMapper, UserVideoWithTag> implements OpenUserVideoWithTagService {
    @Autowired
    private UserVideoWithTagMapper mapper;

    @Override
    public UserVideoWithTag selectUserVideoWithTagById(Long id) {
        return null;
    }

    @Override
    public List<UserVideoWithTag> selectUserVideoWithTagList(UserVideoWithTag userVideoWithTag) {
        return null;
    }

    @Override
    public int insertUserVideoWithTag(UserVideoWithTag userVideoWithTag) {
        return 0;
    }

    @Override
    public int updateUserVideoWithTag(UserVideoWithTag userVideoWithTag) {
        return 0;
    }

    @Override
    public int deleteUserVideoWithTagByIds(Long[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoWithTagById(Long id) {
        return 0;
    }
}
