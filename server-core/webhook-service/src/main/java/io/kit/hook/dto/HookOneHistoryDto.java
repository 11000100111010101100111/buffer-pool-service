package io.kit.hook.dto;

import lombok.Data;

@Data
public class HookOneHistoryDto {
    String hookId;
    String url;
    String requestBody;
    String requestParams;
    String requestHeaders;
    Long requestAt;

    String responseHeaders;
    String responseResult;
    String responseStatus;
    Integer responseCode;
    Long responseAt;

    String eventType;
    String type;

    String status;
    String historyStatus;
    String historyMessage;
}
