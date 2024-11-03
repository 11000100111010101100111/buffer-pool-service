package com.kit.framework.web.service.login;

import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.domain.model.LoginBody;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.core.redis.RedisCache;
import com.kit.common.utils.DateUtils;
import com.kit.common.utils.ip.IpUtils;
import com.kit.framework.web.service.TokenService;
import com.kit.framework.web.service.login.auth.EmailAuthentication;
import com.kit.framework.web.service.login.auth.LoginInfo;
import com.kit.framework.web.service.login.auth.PhoneAuthentication;
import com.kit.framework.web.service.login.auth.provider.CoreAuthenticationManagerDelegator;
import com.kit.system.service.ISysConfigService;
import com.kit.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import java.util.Objects;

public class PhoneLoginService extends AbstractLoginService {
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

    private void checkUserIfExists(String phone) {
        SysUser sysUser = userService.selectUserByPhone(phone);
        if (Objects.isNull(sysUser)) {
            SysUser user = new SysUser();
            user.setPhonenumber(phone);
            user.setNickName(phone);
            user.setUserName(phone);
            user.setRoleIds(new Long[]{3L}); //通过注册的用户只能市游客类型
            userService.insertUser(user);
        }
    }

    public LoginInfo login(LoginBody loginBody) {
        return login(loginBody,
                authenticationManager,
                tokenService, () -> checkUserIfExists(loginBody.getUsername()));
    }

    @Override
    protected LoginUser getLoginUser(Authentication authentication) {
        if (!(authentication instanceof EmailAuthentication)) {
            return super.getLoginUser(authentication);
        }
        return (LoginUser) ((EmailAuthentication) authentication).getLoginUser();
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
        return new PhoneAuthentication(loginBody.getUsername(), loginBody.getCode());
    }
}
