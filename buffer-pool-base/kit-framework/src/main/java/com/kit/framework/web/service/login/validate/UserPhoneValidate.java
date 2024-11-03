package com.kit.framework.web.service.login.validate;

import com.kit.common.constant.CacheConstants;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.redis.RedisCache;
import com.kit.framework.security.context.AuthenticationContextHolder;
import com.kit.system.service.message.phone.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserPhoneValidate implements UserValidate  {
    @Autowired
    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    @Autowired
    private PhoneServiceImpl phoneService;

    /**
     * 登录账户错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(SysUser user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String code = usernamePasswordAuthenticationToken.getCredentials().toString();
        Integer retryCount = Optional.ofNullable((Integer) redisCache.getCacheObject(getCacheKey(username))).orElse(0);

        checkRetryLimit(retryCount, maxRetryCount, lockTime);

        // 验证码校验
        try {
            phoneService.validCode(username, code);
        } catch (Exception e) {
            retryCount = retryCount + 1;
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw e;
        }
        clearLoginRecordCache(username);
    }

    public void clearLoginRecordCache(String loginName) {
        if (redisCache.hasKey(getCacheKey(loginName))) {
            redisCache.deleteObject(getCacheKey(loginName));
        }
    }
}
