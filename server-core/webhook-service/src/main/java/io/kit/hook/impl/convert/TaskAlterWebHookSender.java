package io.kit.hook.impl.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskAlterWebHookSender {
//    WebHookAdapter webHookAdapter;
//    AlarmServiceImpl alarmService;
//    private SettingsService settingsService;
//
//    public void send(AlarmInfo info) {
//        if (settingsService.isCloud()) {
//            log.info("Cloud not support WebHook, cancel to send message");
//            return;
//        }
//        if (null == info) {
//            return;
//        }
//        Map<String, String> taskTitleAndContent = alarmService.getTaskTitleAndContent(info);
//        WebHookAlterInfoDto dto = new WebHookAlterInfoDto();
//        BeanUtils.copyProperties(info, dto);
//        String content = Optional.ofNullable(taskTitleAndContent.get("content")).orElse("");
//        String title = Optional.ofNullable(taskTitleAndContent.get("title")).orElse("");
//        dto.setTitle(title);
//        dto.setContent(content);
//        dto.setSmsEvent(Optional.ofNullable(taskTitleAndContent.get("smsEvent")).orElse(""));
//        dto.copyTxt();
//        dto.setId(info.getId().toHexString());
//        WebHookEvent event = WebHookEvent.of();
//        event.withEvent(dto)
//                .withContent(content)
//                .withTitle(title)
//                .withUserId(Lists.newArrayList(info.getUserId()));
//        Optional.ofNullable(info.getType()).ifPresent(t -> event.withMetric(t.name()));
//        Optional.ofNullable(info.getMetric()).ifPresent(m -> event.withMetric(m.name()));
//        webHookAdapter.send(event);
//    }
}
