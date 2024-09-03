<template>
  <el-container style="border-radius: 5px;">
    <el-container>
      <el-aside width="100%" class="weather-bar-contain">
          <el-row>
            <div>
              <el-tag type="info" class="el-icon-location" style='font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;"'>{{weather.location.province}}{{weather.location.city}} | {{ weather.location.name }}</el-tag>
              <el-divider direction="vertical"/>
              <span style='font-size: 8px;color: gray;font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;"'>{{weather.now.uptime}}</span>
            </div>
          </el-row>
          <el-row style="background-color: #f0f0f0;">
            <el-col :xs="24" :sm="8" :md="6" :lg="4" :xl="2" style="padding: 0;margin: 0;background-color: #f0f0f0;">
              <weather-now class="now" :location="weather.location" :now="weather.now" style="background-color: #f0f0f0;"/>
            </el-col>
            <el-col :xs="24" :sm="8" :md="6" :lg="4" :xl="2" v-for="(item, index) in weather.forecasts" :key="item.date" :offset="index > 0 ? 2 : 0" style="padding: 0;margin: 0">
                <weather-bar class="other" :cityName="parseDay(index)"
                             :date="item.date"
                             :forecast="item"
                             :weatherName="item.text_day"
                             :temperature-max="item.high"
                             :temperature-min="item.low"
                             :lastUpdated="weather.now.uptime"
                             :weatherIcon="item.weatherDayIcon"/>
            </el-col>
          </el-row>
          <el-row>

          </el-row>
      </el-aside>
    </el-container>
  </el-container>
</template>

<script>
  import WeatherBar from "./WeatherBar";
  import WeatherNow from "./WeatherNow";

  export default {
    name: "WeatherGroup",
    components: {WeatherNow, WeatherBar},
    props: {
      weather: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        weather: {
          now: {
            temp: 0,
            rh: 0,
            windClass: "",
            windDir: "",
            text: "",
            textCode: "",
            feelsLikes: 0,
            uptime: "2024-09-02",
            weatherIcon: "/icon/weather/weather_Sunny.png"
          },
          location: {
            country: "",
            province: "",
            city: "",
            name: "深圳",
            id: ""
          },
          forecasts: [{
            city: "深圳",
            date: "2024-09-02",
            textNight: "晴天",
            textNightCode: "Sunny",
            textDay: "晴天",
            textDayCode: "Sunny",
            high: 32,
            low: 38,
            wcNight: "",
            wcDay: "",
            weatherIcon: "/icon/weather/weather_Sunny.png"
          }]
        }
      }
    },
    mounted() {
      // this.weather = weather;
      console.log(this.weather)
    },
    methods: {
      parseDay(index) {
        switch (index) {
          case 0: return '今天';
          case 1: return '明天';
          case 2: return '后天';
          case 3: return '大后天';
          case 4: return '大大后天';
          default: return '';
        }
      }
    }
  }
</script>

<style scoped>
.weather-bar-contain {
  padding: 1%;
}
.weather-container {
  padding: 0px;
  background-color: #f0f0f0;
  border-radius: 10px;
  margin: 0 auto;
}
  .now:hover{
    box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.5);
  }

  .other:hover{
    box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.5);
  }

</style>
