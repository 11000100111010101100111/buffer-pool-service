<template>
  <el-row class="tac">
    <el-col :span="12">
  <el-menu
    :default-active="activeIndex"
    class="el-menu-vertical-demo"
    mode="vertical"
    @select="handleSelect"
    :background-color="backgroundColor"
    :text-color="textColor"
    :collapse="collapse"
    :active-text-color="activeTextColor">
<!--    <el-submenu index="2">-->
<!--      <template slot="title">-->
<!--        <i class="iconfont icon-kaifagongju"></i>-->
<!--        <span slot="title">开发工具</span>-->
<!--      </template>-->
<!--      <el-menu-item index="2-3" disabled class="iconfont icon-api">Open API</el-menu-item>-->
<!--      <el-menu-item index="2-4" disabled class="iconfont icon-webhook">WebHook</el-menu-item>-->
<!--      <el-menu-item index="2-5" disabled class="iconfont icon-json">JSON在线</el-menu-item>-->
<!--      <el-menu-item index="2-6" disabled class="iconfont icon-fuwu">消息通信</el-menu-item>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="3">-->
<!--      <template slot="title">-->
<!--        <i class="iconfont icon-ailiaotian_AIliaotian"></i>-->
<!--        <span slot="title">AI-GC</span>-->
<!--      </template>-->
<!--      <el-menu-item index="3-2" disabled class="iconfont icon-rtcyinshipintongxin">音视频处理</el-menu-item>-->
<!--      <el-menu-item index="3-3" disabled class="iconfont icon-ailiaotian_AIliaotian">AI聊天</el-menu-item>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="4">-->
<!--      <template slot="title">-->
<!--        <i class="iconfont icon-kaifarenyuan"></i>-->
<!--        <span slot="title">开发者</span>-->
<!--      </template>-->
<!--      <el-menu-item index="4-2" disabled class="iconfont icon-fuwu">面向开发者</el-menu-item>-->
<!--      <el-menu-item index="4-3" disabled class="iconfont icon-shuomingwendang">文档中心</el-menu-item>-->
<!--      <el-menu-item index="4-4" disabled class="iconfont icon-dc-icon-guojiazhongdianshiyanshi">实验室</el-menu-item>-->
<!--      <el-menu-item index="4-5" disabled class="iconfont icon-yijianyujianyifankui">意见&建议</el-menu-item>-->
<!--      <el-menu-item index="4-6" disabled class="iconfont icon-QAyanshou">Q&A</el-menu-item>-->
<!--      <el-menu-item index="4-7" disabled><a href="https://www.ele.me" target="_blank"-->
<!--                                             class="iconfont icon-guanyuwomen">关于我们</a>-->
<!--      </el-menu-item>-->
<!--    </el-submenu>-->
    <el-menu-item index="1" @click="homePage">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-menu-item index="2" @click="toAIGCImg">
      <i class="iconfont icon-rtcyinshipintongxin"></i>
      <span slot="title">图像生成</span>
    </el-menu-item>
    <el-menu-item index="3" @click="handleTranslate">
      <i class="iconfont icon-json"></i>
      <span slot="title">翻译</span>
    </el-menu-item>
    <el-menu-item index="4" @click="handleBaiDuAPIMap">
      <i class="iconfont el-icon-map-location"></i>
      <span slot="title">实时天气地图</span>
    </el-menu-item>
    <el-menu-item index="5" @click="blog">
      <i class="iconfont icon-blog1"></i>
      <span slot="title">Blog</span>
    </el-menu-item>
    <el-menu-item index="6" @click="toGithub">
      <i class="iconfont icon-guanli-1111"></i>
      <span slot="title">社区&共建</span>
    </el-menu-item>
  </el-menu>
    </el-col>
  </el-row>
</template>

<script>
    export default {
        name: "MyMenu",
        props: {
          backgroundColor: {
            type: String,
            default: '#545c64'
          },
          textColor: {
            type: String,
            default: '#fff'
          },
          activeTextColor: {
            type: String,
            default: '#ffd04b'
          }
        },
        data() {
          return {
            collapse: true,
            activeIndex: '1',
          }
        },
      methods: {
        blog() {
          window.open('/blog', '_blank');
        },
        toAIGCImg() {
          window.open('/ai-gc/image', '_blank');
        },
        handleTranslate() {
          window.location.href = '/translate';
        },
        homePage() {
          window.location.href = '/home';
        },
        toGithub() {
          window.open('https://github.com/11000100111010101100111/SmallQuestionNotes', '_blank');
        },
        handleBaiDuAPIMap() {
          window.location.href = '/weather-baidu';
        },
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        }
      }
    }
</script>

<style scoped>

</style>
