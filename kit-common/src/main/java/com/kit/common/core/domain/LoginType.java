package com.kit.common.core.domain;

public enum LoginType {
    DEFAULT("default"),
    EMAIL("email"),
    GITHUB("github"),
    GOOGLE("google"),
    WECHAT("wechat"),
    CSDN("csdn");

    String type;
    LoginType(String type) {
        this.type = type;
    }

    public static LoginType type(String typeName) {
        if (null == typeName) return DEFAULT;
        for (LoginType value : values()) {
            if (value.type.equals(typeName.trim())) return value;
        }
        return DEFAULT;
    }
}
