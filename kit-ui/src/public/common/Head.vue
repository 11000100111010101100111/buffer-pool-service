<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo el-menu"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="1" class="el-icon-s-home" @click="homePage">首页</el-menu-item>
    <el-submenu index="2">
      <template slot="title">产品</template>
      <el-submenu index="2-1">
        <template slot="title" class="el-icon-sunny">实时天气地图</template>
        <el-menu-item index="2-1-1" class="el-icon-map-location" @click="handleBaiDuAPIMap">百度 API</el-menu-item>
        <el-menu-item index="2-1-2" disabled class="el-icon-discover" >高德 API</el-menu-item>
        <el-menu-item index="2-3-3" disabled class="el-icon-more">更 多</el-menu-item>
      </el-submenu>
      <el-menu-item index="2-2" disabled class="el-icon-folder-checked">文件解析</el-menu-item>
      <el-submenu index="2-3">
        <template slot="title" class="iconfont icon-kaifagongju">开发工具</template>
        <el-menu-item index="2-3-1" disabled class="iconfont icon-api">Open API</el-menu-item>
        <el-menu-item index="2-3-2" disabled class="iconfont icon-webhook">WebHook</el-menu-item>
        <el-menu-item index="2-3-3" disabled  class="iconfont icon-json">JSON在线</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title" class="iconfont">新计划</template>
      <el-menu-item index="3-1" disabled class="iconfont icon-rtcyinshipintongxin">音视频处理</el-menu-item>
      <el-menu-item index="3-2" disabled class="iconfont icon-ailiaotian_AIliaotian">AI聊天</el-menu-item>
      <el-menu-item index="3-3" disabled class="iconfont icon-fuwu">消息通信</el-menu-item>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title" class="iconfont icon-kaifarenyuan">开发人员</template>
      <el-menu-item index="4-1" disabled class="iconfont icon-fuwu">面向开发者</el-menu-item>
      <el-menu-item index="4-2" disabled class="iconfont icon-shuomingwendang">文档中心</el-menu-item>
      <el-menu-item index="4-3" disabled class="iconfont icon-dc-icon-guojiazhongdianshiyanshi">实验室</el-menu-item>
      <el-menu-item index="4-4" @click="toGithub" class="iconfont icon-guanli-1111">社区&共建</el-menu-item>
      <el-menu-item index="4-5" disabled disabled class="iconfont icon-yijianyujianyifankui">意见&建议</el-menu-item>
      <el-menu-item index="4-6" disabled disabled class="iconfont icon-QAyanshou">Q&A</el-menu-item>
      <el-menu-item index="4-7" disabled><a href="https://www.ele.me" target="_blank" class="iconfont icon-guanyuwomen">关于我们</a></el-menu-item>
    </el-submenu>
    <el-menu-item index="5" class="iconfont icon-blog1" @click="blog">博客</el-menu-item>
    <el-menu-item index="5" class="login-button">
      <div v-if="isLoggedIn" class="avatar-wrapper">
        <el-tooltip class="item" effect="dark" :content="userName" placement="bottom-end">
          <img :src="userAvatar" alt="用户头像" class="avatar"/>
        </el-tooltip>
      </div>
      <div v-else @click="handleLogin" class="login-text">登</div>
    </el-menu-item>
  </el-menu>
</template>

<script>
    export default {
        name: "Head",
      data() {
        return {
          activeIndex: '1',
          activeIndex2: '1',

          activeMenu: '1',
          isLoggedIn: false, // 用户登录状态
          userAvatar: '', // 用户头像地址
          userName: '', // 用户名

          headers: { Authorization: null }
        }
      },
      mounted() {
        this.checkLoginStatus();
      },
      methods: {
        blog() {
          window.open('/blog', '_blank');
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
        },
        handleLogin() {
          // 跳转到登录页面或调用登录逻辑
          window.location.href = '/login'; // 假设登录页面为 /login
        },
        checkLoginStatus() {
          // 检查用户是否登录，可以通过检查 token 或调用后端 API
          const token = store.getters.token;// 示例中从 localStorage 获取 token
          if (token) {
            // 假设存在 token，则用户已登录，获取用户信息
            this.headers.Authorization =  "Bearer " + token;
            this.isLoggedIn = true;
            this.getUserInfo();
          } else {
            this.isLoggedIn = false;
          }
        },
        getUserInfo() {
          // 调用后端 API 获取用户信息
          // 示例中使用了 async/await 和 fetch API，你可以根据你的项目配置使用 axios 或其他请求库
          if (this.headers.Authorization) {
            getUserProfile().then(response => {
              if (200 == response.code) {
                console.log(response.data)
                this.userAvatar = response.data.avatar;
                this.userAvatar = ( this.userAvatar == "" ||  this.userAvatar  == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + this.userAvatar;
                this.userName = response.data.nickName;
              }
            });
          }
        },
      }
    }
</script>

<style scoped>
  body {
    margin: 0 !important;
    padding: 0 !important;
    overflow: hidden;
  }
  .el-menu {
    display: flex;
    justify-content: space-between; /* 让菜单项两端对齐 */
  }

  .login-button {
    margin-left: auto; /* 将按钮推到右边 */
    width: 54px; /* 设置为正方形，宽度 */
    height: 54px; /* 设置为正方形，高度 */
    margin-top: 3px;
    margin-right: 3px;
    padding: 0; /* 移除默认的内边距 */
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%; /* 确保头像是圆形 */
  }

  .login-button > .avatar-wrapper,
  .login-button > .login-text {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .login-button:hover {
    background: #303133;
  }

  .avatar-wrapper {
    border-radius: 50%; /* 确保头像是圆形 */
    overflow: hidden;
    background: #303133;
  }

  .avatar {
    width: 100%; /* 头像填充整个容器 */
    height: 100%;
    object-fit: cover;
  }

  .login-text {
    border-radius: 50%; /* 使登录按钮的文字容器也是圆形 */
    background-color: #FFD700;
    color: rgb(84, 92, 100);
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
    line-height: 1; /* 确保文字居中 */
    transition: transform 0.5s ease;
  }
  .login-text:hover {
    font-weight: bolder;
    transform: scale(1.1);
    box-shadow: 0 0 10px 0 #FFD700;
  }

  .login-button {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  /*.avatar-wrapper {*/
  /*  width: 40px;*/
  /*  height: 40px;*/
  /*  border-radius: 50%; !* 圆形样式 *!*/
  /*  overflow: hidden;*/
  /*  background-color: #f2f2f2;*/
  /*  display: flex;*/
  /*  align-items: center;*/
  /*  justify-content: center;*/
  /*}*/

  /*.avatar {*/
  /*  width: 100%;*/
  /*  height: 100%;*/
  /*  object-fit: cover;*/
  /*}*/
  /* 将 el-header 固定在顶部 */
  /*.el-header{*/
  /*  position: fixed;*/
  /*  top: 0;*/
  /*  left: 0;*/
  /*  width: 100%;*/
  /*  z-index: 1000; !* 确保 header 处于最上层 *!*/
  /*}*/
</style>
