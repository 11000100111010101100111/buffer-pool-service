package io.kit.hook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gavin'Xiao
 * @date 2024/5/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebHookHistory {
    private String hookId;
    private List<HookOneHistory> hookEvent;
}
