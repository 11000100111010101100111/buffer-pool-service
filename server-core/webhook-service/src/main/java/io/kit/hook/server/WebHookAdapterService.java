package io.kit.hook.server;

import cn.hutool.core.collection.CollUtil;
import io.kit.hook.dto.WebHookInfoDto;
import io.kit.hook.entity.HookOneHistory;
import io.kit.hook.entity.WebHookEvent;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public interface WebHookAdapterService {

    void send(WebHookEvent event);

    default void sendAsync(WebHookEvent event, List<WebHookInfoDto> myOpenHookInfoList) {
        if (CollUtil.isEmpty(myOpenHookInfoList)) {
            return;
        }
        CompletableFuture<Void> supplyAsync = CompletableFuture.runAsync(() -> {
        });
        myOpenHookInfoList.stream()
                .filter(Objects::nonNull)
                .forEach(webHookInfo -> supplyAsync.thenRunAsync(() -> sendAndSave(event, webHookInfo)));
    }

    HookOneHistory sendAndSave(WebHookEvent event, WebHookInfoDto myOpenHookInfo);

    HookOneHistory send(WebHookEvent event, WebHookInfoDto myOpenHookInfo);
}
