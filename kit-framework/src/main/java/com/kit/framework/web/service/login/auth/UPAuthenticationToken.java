package com.kit.framework.web.service.login.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UPAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public UPAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UPAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
