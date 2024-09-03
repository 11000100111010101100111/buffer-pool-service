<template>
  <el-container>
    <el-main>
      <div id="map-card" ref="mapCard">
        <baidu-map id="map"
                   :center="{lng: 113.89, lat: 22.555}"
                   @click="handleMapClick"
                   :scroll-wheel-zoom="true"
                   :dragging="true"
                   :keyboard="true"
                   :double-click-zoom="true"
                   @zoomend="handleZoomEnd"
                   @ready="initializeMarker"
                   :zoom="initialZoom"></baidu-map>
      </div>
    </el-main>
    <el-footer class="map-footer" v-show="showCityWeather">
      <el-drawer
        :title="cityName"
        :show-close="false"
        :with-header="false"
        :visible.sync="showCityWeather"
        style="opacity: 0.95;"
        size="50%"
        direction="btt">
        <weather-group
          :weather="weather"/>
      </el-drawer>

    </el-footer>
  </el-container>


</template>

<script>
  import { queryCityLocation, querySimpleWeather,queryCityWeather } from "@/api/weather/cityLocation";
  import WeatherGroup from "./WeatherGroup";

  export default {
    components: {WeatherGroup},
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

        lastZooms: [12],
        hasChangeZoom: false,
        cacheMarkId:[],

        showCityWeather: false,

        weather: {},
        cityName: "深圳"
      };
    },
    mounted() {
      // let zoomLevel = window.innerWidth < 768 ? 12 : 15;  // 根据屏幕宽度调整缩放级别
      // let point = this.map.getCenter();
      // window.addEventListener('resize', function() {
      //   map.centerAndZoom(point, 15);  // 重新调整地图中心和缩放级别
      // });
      // this.map.centerAndZoom(point, zoomLevel);
    },
    methods: {
      handleMapClick(e) {
        //let pt = e.point;
        //this.$message("经度: (" + pt.lon + "), 纬度: (" + pt.lat + ")");
      },
      createIcon(size, point) {
        let iconPath = this.sampleWeather[point.adCode].weatherIcon;
        return new BMap.Icon(iconPath, new BMap.Size(size, size), {
          imageSize: new BMap.Size(size, size),
          anchor: new BMap.Size(size / 2, size), // 锚点设置为图标底部中心
        });
      },
      initializeMarker({ BMap, map }) {
        this.map = map; // 保存地图实例
        this.loadVisibleMarkers(); // 加载可视区域的标记
        this.map.addEventListener("moveend", this.movedMap); // 监听移动事件
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
      movedMap() {
        this.hasChangeZoom = false;
        this.loadVisibleMarkers();
      },
      // 加载可视区域的标记
      loadVisibleMarkers() {
        if (!this.map) return;

        this.clearMarkers();
        const bounds = this.map.getBounds(); // 获取当前可视区域
        this.lastZooms.push(this.map.getZoom());
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
          if (res.code == 200) {
            res.data.forEach(item => this.points.push(item));

            if (this.points.length <= 0) {
              this.points.push({lon: 113.89, lat: 22.555, adCode: "440305"});
            }

            querySimpleWeather({adCode: this.points.map(item => item.adCode).join(','), split: ','})
              .then(response => {
                if (response.code === 200) {
                  this.sampleWeather = response.data;

                  this.visibleMarkers = this.points.filter((point) => {
                    return point.lon <= lonMax && point.lon >= lonMin && point.lat <= latMax && point.lat >= latMin && !this.cacheMarkId.includes(point.adCode);
                  });

                  // 添加可视区域内的标记
                  if (Array.isArray(this.visibleMarkers)) {
                    const currentZoom = this.map.getZoom(); // 使用保存的地图实例
                    const newSize = (currentZoom === this.initialZoom) ?
                      this.initialIconSize
                      : this.initialIconSize * (currentZoom / this.initialZoom);

                    this.visibleMarkers.forEach((point) => {
                      if (!this.sampleWeather[point.adCode]) return;
                      let icon = this.createIcon(newSize, point);
                      if (!icon) return;
                      const marker = new BMap.Marker(new BMap.Point(point.lon, point.lat), {
                        icon: icon,
                      });
                      // 为标注添加点击事件

                      var self = this;
                      marker.addEventListener('click', () => {
                        queryCityWeather(point.adCode).then(rsk => {
                          if (200 === rsk.code) {
                            self.showCityWeather = true;
                            self.weather = rsk.data;
                            self.cityName = self.weather.location.name;
                          }
                        })
                      });

                      this.map.addOverlay(marker);
                      this.markers.push(marker);
                      this.cacheMarkId.push(point.adCode);
                    });
                  }
                }
              });
          }
        });
      },
      // 清除之前的标记
      clearMarkers() {
        if (this.hasChangeZoom) {
          if (Array.isArray(this.markers)) {
            this.markers.forEach((marker) => {
              this.map.removeOverlay(marker);
            });
          }
          this.markers = []; // 清空标记数组
          this.cacheMarkId = [];
          this.lastZooms = [];
        }
        this.points = [];
      },
      // 处理缩放事件
      handleZoomEnd() {
        this.hasChangeZoom = true;
        // 重新加载可视区域的标记以更新图标大小
        this.loadVisibleMarkers();
      },
    },
  };
</script>

<style scoped>
  *{
    padding: 0;
    margin: 0;
  }
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
    margin: 0;
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
