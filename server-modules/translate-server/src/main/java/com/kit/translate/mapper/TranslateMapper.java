package com.kit.translate.mapper;

import com.kit.translate.domain.entity.TranslateInfo;
import com.kit.translate.domain.param.IncreaseHot;
import com.kit.translate.domain.vo.LangVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslateMapper {
    public TranslateInfo queryTranslateInfo(String hash);

    public void upsertTranslateInfo(TranslateInfo info);

    public List<LangVo> queryLang();

    public void increaseHeat(IncreaseHot hot);
}
