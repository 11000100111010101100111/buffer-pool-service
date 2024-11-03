package com.kit.system.service.message.email;

import com.kit.system.service.message.AbstractCodeSend;
import io.kit.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl extends AbstractCodeSend {
    @Autowired
    EmailService emailService;

    @Value("${mail.cacheMin:10}")
    private int cacheMin;
    @Value("${mail.cacheSuffix:email_code_key_}")
    private String cacheSuffix;

    @Override
    public void send(String target, String code) throws Exception {
        EmailService.SendParam param = new EmailService.SendParam();
        param.setCode(String.valueOf(code));
        param.setEmail(target);
        param.setSubject("（登陆）电子邮件验证码： " + code);
        emailService.sendHtmlEmail(param);
    }

    @Override
    protected String getCacheSuffix() {
        return cacheSuffix;
    }

    @Override
    protected int getCacheTimeout() {
        return cacheMin;
    }
}
