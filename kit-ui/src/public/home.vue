<template>
  <el-container class="el-container">
    <el-header class="el-header">
      <Head/>
    </el-header>
    <el-main class="el-main">
      <ul class="infinite-list" style="overflow:auto">
        <li class="infinite-list-item top-li">
          <div class="top-li-sub top-li-img">

          </div>
          <div class="top-li-sub top-li-bottom"></div>
        </li>
        <li class="infinite-list-item second-li">

        </li>
        <li class="infinite-list-item third-li">

        </li>
        <li class="infinite-list-item fou-li">

        </li>
        <li class="infinite-list-item bottom-li">

        </li>
      </ul>
      <Top/>
    </el-main>
  </el-container>

</template>

<script>
  import BaiDuMap from '../views/wather/BaiDuMap.vue';
  import Top from './Top.vue';
  import Head from './common/Head';

  export default {
    name: "Home",
    components: {
      BaiDuMap, Top, Head
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
    top: 0;
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
    background-image: url("../assets/images/home-top.png");
    background-size: cover;
  }

  .top-li-bottom {
    height: 60%;
    top: 50%;
    background-color: #fff;
  }

  .second-li {
    background-color: #b3420d;
  }

  .third-li {
    background-color: #b31474;
  }

  .fou-li {
    background-color: #0c55b3;
  }

  .bottom-li {
    background-color: #48b30f;
  }

</style>
