package com.kit.common.exception.user;

import com.kit.common.utils.StringUtils;

/**
 * 用户不存在异常类
 *
 * @author xiao
 */
public class UserNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
