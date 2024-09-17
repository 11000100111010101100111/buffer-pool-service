package io.kit.map.baidu.vo;

import java.util.HashSet;
import java.util.Set;

public enum WeatherType {
    SUNNY("00", "晴", "Sunny", "Clear"),
    CLOUDY("01", "多云", "Cloudy", "Cloudy"),
    OVERCAST("02", "阴", "Overcast", "Overcast"),
    SHOWER("03", "阵雨", "Shower", "Shower"),
    THUNDERSHOWER("04", "雷阵雨", "Thundershower", "Thundershower"),
    THUNDERSHOWER_WITH_HAIL("05", "雷阵雨伴有冰雹", "Thundershower with hail", "Thundershower with hail"),
    SLEET("06", "雨夹雪", "Sleet", "Sleet"),
    LIGHT_RAIN("07", "小雨", "Light rain", "Light rain"),
    MODERATE_RAIN("08", "中雨", "Moderate rain", "Moderate rain"),
    HEAVY_RAIN("09", "大雨", "Heavy rain", "Heavy rain"),
    STORM("10", "暴雨", "Storm", "Storm"),
    HEAVY_STORM("11", "大暴雨", "Heavy storm", "Heavy storm"),
    SEVERE_STORM("12", "特大暴雨", "Severe storm", "Severe storm"),
    SNOW_FLURRY("13", "阵雪", "Snow flurry", "Snow flurry"),
    LIGHT_SNOW("14", "小雪", "Light snow", "Light snow"),
    MODERATE_SNOW("15", "中雪", "Moderate snow", "Moderate snow"),
    HEAVY_SNOW("16", "大雪", "Heavy snow", "Heavy snow"),
    SNOWSTORM("17", "暴雪", "Snowstorm", "Snowstorm"),
    FOG("18", "雾", "Fog", "Fog"),
    ICE_RAIN("19", "冻雨", "Ice rain", "Ice rain"),
    DUSTSTORM("20", "沙尘暴", "Duststorm", "Duststorm"),
    LIGHT_TO_MODERATE_RAIN("21", "小到中雨", "Light to moderate rain", "Light to moderate rain"),
    MODERATE_TO_HEAVY_RAIN("22", "中到大雨", "Moderate to heavy rain", "Moderate to heavy rain"),
    HEAVY_RAIN_TO_STORM("23", "大到暴雨", "Heavy rain to storm", "Heavy rain to storm"),
    STORM_TO_HEAVY_STORM("24", "暴雨到大暴雨", "Storm to heavy storm", "Storm to heavy storm"),
    HEAVY_TO_SEVERE_STORM("25", "大暴雨到特大暴雨", "Heavy to severe storm", "Heavy to severe storm"),
    LIGHT_TO_MODERATE_SNOW("26", "小到中雪", "Light to moderate snow", "Light to moderate snow"),
    MODERATE_TO_HEAVY_SNOW("27", "中到大雪", "Moderate to heavy snow", "Moderate to heavy snow"),
    HEAVY_SNOW_TO_SNOWSTORM("28", "大到暴雪", "Heavy snow to snowstorm", "Heavy snow to snowstorm"),
    DUST("29", "浮尘", "Dust", "Dust"),
    SAND("30", "扬沙", "Sand", "Sand"),
    SANDSTORM("31", "强沙尘暴", "Sandstorm", "Sandstorm"),
    DENSE_FOG("32", "浓雾", "Dense fog", "Dense fog"),
    TORNADO("33", "龙卷风", "Tornado", "Tornado"),
    WEAK_HIGH_BLOW_SNOW("34", "弱高吹雪", "Weak high blow snow", "Weak high blow snow"),
    MIST("35", "Mist", "Mist", "Mist"),
    HEAVY_DENSE_FOG("49", "强浓雾", "Heavy dense fog", "Heavy dense fog"),
    HAZE("53", "霾", "Haze", "Haze"),
    MODERATE_HAZE("54", "中度霾", "Moderate haze", "Moderate haze"),
    SEVERE_HAZE("55", "重度霾", "Severe haze", "Severe haze"),
    HAZARDOUS_HAZE("56", "严重霾", "Hazardous haze", "Hazardous haze"),
    HEAVY_FOG("57", "大雾", "Heavy fog", "Heavy fog"),
    EXTRA_HEAVY_DENSE_FOG("58", "特强浓雾", "Extra-heavy dense fog", "Extra-heavy dense fog"),
    RAIN("301", "雨", "Rain", "Rain"),
    SNOW("302", "雪", "Snow", "Snow"),
    UN_KNOW("000", "未知", "Unknow", "Unknow");
    String type;
    String typeName;
    String dayEn;
    String nightEn;

    WeatherType(String... chars) {
        this.type = chars[0];
        this.typeName = chars[1];
        this.dayEn = chars[2];
        this.nightEn = chars[3];
    }

    public static String code(String name) {
        for (WeatherType value : values()) {
            if (value.typeName.equals(name)) return value.type;
        }
        return UN_KNOW.type;
    }

    public static Set<String> codes() {
        Set<String> codes = new HashSet<>();
        for (WeatherType value : values()) {
            codes.add(value.type);
        }
        return codes;
    }
}
