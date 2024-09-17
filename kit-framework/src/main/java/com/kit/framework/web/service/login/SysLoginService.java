package com.kit.framework.web.service.login;

import com.kit.common.constant.CacheConstants;
import com.kit.common.constant.Constants;
import com.kit.common.constant.UserConstants;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.domain.model.LoginBody;
import com.kit.common.core.redis.RedisCache;
import com.kit.common.exception.user.BlackListException;
import com.kit.common.exception.user.CaptchaException;
import com.kit.common.exception.user.CaptchaExpireException;
import com.kit.common.exception.user.UserNotExistsException;
import com.kit.common.exception.user.UserPasswordNotMatchException;
import com.kit.common.utils.DateUtils;
import com.kit.common.utils.MessageUtils;
import com.kit.common.utils.StringUtils;
import com.kit.common.utils.ip.IpUtils;
import com.kit.framework.manager.AsyncManager;
import com.kit.framework.manager.factory.AsyncFactory;
import com.kit.framework.web.service.TokenService;
import com.kit.framework.web.service.login.auth.LoginInfo;
import com.kit.framework.web.service.login.auth.provider.CoreAuthenticationManagerDelegator;
import com.kit.system.service.ISysConfigService;
import com.kit.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author xiao
 */
@Component
public class SysLoginService extends AbstractLoginService {
    @Autowired
    protected TokenService tokenService;

    @Resource
    protected CoreAuthenticationManagerDelegator authenticationManager;

    @Autowired
    protected RedisCache redisCache;

    @Autowired
    protected ISysUserService userService;

    @Autowired
    protected ISysConfigService configService;

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            redisCache.deleteObject(verifyKey);
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    @Override
    public AbstractAuthenticationToken getAuthenticationToken(LoginBody loginBody) {
        return new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
    }

    /**
     * 登录验证
     *
     * @return 结果
     */
    @Override
    public LoginInfo login(LoginBody loginBody) {
        final String username = loginBody.getUsername();
        final String password = loginBody.getPassword();
        final String code = loginBody.getCode();
        final String uuid = loginBody.getUuid();
        return login(loginBody,
                authenticationManager,
                tokenService,
                () -> validateCaptcha(username, code, uuid),
                () -> loginPreCheck(username, password));
    }
}
