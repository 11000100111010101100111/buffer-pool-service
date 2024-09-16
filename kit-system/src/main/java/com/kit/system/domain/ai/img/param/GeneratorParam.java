package com.kit.system.domain.ai.img.param;

import lombok.Data;

@Data
public class GeneratorParam {
    String text;
    int width;
    int height;

    public GeneratorParam withText(String text) {
        this.text = text;
        return this;
    }

    public GeneratorParam withWidth(int width) {
        this.width = width;
        return this;
    }

    public GeneratorParam withHeight(int height) {
        this.height = height;
        return this;
    }
}
