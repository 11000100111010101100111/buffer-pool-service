package com.kit.weather.domain.entity;

import com.kit.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class WeatherInfo extends BaseEntity {
    String nowTemp;
    String nowRh;
    String nowWindClass;
    String nowTxt;
    String nowWindDir;
    String nowFeelsLike;
    String upTime;
    String country;
    String province;
    String city;
    String name;
    String locationId;
    String forecasts;
}
