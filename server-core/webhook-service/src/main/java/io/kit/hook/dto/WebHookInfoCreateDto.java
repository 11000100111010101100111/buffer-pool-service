package io.kit.hook.dto;

import io.kit.hook.enums.PingResult;
import lombok.Data;

import java.util.List;
import java.util.Locale;

@Data
public class WebHookInfoCreateDto {
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

    private List<String> hookTypes;

    /**
     * 备注
     */
    private String mark;

    private Locale locale;
}
