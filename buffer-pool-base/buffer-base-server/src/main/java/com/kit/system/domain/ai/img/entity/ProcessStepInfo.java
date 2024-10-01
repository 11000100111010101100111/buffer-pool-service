package com.kit.system.domain.ai.img.entity;

import com.kit.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessStepInfo extends BaseEntity {

    public ProcessStepInfo() {

    }

    public ProcessStepInfo(String processId, String type, String title, String percentage, String mark, String currentStep, String totalSteps, String estimatedTime, String elapsedTime, Long time) {
        this.processId = processId;
        this.type = type;
        this.title = title;
        this.percentage = percentage;
        this.mark = mark;
        this.currentStep = currentStep;
        this.totalSteps = totalSteps;
        this.estimatedTime = estimatedTime;
        this.elapsedTime = elapsedTime;
        this.time = time;
    }

    String processId;

    @ApiParam("处理结果，0：等待处理，1：处理中，2：处理完成，9：处理失败")
    String type;

    String title;

    String percentage;

    String mark;

    String currentStep;

    String totalSteps;

    String estimatedTime;

    String elapsedTime;

    Long time;
}
