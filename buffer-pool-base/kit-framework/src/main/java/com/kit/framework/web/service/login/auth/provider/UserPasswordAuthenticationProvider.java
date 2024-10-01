package com.kit.framework.web.service.login.auth.provider;

import com.kit.framework.web.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserPasswordAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationEmail = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authenticationEmail.getPrincipal();
        String pwd = (String) authenticationEmail.getCredentials();

        UserDetails loadedUser = userDetailsService.loadUserByUsername(username);

        if (loadedUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            return new UsernamePasswordAuthenticationToken(loadedUser, pwd, loadedUser.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserPasswordAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
