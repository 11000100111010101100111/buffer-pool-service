package com.kit.system.mapper;

import com.kit.system.domain.translate.entity.TranslateInfo;
import com.kit.system.domain.translate.param.IncreaseHot;
import com.kit.system.domain.translate.vo.LangVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslateMapper {
    public TranslateInfo queryTranslateInfo(String hash);

    public void upsertTranslateInfo(TranslateInfo info);

    public List<LangVo> queryLang();

    public void increaseHeat(IncreaseHot hot);
}
