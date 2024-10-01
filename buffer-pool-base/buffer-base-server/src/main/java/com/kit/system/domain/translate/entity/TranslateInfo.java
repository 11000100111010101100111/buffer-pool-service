package com.kit.system.domain.translate.entity;

import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class TranslateInfo extends BaseEntity {
    int id;
    String hash;
    String from;
    String to;
    String transResultSrc;
    String transResultDst;

    public TranslateInfo hash(String hash) {
        this.hash = hash;
        return this;
    }

    public TranslateInfo from(String from) {
        this.from = from;
        return this;
    }

    public TranslateInfo to(String to) {
        this.to = to;
        return this;
    }

    public TranslateInfo transResultSrc(String src) {
        this.transResultSrc = src;
        return this;
    }

    public TranslateInfo transResultDst(String dst) {
        this.transResultDst = dst;
        return this;
    }
}
