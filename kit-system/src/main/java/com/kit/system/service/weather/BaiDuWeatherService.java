package com.kit.system.service.weather;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.kit.system.domain.weather.baidu.emun.CityType;
import com.kit.system.domain.weather.baidu.entity.CityInfo;
import com.kit.system.domain.weather.baidu.param.QueryCityLocationParam;
import com.kit.system.domain.weather.baidu.vo.QueryCityLocationCountVo;
import com.kit.system.domain.weather.baidu.vo.WeatherInfoVo;
import com.kit.system.mapper.BaiDuWeatherMapper;
import io.kit.map.baidu.BaiDuWeatherAPI;
import io.kit.map.baidu.vo.BaiDuWeatherResult;
import io.kit.map.baidu.vo.WeatherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BaiDuWeatherService {
    @Value("${map.baidu.expire:24}")
    int expire;
    @Autowired
    private BaiDuWeatherAPI weatherAPI;

    @Autowired
    private BaiDuWeatherMapper baiDuWeatherMapper;

    @Resource(name = "weatherQueryExecutor")
    private Executor weatherQueryExecutor;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static final AtomicInteger countDownLatch = new AtomicInteger(25);

    public List<CityInfo> queryCityLocation(QueryCityLocationParam param) {
        QueryCityLocationCountVo queryCityLocationCountVo = baiDuWeatherMapper.queryCityLocationCount(param);
        if (null == queryCityLocationCountVo) return new ArrayList<>();
        List<String> queryTypes = null;
        ////省0（直辖市1），省会2，市3，县4（区5）
        if (queryCityLocationCountVo.getProvinceCount() > 5) {
            if (queryCityLocationCountVo.getCityCount() > 50) {
                queryTypes = new ArrayList<>();
                if (queryCityLocationCountVo.getCountyCount() <= 500) {
                    queryTypes.add(CityType.COUNTRY.getType());
                    queryTypes.add(CityType.DISTRICT.getType());
                } else {
                    queryTypes.add(CityType.PROVINCE.getType());
                    queryTypes.add(CityType.MUNICIPALITY.getType());
                    queryTypes.add(CityType.PROVINCIAL_CAPITAL.getType());
                    queryTypes.add(CityType.CITY.getType());
                }
            }
        }
        param.setQueryTypes(queryTypes);
        List<CityInfo> cityInfos = baiDuWeatherMapper.cityLocationInfo(param);
        findAndSaveWeatherAsync(cityInfos.stream().map(CityInfo::getAdCode).collect(Collectors.toSet()));
        return cityInfos;
    }

    @Async("weatherQueryExecutor")
    public void findAndSaveWeatherAsync(Set<String> adCodes) {
        CompletableFuture<Void> supplyAsync = CompletableFuture.runAsync(() -> {}, weatherQueryExecutor);
        adCodes.forEach(adCode ->
            supplyAsync.thenAcceptAsync((action) -> {
                final String redisKey = genericRedisKey(adCode);
                //判断redis是否存在当天的天气，存在则返回，不存在异步获取
                if (Boolean.TRUE.equals(redisTemplate.hasKey(redisKey))) {
                    Long expireTime = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);
                    if (expireTime != null && expireTime > 0) {
                        return;
                    }
                }

                //查询天气
                try {
                    BaiDuWeatherResult.Result result;
                    try {
                        lock();
                        result = weatherAPI.query(adCode);
                    } finally {
                        unlock();
                    }
                    update(redisKey, result);
                } catch (Exception e) {
                    log.warn(e.getMessage());
                }
            })
        );
    }

    private void lock() {
        while (true) {
            if (countDownLatch.get() > 0) {
                synchronized (countDownLatch) {
                    if (countDownLatch.get() > 0) {
                        countDownLatch.decrementAndGet();
                        break;
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //do nothing
            }
        }
    }

    private void unlock() {
        if (countDownLatch.get() < 30) {
            synchronized (countDownLatch) {
                if (countDownLatch.get() < 30) {
                    countDownLatch.addAndGet(1);
                }
            }
        }
    }

    private String genericRedisKey(String adCode) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return String.format("%s_%s", adCode, dateFormat.format(new Date()));
    }

    private void update(String redisKey, BaiDuWeatherResult.Result result) {
        if (Objects.isNull(result)) return;
        //保存当前天气到redis，有效期6小时
        redisTemplate.opsForValue().set(redisKey, result.getNow(), this.expire, TimeUnit.HOURS);
        //@todo 保存完整信息到mysql
        WeatherInfoVo weatherInfoVo = WeatherInfoVo.create()
                .withForecasts(result.getForecasts())
                .withLocation(result.getLocation())
                .withNow(result.getNow());
    }

    public Map<String, BaiDuWeatherResult.Now> findSimple(Collection<String> adCodes) {
        if (CollectionUtil.isEmpty(adCodes)) return new HashMap<>();
        return adCodes.stream().distinct()
                .map(code -> new HashMap.SimpleEntry<>(code, findSimple(code)))
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public BaiDuWeatherResult.Now findSimple(String adCode) {
        //先在redis查，查到了返回
        final String redisKey = genericRedisKey(adCode);
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o instanceof Map) {
            return JSONUtil.toBean(JSONUtil.toJsonStr(o),  BaiDuWeatherResult.Now.class);
        }

        //@todo 去数据库查，查到了更新到redis后返回


        //使用api查，异步更新到redis和MySQL，返回
        CompletableFuture<BaiDuWeatherResult.Now> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                lock();
                BaiDuWeatherResult.Result query = weatherAPI.query(adCode);
                if (Objects.nonNull(query)) return query.getNow();
            } catch (Exception e) {
                log.warn(e.getMessage());
            } finally {
                unlock();
            }
            return null;
        }, weatherQueryExecutor);
        try {
            BaiDuWeatherResult.Now now = supplyAsync.get();
            Optional.ofNullable(now).ifPresent(n -> {
                supplyAsync.thenAcceptAsync(action -> {
                    try {
                        BaiDuWeatherResult.Result result = weatherAPI.query(adCode);
                        if (Objects.isNull(result)) return;
                        update(redisKey, result);
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                });
            });
            return now;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public WeatherInfoVo findWeatherFromApi(String adCode) {
        try {
            BaiDuWeatherResult.Result result = weatherAPI.query(adCode);
            if (Objects.isNull(result)) return null;
            return WeatherInfoVo.create()
                    .withForecasts(result.getForecasts())
                    .withLocation(result.getLocation())
                    .withNow(result.getNow());
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, String> getWeatherPicPath() {
        List<Map<String, String>> weatherPicPath = baiDuWeatherMapper.getWeatherPicPath();
        Map<String, String> mapping = new HashMap<>();
        if (CollectionUtil.isEmpty(weatherPicPath)) return mapping;
        weatherPicPath.stream().filter(Objects::nonNull).forEach(e -> mapping.put(e.get("code"), e.get("path")));
        return mapping;
    }
}
