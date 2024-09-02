package io.kit.hook.impl.convert.stage;

import cn.hutool.json.JSONUtil;
import io.kit.hook.dto.WebHookAlterInfoDto;
import io.kit.hook.dto.WebHookInfoDto;
import io.kit.hook.entity.WebHookEvent;
import io.kit.hook.enums.ConstVariable;
import io.kit.hook.work.Converter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TaskAlterInfoConverter implements Converter<Map<String, Object>> {
    @Override
    public Map<String, Object> convert(Object o, WebHookInfoDto myOpenHookInfo, WebHookEvent webHookEvent) {
        Map<String, Object> event = new HashMap<>();
        event.put("action", "TaskAlter");
        event.put(ConstVariable.HOOK_ID, myOpenHookInfo.getId());
        event.put("actionTime", System.currentTimeMillis());
        event.put("title", webHookEvent.getTitle());
        event.put("content", webHookEvent.getContent());
        event.put("type", webHookEvent.getType());
        event.put("actionData", Optional.of(new HashMap<>(JSONUtil.parseObj(JSONUtil.toJsonStr(o)))).orElse(new HashMap<>()));
        return (Map<String, Object>) Optional.ofNullable(fixObject(eventData(event, myOpenHookInfo))).orElse(new HashMap<String, Object>());
    }

    @Override
    public Map<String, Object> getAllAlarmInfo(Object o) {
        try {
            if (o instanceof WebHookAlterInfoDto) {
                return Optional.of(new HashMap<>(JSONUtil.parseObj(JSONUtil.toJsonStr(o)))).orElse(new HashMap<>());
            } else if (o instanceof Map) {
                return (Map<String, Object>) o;
            } else {
                return new HashMap<>();
            }
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, Object> customTemplate(String customTemplate) {
        Map<String, Object> template;
        if (StringUtils.isNotBlank(customTemplate)) {
            try {
                template = Optional.of(new HashMap<>(JSONUtil.parseObj(customTemplate))).orElse(new HashMap<>());
            } catch (Exception e) {
                log.warn("Custom template by user created not a json map, template: {}, error: {}", customTemplate, e.getMessage());
                template = new HashMap<>();
            }
        } else {
            template = new HashMap<>();
        }
        return template;
    }
}
