package com.kit.generation.domain.vo;

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
