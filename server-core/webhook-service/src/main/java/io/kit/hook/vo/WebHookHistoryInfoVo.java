package io.kit.hook.vo;

import lombok.Data;

@Data
public class WebHookHistoryInfoVo {
    String id;
    String hookId;
    String url;
    String eventType;
    String type;
    String status;

    String requestId;
    String requestHeaders;
    String requestBody;
    String requestParams;
    Long requestAt;

    String responseHeaders;
    String responseResult;
    String responseStatus;
    Integer responseCode;
    Long responseAt;

    String historyStatus;
    String historyMessage;

    Long createAt;
}
