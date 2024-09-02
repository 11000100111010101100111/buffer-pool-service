package io.kit.hook.impl.convert.stage;

import cn.hutool.json.JSONUtil;
import io.kit.hook.dto.WebHookInfoDto;
import io.kit.hook.entity.WebHookEvent;
import io.kit.hook.work.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PingWebHookConverter implements Converter<Map<String, Object>> {
    public static final String PING_TEMPLATE = "{" +
            "    \"action\": \"Ping\",\n" +
            "    \"hookId\": \"WebHook ID\",\n" +
            "    \"actionTime\": \"WebHook发生时间\",\n" +
            "    \"title\": \"标题文本\",\n" +
            "    \"type\": \"webhook.ping\",\n" +
            "    \"content\": \"事件内容摘要\",\n" +
            "    \"actionData\": {\n" +
            "        \"message\": \"这是一条Ping事件\",\n" +
            "        \"type\": \"webhook.ping,\n" +
            "        \"typeTxt\": \"Ping事件\"\n" +
            "    }" +
            "}";

    @Override
    public Map<String, Object> convert(Object o, WebHookInfoDto myOpenHookInfo, WebHookEvent event) {
        Map<String, Object> eventData = eventData(event, myOpenHookInfo);
        eventData.put("action", "Ping");
        return (Map<String, Object>) Optional.ofNullable(fixObject(eventData)).orElse(new HashMap<String, Object>());
    }

    @Override
    public Map<String, Object> customTemplate(String customTemplate) {
        try {
            return Optional.of(new HashMap<>(JSONUtil.parseObj(customTemplate))).orElse(new HashMap<>());
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, Object> getAllAlarmInfo(Object o) {
        return new HashMap<>(JSONUtil.parseObj(PING_TEMPLATE));
    }
}
