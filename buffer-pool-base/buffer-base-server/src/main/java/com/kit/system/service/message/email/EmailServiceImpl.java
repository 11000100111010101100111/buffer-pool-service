package com.kit.system.service.message.email;

import io.kit.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailServiceImpl {
    @Autowired
    EmailService emailService;

    @Value("${mail.cacheMin:10}")
    private int cacheMin;
    @Value("${mail.cacheSuffix:email_code_key_}")
    private String cacheSuffix;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public void sendCode(String email) {
        int code = (int) (Math.random() * 900000) + 100000;

        String cacheKey = String.format("%s%s", cacheSuffix, email);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
            Long expireTime = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
            if (expireTime != null && expireTime > 540) {
                throw new RuntimeException("验证码不能发送太频繁");
            }
        }
        EmailService.SendParam param = new EmailService.SendParam();
        param.setCode(String.valueOf(code));
        param.setEmail(email);
        param.setSubject("（登陆）电子邮件验证码： " + code);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(cacheKey, code, cacheMin, TimeUnit.MINUTES);
        try {
            emailService.sendHtmlEmail(param);
        } catch (Exception e) {
            redisTemplate.delete(cacheKey);
            throw new RuntimeException("验证码发送失败，请重试，" + e.getMessage());
        }
    }

    public boolean validCode(String email, String code) {
        String cacheKey = String.format("%s%s", cacheSuffix, email);
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
            throw new RuntimeException("验证码已经失效");
        }
        Long expireTime = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
        if (expireTime == null || expireTime <= 0) {
            throw new RuntimeException("验证码已经失效");
        }
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Object key = operations.get(cacheKey);
        if (String.valueOf(key).equals(code)) {
            redisTemplate.delete(cacheKey);
            return true;
        }
        return false;
    }
}
