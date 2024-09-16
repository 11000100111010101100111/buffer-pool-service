package com.kit.common.exception.user;

import com.kit.common.utils.StringUtils;

public class LoginTypeUnSupportException extends UserException {
    private static final long serialVersionUID = 1L;

    public LoginTypeUnSupportException(String... msg) {
        super("user.login.un.support", new String[]{StringUtils.join(msg)});
    }
}
