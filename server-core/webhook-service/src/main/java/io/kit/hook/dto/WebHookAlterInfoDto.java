package io.kit.hook.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class WebHookAlterInfoDto {
    private String id;
    private String agentId;
    private String taskId;
    private String name;
    private String nodeId;
    private String node;
    private Integer currentValue;
    private Integer threshold;
    private Date firstOccurrenceTime;
    private Date lastOccurrenceTime;
    private Date lastNotifyTime;
    private Integer tally;
    private String summary;
    private Date recoveryTime;
    private Date closeTime;
    private String closeBy;
    private String inspectId;

    private String title;
    private String content;
    private String smsEvent;

    private String statusTxt;
    private String componentTxt;
    private String typeTxt;
    private String metricTxt;

    private transient Map<String, Object> param;
}
