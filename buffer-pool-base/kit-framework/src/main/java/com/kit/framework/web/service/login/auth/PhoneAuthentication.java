package com.kit.framework.web.service.login.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;

public class PhoneAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 550L;
    private final String phone;
    private String code;
    private UserDetails loginUser;

    public PhoneAuthentication(String phone, String code) {
        super((Collection) null);
        this.phone = phone;
        this.code = code;
        this.setAuthenticated(false);
    }

    public PhoneAuthentication(String phone, String code, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone = phone;
        this.code = code;
        super.setAuthenticated(true);
    }

    public PhoneAuthentication withLoginUser(UserDetails user) {
        this.loginUser = user;
        return this;
    }

    public Object getCredentials() {
        return this.code;
    }

    public Object getPrincipal() {
        return this.phone;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.code = null;
    }

    public UserDetails getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserDetails loginUser) {
        this.loginUser = loginUser;
    }
}
