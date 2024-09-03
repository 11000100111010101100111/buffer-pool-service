<template>
  <el-card :body-style="{ padding: '0px' }" style="border-radius: 10px;margin: 5px;">
      <div class="weather-container" :style="styleDayNight">
        <day-and-night @mode-changed="onModeChanged"/>
        <div class="weather-icon-container">
          <img :src="weatherIcon" alt="天气图标" class="weather-icon" />
        </div>
        <div>
          <p class="city-name">{{ cityName }} {{ date }} {{day}}</p>
          <h2 class="weather-name">{{ weatherName }}</h2>
          <p class="temperature">{{ temperatureMin }}°C ~ {{temperatureMax}}°C</p>
        </div>
        <div class="weather-info bottom clearfix">
          <p class="last-updated" style="font-size: 6px;color: gray;">数据来源: 百度天气</p>
        </div>
      </div>
  </el-card>
</template>

<script>
  import DayAndNight from "../../layout/components/DayAndNight";
  export default {
    name: "WeatherBar",
    components: {DayAndNight},
    props: {
      cityName: {
        type: String,
        required: true,
      },
      date: {
        type: String,
        required: true,
      },
      weatherName: {
        type: String,
        required: true,
      },
      temperatureMin: {
        type: Number,
        required: true,
      },
      temperatureMax: {
        type: Number,
        required: true,
      },
      lastUpdated: {
        type: String,
        required: true,
      },
      weatherIcon: {
        type: String,
        required: true,
      },
      forecast: {
        type: Object,
        required: true,
      },
    },
    data(){
      return {
        styleDayNight: {'background-color': '#ffffff'}
      }
    },
    methods:{
      onModeChanged(mode) {
        if (mode === 'night') {
          this.weatherIcon = this.forecast.weatherNightIcon;
          this.weatherName =  this.forecast.text_night;
          this.styleDayNight = {'background-color': '##000'};
          return;
        }
        this.weatherIcon = this.forecast.weatherDayIcon;
        this.weatherName = this.forecast.text_day;
        this.styleDayNight = {'background-color': '#ffffff'};
      }
    }
  };
</script>

<style scoped>
  .weather-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    padding: 20px;
    background-color: #f0f0f0;
    border-radius: 10px;
    max-width: 300px;
    margin: 0 auto;
  }

  .weather-info {
    margin-bottom: 20px;
  }

  .city-name {
    margin: 0;
  }

  .date,
  .weather-name,
  .temperature,
  .last-updated {
    margin: 5px 0;
  }

  .weather-icon-container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }

  .weather-icon {
    width: 80px;
    height: 80px;
    object-fit: contain;
  }

  .el-header,.el-footer {
    padding: 0;
    margin: 0;
  }



  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    width: 100%;
    padding: 0;
    margin: 0;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
