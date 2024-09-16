package io.kit.map.baidu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Data
public class BaiDuWeatherResult {
    public static final int SUCCEED = 0;

    private Result result;
    private String message;
    int status;

    public static abstract class AbstractResult implements Serializable {
        private static final long serialVersionUID = 1L;

        public abstract void collectCodes(Set<String> codes);

        public abstract void withUrl(Map<String, String> urlMap);
    }

    @Data
    public static class Result extends AbstractResult implements Serializable {
        private static final long serialVersionUID = 1L;
        private Now now;
        private Location location;
        private List<Forecast> forecasts;
        protected String weatherIcon;

        @Override
        public void collectCodes(Set<String> codes) {
            Optional.ofNullable(now).ifPresent(n -> n.collectCodes(codes));
            Optional.ofNullable(forecasts).ifPresent(f -> f.forEach(item -> item.collectCodes(codes)));
        }

        @Override
        public void withUrl(Map<String, String> urlMap) {
            Optional.ofNullable(now).ifPresent(n -> n.withUrl(urlMap));
            Optional.ofNullable(forecasts).ifPresent(f -> f.forEach(item -> item.withUrl(urlMap)));
        }

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }
    }

    public boolean succeed() {
        return SUCCEED == status;
    }

    @Data
    public static class Now extends AbstractResult implements Serializable {
        private static final long serialVersionUID = 1L;
        private int temp;
        private int rh;
        @JsonProperty("wind_class")
        private String windClass;
        @JsonProperty("wind_dir")
        private String windDir;
        private String text;
        private String textCode;
        @JsonProperty("feelsLike")
        private int feelsLikes;
        private String uptime;
        protected String weatherIcon;

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        public void setText(String text) {
            this.text = text;
            this.textCode = WeatherType.code(text);
        }

        public void setTextCode(String textCode) {
            //do nothing
        }

        @Override
        public void collectCodes(Set<String> codes) {
            Optional.ofNullable(textCode).ifPresent(codes::add);
        }

        @Override
        public void withUrl(Map<String, String> urlMap) {
            Optional.ofNullable(textCode).ifPresent(code -> this.setWeatherIcon(urlMap.get(code)));
        }


    }

    @Data
    public static class Location implements Serializable {
        private static final long serialVersionUID = 1L;
        private String country;
        private String province;
        private String city;
        private String name;
        private String id;
    }

    @Data
    public static class Forecast extends AbstractResult implements Serializable {
        private static final long serialVersionUID = 1L;
        @JsonProperty("wd_night")
        private String wdNight;
        private String date;
        private int high;
        private String week;
        @JsonProperty("text_night")
        private String textNight;
        private String textNightCode;
        @JsonProperty("wd_day")
        private String wdDay;
        private int low;
        @JsonProperty("wc_night")
        private String wcNight;
        @JsonProperty("text_day")
        private String textDay;
        private String textDayCode;
        @JsonProperty("wc_day")
        private String wcDay;

        private String weatherDayIcon;
        private String weatherNightIcon;


        public void setTextNight(String textNight) {
            this.textNight = textNight;
            this.textNightCode = WeatherType.code(textNight);
        }

        public void setTextNightCode(String textNightCode) {
            //do nothing
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
            this.textDayCode = WeatherType.code(textDay);
        }

        public void setTextDayCode(String textDayCode) {
            //do nothing
        }

        @Override
        public void collectCodes(Set<String> codes) {
            if (Objects.isNull(weatherDayIcon)) {
                Optional.ofNullable(textDayCode).ifPresent(codes::add);
            }
            if (Objects.isNull(weatherNightIcon)) {
                Optional.ofNullable(textNightCode).ifPresent(codes::add);
            }
        }

        @Override
        public void withUrl(Map<String, String> urlMap) {
            if (Objects.isNull(weatherDayIcon)) {
                Optional.ofNullable(textDayCode).ifPresent(code -> this.setWeatherDayIcon(urlMap.get(code)));
            }
            if (Objects.isNull(weatherNightIcon)) {
                Optional.ofNullable(textNightCode).ifPresent(code -> this.setWeatherNightIcon(urlMap.get(code)));
            }
        }
    }
}
