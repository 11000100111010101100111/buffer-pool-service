package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoDataInfo;
import com.kit.video.mapper.UserVideoDataInfoMapper;
import com.kit.video.service.open.OpenUserVideoDataInfoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class OpenUserVideoDataInfoServiceImpl
        extends ServiceImpl<UserVideoDataInfoMapper, UserVideoDataInfo>
        implements OpenUserVideoDataInfoService {
    @Autowired
    UserVideoDataInfoMapper userVideoDataInfoMapper;

    @Override
    public UserVideoDataInfo selectUserVideoDataInfoById(String id) {
        return null;
    }

    @Override
    public List<UserVideoDataInfo> selectUserVideoDataInfoById(List<String> ids) {
        return baseMapper.selectUserVideoDataInfoByIds(ids);
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
    public int deleteUserVideoDataInfoByIds(String[] ids) {
        return 0;
    }

    @Override
    public int deleteUserVideoDataInfoById(String id) {
        return 0;
    }
}
