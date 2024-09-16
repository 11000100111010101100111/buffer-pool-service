package io.kit.translate.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.kit.translate.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class TranslateResult {
    private String from;
    private String to;

    //{"from":"zh","to":"en","trans_result":[{"src":"\u5c0f\u6865\u6d41\u6c34\u4eba\u5bb6","dst":"Xiaoqiao Liushui Family"}]}
    @JsonProperty("trans_result")
    private List<TranslateResultEntry> transResult;

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
        if (null == this.transResult) {
            this.transResult = new ArrayList<>();
        }
        this.transResult.add(TranslateResultEntry.entry(src, dst));
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

    public List<TranslateResultEntry> getTransResult() {
        return transResult;
    }
    public TranslateResultEntry getFirstTransResult() {
        return null == transResult || transResult.isEmpty() ? null : transResult.get(0);
    }

    public void setTransResult(List<TranslateResultEntry> transResult) {
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
