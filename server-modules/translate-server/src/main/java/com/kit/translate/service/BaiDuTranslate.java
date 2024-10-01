package com.kit.translate.service;

import com.kit.translate.domain.entity.TranslateInfo;
import com.kit.translate.domain.param.IncreaseHot;
import com.kit.translate.domain.param.Search;
import com.kit.translate.domain.vo.LangVo;
import com.kit.translate.domain.vo.LangVoResult;
import com.kit.translate.mapper.TranslateMapper;
import io.kit.translate.MD5;
import io.kit.translate.TranslateApi;
import io.kit.translate.param.TranslateParam;
import io.kit.translate.vo.TranslateResult;
import io.kit.translate.vo.TranslateResultEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BaiDuTranslate {
    @Autowired
    TranslateApi translateApi;

    @Value("${translate.baidu.redisKeySuffix:translate_}")
    private String keySuffix;

    @Value("${translate.baidu.expire:30}")
    private int expire;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TranslateMapper translateMapper;

    @Resource(name = "calculateLangHeatExecutor")
    private Executor calculateLangHeatExecutor;

    public LangVoResult queryLangList() {
        LangVoResult result = new LangVoResult();
        //分别选出热度前三的from已经to
        List<LangVo> langVos = Optional.ofNullable(translateMapper.queryLang()).orElse(new ArrayList<>());
        Map<String, List<LangVo>> sortedMap = new TreeMap<>(String::compareTo);
        List<LangVo> toHots = langVos.stream().sorted(Comparator.comparingInt(LangVo::getToHot).reversed()).limit(3).collect(Collectors.toList());
        List<LangVo> fromHots = langVos.stream().sorted(Comparator.comparingInt(LangVo::getFromHot).reversed()).limit(3).collect(Collectors.toList());
        result.setTopToHot(toHots);
        result.setTopFromHot(fromHots);
        sortedMap.putAll(langVos.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(LangVo::getSort)));
        result.setGroupLang(new LinkedHashMap<>(sortedMap));
        return result;
    }


    public TranslateResult translate(Search search) {
        calculateLangHeat(search);
        String hash = String.format("hash[%s]_from[%s]_to[%s]", MD5.md5(search.getQuery()), search.getFrom(), search.getTo());

        boolean cached = keyFromRedis(hash);
        // 判断redis中是否包含，不包含则去Mysql查询，包含则直接查Mysql返回结果
        if (cached) {
            TranslateResult fromMysql = findFromMysql(hash);
            if (Objects.nonNull(fromMysql)) {
                updateToRedis(hash);
                return fromMysql;
            }
        }

        // redis不包含，mysql没有结果，查API，更新mysql,redis
        TranslateResult fromApi = findFromApi(search);
        try {
            updateToMysql(hash, fromApi);
            updateToRedis(hash);
        } catch (Exception e) {
            log.warn("Unable to update translate result to mysql or redis, key: {}, message: {}", hash, e.getMessage());
        }
        return fromApi;
    }

    private TranslateResult findFromApi(Search search) {
        TranslateParam param = new TranslateParam();
        param.setP(search.getQuery());
        param.setTo(search.getTo());
        param.setFrom(search.getFrom());
        return translateApi.translate(param);
    }


    private TranslateResult findFromMysql(String hash) {
        TranslateInfo translateInfo = translateMapper.queryTranslateInfo(hash);
        if (Objects.nonNull(translateInfo)) {
            return TranslateResult.result()
                    .from(translateInfo.getFrom())
                    .to(translateInfo.getTo())
                    .translateResult(translateInfo.getTransResultSrc(), translateInfo.getTransResultDst());
        }
        return null;
    }

    private void updateToMysql(String hash, TranslateResult fromApi) {
        if (Objects.nonNull(fromApi) && Objects.nonNull(fromApi.getFirstTransResult())) {
            TranslateResultEntry firstTransResult = fromApi.getFirstTransResult();
            translateMapper.upsertTranslateInfo(new TranslateInfo()
                    .to(fromApi.getTo())
                    .hash(hash)
                    .transResultDst(firstTransResult.getDst())
                    .transResultSrc(firstTransResult.getSrc())
                    .from(fromApi.getFrom()));
        }
    }

    private void updateToRedis(String hash) {
        redisTemplate.opsForValue().set(hash, true, this.expire, TimeUnit.DAYS);
    }

    private boolean keyFromRedis(String hashCode) {
        final String redisKey = genericRedisKey(hashCode);
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
            return false;
        }
        Long expireTime = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);
        if (expireTime != null && expireTime > 0) {
            return true;
        }
        return false;
    }

    /**
     * 异步计算语言的使用热度
     */
    private void calculateLangHeat(Search search) {
        calculateLangHeatExecutor.execute(() -> {
            try {
                IncreaseHot hot = new IncreaseHot();
                hot.setFromCode(search.getFrom());
                hot.setToCode(search.getTo());
                translateMapper.increaseHeat(hot);
            } catch (Exception e) {
                log.warn("Can not to calculate's use hot, from: {}, to: {}, error: {}", search.getFrom(), search.getTo(), e.getMessage());
            }
        });
    }


    private String genericRedisKey(String hashCode) {
        return String.format("%s_%s", this.keySuffix, hashCode);
    }
}
