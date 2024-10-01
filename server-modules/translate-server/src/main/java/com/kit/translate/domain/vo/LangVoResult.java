package com.kit.translate.domain.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LangVoResult {
    List<LangVo> topToHot;
    List<LangVo> topFromHot;
    Map<String, List<LangVo>> groupLang;
}
