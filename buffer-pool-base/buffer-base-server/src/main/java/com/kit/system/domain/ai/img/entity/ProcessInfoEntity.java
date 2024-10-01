package com.kit.system.domain.ai.img.entity;

import com.kit.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessInfoEntity extends BaseEntity {
    public ProcessInfoEntity() {
    }

    public ProcessInfoEntity(String ipOrUserId, String processId, String result, String resultMessage, String text, int width, int height, String createBy, String imgUrl) {
        this.ipOrUserId = ipOrUserId;
        this.processId = processId;
        this.result = result;
        this.resultMessage = resultMessage;
        this.text = text;
        this.width = width;
        this.height = height;
        this.createBy = createBy;
        this.imgUrl = imgUrl;
    }

    String ipOrUserId;
    String processId;
    String result;
    String resultMessage;
    String text;
    int width;
    int height;
    String createBy;
    String imgUrl;
}
