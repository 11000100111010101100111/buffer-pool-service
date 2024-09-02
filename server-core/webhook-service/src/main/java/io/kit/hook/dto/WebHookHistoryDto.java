package io.kit.hook.dto;

import io.kit.hook.entity.HookOneHistory;
import lombok.Data;

import java.util.List;

@Data
public class WebHookHistoryDto {
    private String hookId;
    private String sendBy;
    private List<HookOneHistory> hookEvent;
    private Boolean delete;
    private Long eventCount;
}
