package io.kit.map.baidu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaiDuWeatherResult {
    public static final int SUCCEED = 0;

    private Result result;
    private String message;
    int status;

    @Data
    public static class Result {
        private Now now;
        private Location location;
        private List<Forecast> forecasts;
    }

    public boolean succeed() {
        return SUCCEED == status;
    }

    @Data
    public static class Now implements Serializable {
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

        public void setText(String text) {
            this.text = text;
            this.textCode = WeatherType.code(text);
        }

        public void setTextCode(String textCode) {
            //do nothing
        }
    }

    @Data
    public static class Location {
        private String country;
        private String province;
        private String city;
        private String name;
        private String id;
    }

    @Data
    public static class Forecast {
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
    }
}
