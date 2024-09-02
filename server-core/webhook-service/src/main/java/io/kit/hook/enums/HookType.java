package io.kit.hook.enums;

import io.kit.hook.impl.convert.stage.TaskAlterInfoConverter;
import io.kit.hook.impl.convert.stage.PingWebHookConverter;

public enum HookType {
    ALTER("task.alter", TaskAlterInfoConverter.class.getName()),
    NORMAL_PING("webhook.ping", PingWebHookConverter.class.getName()),
    ;
    String hookName;
    String hookBeanName;

    HookType(String hookName, String hookBeanName) {
        this.hookName = hookName;
        this.hookBeanName = hookBeanName;
    }

    public String getHookName() {
        return hookName;
    }

    public String getHookBeanName() {
        return hookBeanName;
    }
}
