package com.kit.system.domain.weather.baidu.vo;

import cn.hutool.core.collection.CollectionUtil;
import io.kit.map.baidu.vo.BaiDuWeatherResult;
import io.kit.map.baidu.vo.WeatherType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class WeatherInfoVo {
    int nowTemp;
    int nowRh;
    String nowWindClass;
    String nowTxt;
    String nowTxtCode;
    String nowWindDir;
    int nowFeelsLike;
    String upTime;

    String country;
    String province;
    String city;
    String name;
    String locationId;

    List<WeatherEntryVo> forecastList;

    public static WeatherInfoVo create() {
        return new WeatherInfoVo();
    }

    public WeatherInfoVo withNow(BaiDuWeatherResult.Now now) {
        if (Objects.isNull(now)) return this;
        this.nowTemp = now.getTemp();
        this.nowRh = now.getRh();
        this.nowWindClass = now.getWindClass();
        this.nowTxt = now.getText();
        this.nowTxtCode = WeatherType.code(this.nowTxt);
        this.nowWindDir = now.getWindDir();
        this.nowFeelsLike = now.getFeelsLikes();
        this.upTime = now.getUptime();
        return this;
    }

    public WeatherInfoVo withLocation(BaiDuWeatherResult.Location location) {
        if (Objects.isNull(location)) return this;
        this.country = location.getCountry();
        this.province = location.getProvince();
        this.city = location.getCity();
        this.name = location.getName();
        this.locationId = location.getId();
        return this;
    }

    public WeatherInfoVo withForecasts(List<BaiDuWeatherResult.Forecast> forecasts) {
        if (Objects.isNull(this.forecastList)) this.forecastList = new ArrayList<>();
        if (CollectionUtil.isEmpty(forecasts)) {
            return this;
        }
        forecasts.stream()
                .filter(Objects::nonNull)
                .forEach(forecast -> Optional.ofNullable(WeatherEntryVo.parse(forecast)).ifPresent(this.forecastList::add));
        return this;
    }
}
