package com.kit.framework.web.service.login.auth.provider;

import com.kit.framework.web.service.UserDetailsServiceImpl;
import com.kit.framework.web.service.login.auth.EmailAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class EmailAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthentication authenticationEmail = (EmailAuthentication) authentication;
        String email = (String) authenticationEmail.getPrincipal();
        String code = (String) authenticationEmail.getCredentials();

//         如果验证码一致，从数据库中读取该手机号对应的用户信息
        UserDetails loadedUser = userDetailsService.loadUserByUsername(email);

        if (loadedUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            return new EmailAuthentication(email, code, loadedUser.getAuthorities()).withLoginUser(loadedUser);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailAuthentication.class.isAssignableFrom(aClass);
    }

    public EmailAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}