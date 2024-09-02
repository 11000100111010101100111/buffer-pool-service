package io.kit.hook.entity;

import io.kit.hook.enums.PingResult;
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
public class WebHookInfo {

    private String userId;

    /**
     * WebHook name
     */
    private String hookName;

    /**
     * WebHook URL
     */
    private String url;

    /**
     * 是否启用
     */
    private Boolean open;

    private String token;

    private String httpUser;

    private String httpPwd;

    private String customHttpHeaders;

    /**
     *
     */
    private String customTemplate;

    /**
     * ping状态
     */
    private PingResult pingResult;

    /**
     * 备注
     */
    private String mark;

    private List<String> hookTypes;

    private boolean deleted;
}
