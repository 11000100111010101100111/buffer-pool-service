<template>
  <el-container>
    <el-header>
      <Head/>
    </el-header>
    <el-main>
      <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
        <li class="infinite-list-item top-li">
          <div class="top-li-sub top-li-img">
          </div>
          <div class="top-li-sub top-li-bottom">
            <el-container style="border-radius: 5px;">
              <el-aside width="100%" class="weather-bar-contain">
                <el-row>
                  <el-col :xs="2" :sm="2" :md="2" :lg="8" :xl="2">
                    <el-select v-model="fromValue" placeholder="请选择">
                      <el-option-group
                        v-for="group in options.from"
                        :key="group.label"
                        :label="group.label">
                        <el-option
                          v-for="item in group.options"
                          :key="item.code"
                          :label="item.name"
                          :value="item.code">
                        </el-option>
                      </el-option-group>
                    </el-select>
                    <el-select v-model="toValue" placeholder="请选择">
                      <el-option-group
                        v-for="group in options.to"
                        :key="group.label"
                        :label="group.label">
                        <el-option
                          v-for="item in group.options"
                          :key="item.code"
                          :label="item.name"
                          :value="item.code">
                        </el-option>
                      </el-option-group>
                    </el-select>
                    <el-button type="info">信息按钮</el-button>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :span="6">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :span="6">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                </el-row>
              </el-aside>
            </el-container>
          </div>
        </li>
      </ul>
    </el-main>
    <el-footer>

    </el-footer>
  </el-container>
</template>

<script>
  import {queryLanguage} from '@/api/translate/translate';
  import Head from '../common/Head';
  import BaiDuMap from "../../views/wather/BaiDuMap";

  export default {
    name: "LangTranslate",
    components: {
      Head
    },
    data() {
      return {
        fromValue: 'zh',
        toValue: 'en',
        options: {
          "from": [{
            "label": '热门',
            "options": [{
              name: "",
              code: "",
              tag: "",
              sort: "",
              fromHot: 1,
              toHot: 1
            }, {
              "label": 'A',
              "options": [{
                name: "",
                code: "",
                tag: "",
                sort: "",
                fromHot: 1,
                toHot: 1
              }]
            }]
          }],
          "to": []
        }
      }
    },
    created() {
      queryLanguage().then((res) => {
        if (res.code === 200) {
          let fromOptions = [];
          let toOptions = [];
          console.log(res)
          let topTo = res.data['topToHot'];
          let topFrom = res.data['topFromHot'];
          fromOptions.push({
            label: '热门语言',
            options: topFrom
          });
          toOptions.push({
            label: '热门语言',
            options: topTo
          });
          Object.keys(res.data['groupLang']).forEach(key => {
            fromOptions.push({label: key, options: res.data['groupLang'][key]});
            toOptions.push({label: key, options: res.data['groupLang'][key]})
          });
          this.options = {
            "from": fromOptions,
            "to": toOptions
          }
        }
      })
    }
  }
</script>

<style scoped>
  * {
    margin: 0;
    padding: 0;
  }

  body {
    overflow: hidden;
  }

  .el-container {
    overflow: hidden;
  }

  .el-header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000; /* 确保头部浮动在最上层 */
    background-color: rgba(0, 0, 0, 0.0); /* 背景颜色避免透明时影响显示 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.0); /* 为头部添加阴影 */
  }

  .el-main {
  }

  .infinite-list-item {
    top: 0;
    left: 0;
    width: 100vw; /* 宽度占满父容器 */
    height: 100vh; /* 高度为可视区高度减去 header 的高度 */
    list-style: none;
  }

  .top-li-sub {
    width: 100%;
  }

  .top-li-img {
    top: 0;
    height: 40%;
    background-image: url("../../assets/images/home-top.png");
    background-size: cover;
  }

  .top-li-bottom {
    height: 60%;
    top: 50%;
    background-color: #fff;
  }
</style>
