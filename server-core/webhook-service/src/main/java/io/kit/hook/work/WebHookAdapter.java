package io.kit.hook.work;

import io.kit.hook.dto.WebHookInfoDto;
import io.kit.hook.entity.HookOneHistory;
import io.kit.hook.entity.WebHookEvent;
import io.kit.hook.enums.HookType;
import io.kit.hook.enums.PingResult;
import io.kit.hook.impl.WebHookHistoryServiceImpl;
import io.kit.hook.impl.WebHookServiceImpl;
import io.kit.hook.server.WebHookAdapterService;
import io.kit.hook.work.sender.WebHookPostSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Primary
public class WebHookAdapter implements WebHookAdapterService {
    @Autowired
    WebHookHistoryServiceImpl webHookHistoryService;
    @Autowired
    WebHookServiceImpl webHookService;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    WebHookPostSender webHookPostSender;

    @Override
    public void send(WebHookEvent event) {
        List<String> userId = event.getUserId();
        String type = event.getType();
        String metric = event.getMetric();
        if (StringUtils.isBlank(type) && StringUtils.isBlank(metric)) {
            return;
        }
        List<WebHookInfoDto> myOpenHookInfoList = new ArrayList<>();//@todo webHookService.findMyOpenHookInfoList(type, metric, userId);
        sendAsync(event, myOpenHookInfoList);
    }

    @Override
    public HookOneHistory sendAndSave(WebHookEvent event, WebHookInfoDto myOpenHookInfo) {
        HookOneHistory send = null;
        String hookId = myOpenHookInfo.getId();
        try {
            send = send(event, myOpenHookInfo);
            if (Objects.isNull(send)) return null;
            //@todo webHookHistoryService.pushHistory(hookId, Lists.newArrayList(send));
            WebHookInfoDto updatePingResult = new WebHookInfoDto();
            //@todo updatePingResult.setId(MongoUtils.toObjectId(hookId));
            updatePingResult.setPingResult(PingResult.valueOf(send.getStatus()));
            //@todo webHookService.updatePingResult(updatePingResult);
            return send;
        } catch (Exception e) {
            log.error("An exception occurred during the call to WebHook, hook url: {}, hook id: {}, error message: {}",
                    myOpenHookInfo.getUrl(),
                    hookId,
                    e.getMessage(),
                    e);
        }
        return send;
    }

    @Override
    public HookOneHistory send(WebHookEvent event, WebHookInfoDto myOpenHookInfo) {
        Object eventData = event.getEvent();
        String type = event.getType();
        HookType hookType = Converter.getHookBeanNameByType(type);
        if (Objects.isNull(hookType)) {
            log.warn("UnSupport {} type to send WebHook, please note", type);
            return null;
        }
        Converter<?> converter = applicationContext.getBean(hookType.getHookBeanName(), Converter.class);
        Object convert = converter.convert(eventData, myOpenHookInfo, event);
        String url = myOpenHookInfo.getUrl();
        HookOneHistory history = webHookPostSender.getHookOneHistoryByParams(url,
                converter.analyseHead(myOpenHookInfo),
                new HashMap<>(),
                convert
        );
        history.setType(event.getMetric());
        history.setEventType(hookType.getHookName());
        history.setHookId(myOpenHookInfo.getId());
        return webHookPostSender.send(history);
    }
}
