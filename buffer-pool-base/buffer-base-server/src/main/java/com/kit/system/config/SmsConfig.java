package com.kit.system.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class SmsConfig {
    @Value("${sms.apiUrl}")
    private String apiUrl;

    @Value("${sms.appId}")
    private String appId;

    @Value("${sms.appSecret}")
    private String appSecret;

    @Value("${sms.templateId}")
    private String templateId;

    @Value("${sms.timeout}")
    private int timeout;
}
