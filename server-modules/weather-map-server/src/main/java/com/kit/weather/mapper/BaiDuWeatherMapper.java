package com.kit.weather.mapper;

import com.kit.weather.domain.entity.CityInfo;
import com.kit.weather.domain.param.QueryCityLocationParam;
import com.kit.weather.domain.vo.QueryCityLocationCountVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaiDuWeatherMapper {
    public QueryCityLocationCountVo queryCityLocationCount(QueryCityLocationParam param);

    public List<CityInfo> cityLocationInfo(QueryCityLocationParam param);

    public List<Map<String, String>> getWeatherPicPath();

    public List<Map<String, String>> getWeatherPicPathByCodes(String[] codes);
}
