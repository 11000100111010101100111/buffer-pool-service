package com.kit.framework.web.service.login;

import com.kit.framework.web.service.login.auth.EmailAuthentication;
import com.kit.framework.web.service.login.validate.UserEmailValidate;
import com.kit.framework.web.service.login.validate.UserPasswordValidate;
import com.kit.framework.web.service.login.validate.UserValidate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public enum LoginType {
    DEFAULT("default", SysLoginService.class, UsernamePasswordAuthenticationToken.class, UserPasswordValidate.class),
    EMAIL("email", EmailLoginService.class, EmailAuthentication.class, UserEmailValidate.class),
//    GITHUB("github"),
//    GOOGLE("google"),
//    WECHAT("wechat"),
//    CSDN("csdn")
    ;

    String type;
    Class<? extends AbstractLoginService> serviceClass;
    Class<? extends AbstractAuthenticationToken> authClass;
    Class<? extends UserValidate> validateClass;

    LoginType(String type, Class<? extends AbstractLoginService> serviceClass, Class<? extends AbstractAuthenticationToken> authClass, Class<? extends UserValidate> validateClass) {
        this.serviceClass = serviceClass;
        this.authClass = authClass;
        this.type = type;
        this.validateClass = validateClass;
    }

    public static LoginType type(String typeName) {
        if (null == typeName) return DEFAULT;
        for (LoginType value : values()) {
            if (value.type.equals(typeName.trim())) return value;
        }
        return DEFAULT;
    }

    public static LoginType getByAuthClass(Class<? extends Authentication> authClass) {
        if (null == authClass) return DEFAULT;
        for (LoginType value : values()) {
            if (value.authClass.getName().equals(authClass.getName())) return value;
        }
        return DEFAULT;
    }

    public boolean isType(String type) {
        return this.type.equals(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class<? extends AbstractAuthenticationToken> getAuthClass() {
        return authClass;
    }

    public void setAuthClass(Class<? extends AbstractAuthenticationToken> authClass) {
        this.authClass = authClass;
    }

    public Class<? extends UserValidate> getValidateClass() {
        return validateClass;
    }

    public void setValidateClass(Class<? extends UserValidate> validateClass) {
        this.validateClass = validateClass;
    }

    public Class<? extends AbstractLoginService> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<? extends AbstractLoginService> serviceClass) {
        this.serviceClass = serviceClass;
    }
}
