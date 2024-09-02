package io.kit.hook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HookOneHistory {
    private String id;
    private String hookId;
    private String url;
    private String requestHeaders;
    private String requestBody;
    private String requestParams;
    private Long requestAt;

    private String responseHeaders;
    private String responseResult;
    private String responseStatus;
    private Integer responseCode;
    private Long responseAt;


    private String eventType;
    private String type;

    private String status;

    private String historyStatus;
    private String historyMessage;
}
