package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoIntegration;
import com.kit.video.mapper.UserVideoIntegrationMapper;
import com.kit.video.service.open.UserVideoIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserVideoIntegrationServiceImpl
        extends ServiceImpl<UserVideoIntegrationMapper, UserVideoIntegration>
        implements UserVideoIntegrationService {
}
