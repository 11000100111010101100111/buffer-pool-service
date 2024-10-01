package com.kit.system.domain.weather.baidu.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.kit.map.baidu.vo.BaiDuWeatherResult;
import io.kit.map.baidu.vo.WeatherType;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Objects;

@Data
public class WeatherEntryVo {

    @ApiParam("夜间风向")
    @JsonProperty("wd_night")
    String wdNight;

    @ApiParam("日间风向")
    @JsonProperty("wd_day")
    String wdDay;

    @ApiParam("夜间风力")
    @JsonProperty("wc_night")
    String wcNight;

    @ApiParam("日间风力")
    @JsonProperty("wc_day")
    String wcDay;

    @ApiParam("时间， yyyy-MM-dd")
    String date;

    @ApiParam("最高气温")
    int high;

    @ApiParam("最低气温")
    int low;

    @ApiParam("星期")
    String week;

    @ApiParam("夜间天气类型")
    @JsonProperty("text_night")
    String textNight;

    @ApiParam("日间天气类型")
    @JsonProperty("text_day")
    String textDay;

    @ApiParam("夜间天气类型值")
    String textNightType;

    @ApiParam("日间天气类型值")
    String textDayType;


    public static WeatherEntryVo parse(BaiDuWeatherResult.Forecast forecast) {
        if (Objects.isNull(forecast)) return null;
        WeatherEntryVo vo = new WeatherEntryVo();
        vo.setWdNight(forecast.getWdNight());
        vo.setWdDay(forecast.getWdDay());
        vo.setWcNight(forecast.getWcNight());
        vo.setWcDay(forecast.getWcDay());
        vo.setDate(forecast.getDate());
        vo.setHigh(forecast.getHigh());
        vo.setLow(forecast.getLow());
        vo.setWeek(forecast.getWeek());
        vo.setTextNightType(WeatherType.code(forecast.getTextNight()));
        vo.setTextNight(forecast.getTextNight());
        vo.setTextDayType(WeatherType.code(forecast.getTextDay()));
        vo.setTextDay(forecast.getTextDay());
        return vo;
    }
}
