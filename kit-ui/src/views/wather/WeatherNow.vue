<template>
  <el-card :body-style="{ padding: '0px' }" style="border-radius: 10px;margin: 5px;background-color: #f0f0f0;">
    <div class="weather-container" style="background-color: #f0f0f0;">
      <day-and-night @mode-changed="onModeChanged" style="opacity: 0;" class="ignore"/>
      <div class="weather-icon-container">
        <img :src="now.weatherIcon" alt="天气图标" class="weather-icon"/>
      </div>
      <div>
        <p class="city-name">{{ location.province }}{{location.city}}{{location.name}}</p>
        <h2 class="weather-name">当前 {{ now.text }}</h2>
        <p class="temperature">{{ now.temp }}°C {{ now.wind_dir }}{{now.wind_class}}</p>
      </div>
      <div class="weather-info bottom clearfix">
        <p class="last-updated" style="font-size: 10px;color: gray;">最近更新: {{now.uptime}}</p>
      </div>
    </div>
  </el-card>
</template>

<script>
  import DayAndNight from "../../layout/components/DayAndNight";

  export default {
    name: "WeatherNow",
    components: {DayAndNight},
    props: {
      location: {
        type: Object,
        required: true,
      },
      now: {
        type: Object,
        required: true,
      },
    },
    data() {
      return {}
    },
    methods: {
      onModeChanged() {
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
    background-color: #ffffff;
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

  .el-header, .el-footer {
    padding: 0;
    margin: 0;
  }


  .time {
    font-size: 13px;
    color: #ffffff;
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

  .ignore:hover {
    cursor: default;
  }
</style>
