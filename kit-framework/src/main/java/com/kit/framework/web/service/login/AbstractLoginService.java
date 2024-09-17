package com.kit.framework.web.service.login;

import com.kit.common.constant.Constants;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.domain.model.LoginBody;
import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.exception.ServiceException;
import com.kit.common.exception.user.UserPasswordNotMatchException;
import com.kit.common.utils.MessageUtils;
import com.kit.framework.manager.AsyncManager;
import com.kit.framework.manager.factory.AsyncFactory;
import com.kit.framework.security.context.AuthenticationContextHolder;
import com.kit.framework.web.service.TokenService;
import com.kit.framework.web.service.login.auth.LoginInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import java.util.Objects;

public abstract class AbstractLoginService {

    protected LoginInfo login(LoginBody loginBody,
                              AuthenticationManager authenticationManager,
                              TokenService tokenService,
                              Runnable... checkBefore) {
        String username = loginBody.getUsername();
        if (Objects.nonNull(checkBefore) && checkBefore.length > 0) {
            for (Runnable runnable : checkBefore) {
                runnable.run();
            }
        }
        // 用户验证
        Authentication authentication = null;
        try {
            AbstractAuthenticationToken authenticationToken = getAuthenticationToken(loginBody);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        LoginUser loginUser = getLoginUser(authentication);
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        SysUser user = loginUser.getUser();
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAvatar(user.getAvatar());
        loginInfo.setNickName(user.getNickName());
        loginInfo.setToken(tokenService.createToken(loginUser));
        return loginInfo;
    }

    protected LoginUser getLoginUser(Authentication authentication) {
        return (LoginUser) authentication.getPrincipal();
    }

    public abstract LoginInfo login(LoginBody loginBody);

    public abstract void recordLoginInfo(Long userId);

    public abstract AbstractAuthenticationToken getAuthenticationToken(LoginBody loginBody);


}
