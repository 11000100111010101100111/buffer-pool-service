package io.kit.translate.vo;

public class TranslateResultEntry {
    private String src;
    private String dst;

    public static TranslateResultEntry entry(String src, String dst) {
        return new TranslateResultEntry().src(src).dst(dst);
    }

    public TranslateResultEntry src(String src) {
        this.src = src;
        return this;
    }

    public TranslateResultEntry dst(String dst) {
        this.dst = dst;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
