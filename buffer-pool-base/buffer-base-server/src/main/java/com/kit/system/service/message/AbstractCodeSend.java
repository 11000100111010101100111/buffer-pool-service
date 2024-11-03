package com.kit.system.service.message;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public abstract class AbstractCodeSend {
    @Resource(name = "redisTemplate")
    protected RedisTemplate<String, Object> redisTemplate;

    protected abstract void send(String target, String code) throws Exception;

    protected String genericCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public void sendCode(String target) {
        String code = genericCode();
        String cacheKey = String.format("%s%s", getCacheSuffix(), target);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
            Long expireTime = redisTemplate.getExpire(cacheKey, TimeUnit.SECONDS);
            if (expireTime != null && expireTime > 540) {
                throw new RuntimeException("验证码不能发送太频繁");
            }
        }
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(cacheKey, code, getCacheTimeout(), TimeUnit.MINUTES);
        try {
            send(target, code);
        } catch (Exception e) {
            redisTemplate.delete(cacheKey);
            throw new RuntimeException("验证码发送失败，请重试，" + e.getMessage());
        }
    }

    public boolean validCode(String target, String code) {
        String cacheKey = String.format("%s%s", getCacheSuffix(), target);
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

    protected abstract String getCacheSuffix();

    protected abstract int getCacheTimeout();
}
