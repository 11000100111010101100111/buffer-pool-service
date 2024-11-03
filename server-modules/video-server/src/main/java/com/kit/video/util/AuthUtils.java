package com.kit.video.util;

import com.kit.common.core.domain.model.LoginUser;
import com.kit.common.utils.SecurityUtils;

public class AuthUtils {
    private AuthUtils() {}

    public static String getUserId() {
        try {
            final LoginUser loginUser = SecurityUtils.getLoginUser();
            return String.valueOf(loginUser.getUserId());
        } catch (Exception e) {
            return null;
        }
    }
}
