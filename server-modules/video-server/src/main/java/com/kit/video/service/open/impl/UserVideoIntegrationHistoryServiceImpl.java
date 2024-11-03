package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoIntegrationHistory;
import com.kit.video.mapper.UserVideoIntegrationHistoryMapper;
import com.kit.video.service.open.UserVideoIntegrationServiceHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserVideoIntegrationHistoryServiceImpl extends ServiceImpl<UserVideoIntegrationHistoryMapper, UserVideoIntegrationHistory>
        implements UserVideoIntegrationServiceHistory {
}
