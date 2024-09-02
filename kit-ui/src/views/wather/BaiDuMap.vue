<template>
  <div id="map-card" ref="mapCard">
    <baidu-map id="map"
               :center="{lng: 113.89, lat: 22.555}"
               @click="handleMapClick"
               :scroll-wheel-zoom="true"
               @zoomend="handleZoomEnd"
               @ready="initializeMarker"
               :zoom="initialZoom"></baidu-map>
    <el-menu default-active="1-4-1"
             class="el-menu-vertical-demo floating-menu"
             :hidden="isPublic"
             :collapse="true">
      <el-submenu index="1">
        <el-menu-item index="1" :disabled="!topPic.simple">
          <i class="el-icon-menu"></i>
          <span slot="title">简约图层</span>
        </el-menu-item>
        <el-menu-item index="2" :disabled="!topPic.realtime">
          <i class="el-icon-document"></i>
          <span slot="title">实时图层</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <span slot="title">关闭天气图层</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  import { queryCityLocation, querySimpleWeather,queryWeatherPicPath } from "@/api/weather/cityLocation";

  export default {
    props: {
      isPublic: {
        type: Boolean,
        required: true,
        default: false
      }
    },
    data() {
      return {
        isPopoverVisible: false,
        customMapStyle: {
          styleId: '654b890d11d6aed32a595398821d3e0d'
        },
        topPic:{
          "simple": true,
          "realtime": false
        },

        initialIconSize: 50, // 初始图标大小
        initialZoom: 12, // 初始缩放级别
        markers: [], // 存储 Marker 实例
        map: null, // 存储百度地图的 Map 实例
        points: [], // 所有点的列表
        visibleMarkers: [], // 可视区域内的标记
        sampleWeather: {},
        weatherMapping: null
      };
    },
    mounted() {
      this.initWeatherMapping();
    },
    methods: {
      handleMapClick(e) {
        //let pt = e.point;
        //this.$message("经度: (" + pt.lon + "), 纬度: (" + pt.lat + ")");
      },
      initWeatherMapping() {
        queryWeatherPicPath().then(res => {
          if (200 == res.code) {
            this.weatherMapping = res.data;
          }
        })
      },
      createIcon(size, weatherInfo) {
        if (!weatherInfo) return null;
        if (!weatherInfo.text) return null;
        if (null == this.weatherMapping) this.initWeatherMapping();
        let iconPath = this.weatherMapping[weatherInfo.textCode];
        console.log(iconPath);
        return new BMap.Icon(iconPath, new BMap.Size(size, size), {
          imageSize: new BMap.Size(size, size),
          anchor: new BMap.Size(size / 2, size), // 锚点设置为图标底部中心
        });
      },
      initializeMarker({ BMap, map }) {
        this.map = map; // 保存地图实例
        this.loadVisibleMarkers(); // 加载可视区域的标记
        this.map.addEventListener("zoomend", this.loadVisibleMarkers); // 监听缩放事件
        this.map.addEventListener("moveend", this.loadVisibleMarkers); // 监听移动事件
      },
      getBounds() {
        const bounds = this.map.getBounds(); // 获取当前可视区域
        if (!bounds) return null;
        let latMin = bounds.$d;
        let latMax = bounds.Yd;
        let lonMin = bounds.Ne;
        let lonMax = bounds.Je;
        return {
          "latMin":latMin,
          "latMax":latMax,
          "lonMin":lonMin,
          "lonMax":lonMax
        };
      },
      // 加载可视区域的标记
      loadVisibleMarkers() {
        if (!this.map) return;
        // 清除之前的标记
        this.clearMarkers();
        const bounds = this.map.getBounds(); // 获取当前可视区域
        if (!bounds) return;

        let latMin = bounds.$d;
        let latMax = bounds.Yd;
        let lonMin = bounds.Ne;
        let lonMax = bounds.Je;
        queryCityLocation({
          "latMin":latMin,
          "latMax":latMax,
          "lonMin":lonMin,
          "lonMax":lonMax
        }).then(res => {
          if (res.code == '200') {
            this.points = res.data;
          } else {
            this.points = [{ lon: 113.89, lat: 22.555, adCode:"" }]
          }
        });

        if (this.points.length > 0) {
          querySimpleWeather({adCode: this.points.map(item => item.adCode).join(','), split: ','})
            .then(response => {
              if (response.code == '200') {
                this.sampleWeather = response.data;
              }
            })
        }


        this.visibleMarkers = this.points.filter((point) => {
          return point.lon <= lonMax && point.lon >= lonMin && point.lat <= latMax && point.lat >= latMin;
        });
        // 添加可视区域内的标记
        if (Array.isArray(this.visibleMarkers)) {
          const currentZoom = this.map.getZoom(); // 使用保存的地图实例
          const newSize = (currentZoom === this.initialZoom) ?
            this.initialIconSize
            : this.initialIconSize * (currentZoom / this.initialZoom);

          this.visibleMarkers.forEach((point) => {
            let icon = this.createIcon(newSize, this.sampleWeather[point.adCode]);
            if (!icon) return;
            const marker = new BMap.Marker(new BMap.Point(point.lon, point.lat), {
              icon: icon,
            });
            this.map.addOverlay(marker);
            this.markers.push(marker);
          });
        }
      },
      // 清除之前的标记
      clearMarkers() {
        if (Array.isArray(this.markers)) {
          this.markers.forEach((marker) => {
            this.map.removeOverlay(marker);
          });
        }
        this.markers = []; // 清空标记数组
      },
      // 处理缩放事件
      handleZoomEnd() {
        // 重新加载可视区域的标记以更新图标大小
        this.loadVisibleMarkers();
      },
    },
  };
</script>

<style scoped>
  #map {
    width: 100%;
    height: 100vh;
    margin: 0;
    padding: 0;
    position: relative;
    opacity: 1;
  }

  #map-card {
    padding: 0;
  }
  .el-popover {
    position: absolute;
  }
  .floating-menu {
     position: absolute;
     top: 10px;
     right: 10px;
    width: 50px;
    border-radius: 20px; /* 设置圆角 */
    overflow: hidden; /* 防止内容溢出 */
    z-index: 99999;
  }
  .custom-icon {
    opacity: 1; /* 50% 透明度 */
  }
</style>
