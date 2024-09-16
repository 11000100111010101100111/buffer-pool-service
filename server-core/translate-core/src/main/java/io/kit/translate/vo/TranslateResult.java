package io.kit.translate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kit.translate.ErrorMessage;

public class TranslateResult {
    private String from;
    private String to;

    @JsonProperty("trans_result")
    private TranslateResultEntry transResult;

    @JsonProperty("error_code")
    private String errorCode;

    private String errorMessage;

    public static TranslateResult result() {
        return new TranslateResult();
    }

    public TranslateResult from(String from) {
        this.from = from;
        return this;
    }

    public TranslateResult to(String to) {
        this.to = to;
        return this;
    }

    public TranslateResult translateResult(String src, String dst) {
        this.transResult = TranslateResultEntry.entry(src, dst);
        return this;
    }

    public TranslateResult errorCode(String code) {
        this.errorCode = errorCode;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TranslateResultEntry getTransResult() {
        return transResult;
    }

    public void setTransResult(TranslateResultEntry transResult) {
        this.transResult = transResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = ErrorMessage.byCode(errorCode);
    }
}
