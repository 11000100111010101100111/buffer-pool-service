package com.kit.system.mapper;

import com.kit.system.domain.weather.baidu.entity.CityInfo;
import com.kit.system.domain.weather.baidu.param.QueryCityLocationParam;
import com.kit.system.domain.weather.baidu.vo.QueryCityLocationCountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface BaiDuWeatherMapper {
    public QueryCityLocationCountVo queryCityLocationCount(QueryCityLocationParam param);

    public List<CityInfo> cityLocationInfo(QueryCityLocationParam param);

    public List<Map<String, String>> getWeatherPicPath();

    public List<Map<String, String>> getWeatherPicPathByCodes(String[] codes);
}
