<template>
  <el-container class="el-container">
    <el-header class="el-header">
      <Head/>
    </el-header>
    <el-main class="el-main">

    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "blog"
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
</style>
