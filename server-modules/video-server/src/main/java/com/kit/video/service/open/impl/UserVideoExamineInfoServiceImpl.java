package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoExamineInfo;
import com.kit.video.mapper.UserVideoExamineMapper;
import com.kit.video.service.open.UserVideoExamineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserVideoExamineInfoServiceImpl
        extends ServiceImpl<UserVideoExamineMapper, UserVideoExamineInfo>
        implements UserVideoExamineInfoService {
}
