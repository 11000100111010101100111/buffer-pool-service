package io.kit.hook.work.sender;


import io.kit.hook.entity.HookOneHistory;
import io.kit.hook.enums.PingResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Slf4j
public abstract class WebHookAbstractSender {
    @Setter
    @Getter
    @Value("${webhook.http.connectTimout:3000}")
    protected int connectTimout; //ms

    @Setter
    @Getter
    @Value("${webhook.http.connectionRequestTimeout:3000}")
    protected int connectionRequestTimeout; //ms

    @Setter
    @Getter
    @Value("${webhook.http.retryTimes:3}")
    protected int httpRetryTimes;

    public boolean checkURL(String url) {
        if (StringUtils.isBlank(url)) {
            log.warn("URL is invalid");
            return false;
        }
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    public abstract HookOneHistory send(String url,
                        Map<String, Object> head,
                        Map<String, Object> urlParam,
                        Object body);

    public abstract HookOneHistory send(HookOneHistory history);

    protected abstract HookOneHistory send(HookOneHistory history, Header[] headers);

    protected HookOneHistory httpControl(HookOneHistory history, int httpRetryTimes, Header[] headers) {
        int reTryTimes = Math.max(httpRetryTimes, 1);
        while (reTryTimes > 0) {
            send(history, headers);
            if (PingResult.SUCCEED.name().equals(history.getStatus())) {
                return history;
            }
            reTryTimes--;
        }
        return history;
    }
}
