package com.kit.system.config;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsClient {
    @Autowired
    SmsConfig config;

    @Bean
    public ZhenziSmsClient zhenziSmsClient() {
        return new ZhenziSmsClient(config.getApiUrl(), config.getAppId(), config.getAppSecret());
    }
}
