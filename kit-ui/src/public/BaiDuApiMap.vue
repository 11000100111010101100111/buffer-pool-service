<template>
  <el-container class="el-container">
    <el-header class="el-header">
      <Head/>
    </el-header>
    <el-main class="el-main">
      <Top />
      <BaiDuMap :is-public="true" class="bai-du-map"/>
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
  body {
    margin: 0 !important;
    padding: 0 !important;
    overflow: hidden;
  }
  .el-container, .el-header, .el-main {
    padding: 0;
    overflow: hidden;
    margin: 0;
  }

  .bai-du-map {
    width: 100%;       /* 宽度占满父容器 */
    height: calc(100vh - 60px); /* 高度为可视区高度减去 header 的高度 */
  }
</style>
