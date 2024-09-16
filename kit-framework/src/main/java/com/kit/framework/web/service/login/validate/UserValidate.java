package com.kit.framework.web.service.login.validate;

import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.exception.user.LoginTypeUnSupportException;
import com.kit.common.exception.user.UserPasswordRetryLimitExceedException;
import com.kit.common.utils.spring.SpringUtils;
import com.kit.framework.security.context.AuthenticationContextHolder;
import com.kit.framework.web.service.login.LoginType;
import org.springframework.beans.BeansException;
import org.springframework.security.core.Authentication;

public interface UserValidate {

    void validate(SysUser user);

    default void checkRetryLimit(Integer retryCount, int maxRetryCount, int lockTime) {
        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }
    }


    public static void autoValidate(SysUser user) {
        Authentication authentication = AuthenticationContextHolder.getContext();
        LoginType byAuthClass = LoginType.getByAuthClass(authentication.getClass());
        try {
            UserValidate validate = SpringUtils.getBean(byAuthClass.getValidateClass());
            validate.validate(user);
        } catch (BeansException e) {
            throw new LoginTypeUnSupportException(byAuthClass.getType());
        }
    }
}
