package com.kit.translate.controller;

import com.kit.common.annotation.RateLimiter;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.enums.LimitType;
import com.kit.translate.domain.param.Search;
import com.kit.translate.service.BaiDuTranslate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("百度-文本翻译")
@RestController
@RequestMapping("/open/translate")
public class TranslateController {
    @Autowired
    private BaiDuTranslate translate;

    @ApiOperation("翻译")
    @PostMapping
    @RateLimiter(key = "#ip", count = 3, time = 60, limitType = LimitType.IP)
    public AjaxResult translate(@RequestBody Search search) {
        return AjaxResult.success(translate.translate(search));
    }

    @ApiOperation("查询语言类型")
    @GetMapping("/language")
    @RateLimiter(key = "#ip", count = 60, time = 60, limitType = LimitType.IP)
    public AjaxResult queryLanguage() {
        return AjaxResult.success(translate.queryLangList());
    }
}
