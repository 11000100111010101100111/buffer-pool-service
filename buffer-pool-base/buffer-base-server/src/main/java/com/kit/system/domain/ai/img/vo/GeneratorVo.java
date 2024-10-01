package com.kit.system.domain.ai.img.vo;

import lombok.Data;

@Data
public class GeneratorVo {
    String processId;
    String text;

    public GeneratorVo withProcessId(String processId) {
        this.processId = processId;
        return this;
    }

    public GeneratorVo withText(String text) {
        this.text = text;
        return this;
    }
}
