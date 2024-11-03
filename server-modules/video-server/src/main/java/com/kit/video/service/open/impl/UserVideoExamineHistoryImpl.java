package com.kit.video.service.open.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kit.video.domain.UserVideoExamineHistory;
import com.kit.video.mapper.UserVideoExamineHistoryMapper;
import com.kit.video.service.open.UserVideoExamineHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserVideoExamineHistoryImpl
        extends ServiceImpl<UserVideoExamineHistoryMapper, UserVideoExamineHistory>
        implements UserVideoExamineHistoryService {
}
