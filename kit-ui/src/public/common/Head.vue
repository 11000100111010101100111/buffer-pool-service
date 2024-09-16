<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo el-menu"
    mode="horizontal"
    @select="handleSelect"
    :background-color="backgroundColor"
    :text-color="textColor"
    :active-text-color="activeTextColor">
    <el-menu-item index="1" class="el-icon-s-home" @click="homePage">首页</el-menu-item>
    <el-submenu index="2">
      <template slot="title">工具包</template>
      <el-submenu index="2-1">
        <template slot="title" class="iconfont icon-kaifagongju">开发工具</template>
        <el-menu-item index="2-1-1" class="iconfont el-icon-map-location" @click="handleBaiDuAPIMap">实时天气地图</el-menu-item>
        <el-menu-item index="2-1-2" class="iconfont icon-json" @click="handleTranslate">翻译</el-menu-item>
        <el-menu-item index="2-1-1" disabled class="iconfont icon-api">Open API</el-menu-item>
        <el-menu-item index="2-1-2" disabled class="iconfont icon-webhook">WebHook</el-menu-item>
        <el-menu-item index="2-1-3" disabled class="iconfont icon-json">JSON在线</el-menu-item>
        <el-menu-item index="2-1-4" disabled class="iconfont icon-fuwu">消息通信</el-menu-item>
      </el-submenu>
      <el-submenu index="2-2">
        <template slot="title" class="iconfont icon-kaifagongju">AIGC</template>
        <el-menu-item index="2-2-1" class="iconfont icon-rtcyinshipintongxin" @click="toAIGCImg">图像生成</el-menu-item>
        <el-menu-item index="2-2-1" disabled class="iconfont icon-rtcyinshipintongxin">音视频处理</el-menu-item>
        <el-menu-item index="2-2-2" disabled class="iconfont icon-ailiaotian_AIliaotian">AI聊天</el-menu-item>
      </el-submenu>
      <el-submenu index="2-3">
        <template slot="title" class="iconfont icon-kaifarenyuan">开发者</template>
        <el-menu-item index="2-3-1" @click="toGithub" class="iconfont icon-guanli-1111">社区&共建</el-menu-item>
        <el-menu-item index="2-3-2" disabled class="iconfont icon-fuwu">面向开发者</el-menu-item>
        <el-menu-item index="2-3-3" disabled class="iconfont icon-shuomingwendang">文档中心</el-menu-item>
        <el-menu-item index="2-3-4" disabled class="iconfont icon-dc-icon-guojiazhongdianshiyanshi">实验室</el-menu-item>
        <el-menu-item index="2-3-5" disabled disabled class="iconfont icon-yijianyujianyifankui">意见&建议</el-menu-item>
        <el-menu-item index="2-3-6" disabled disabled class="iconfont icon-QAyanshou">Q&A</el-menu-item>
        <el-menu-item index="2-3-7" disabled><a href="https://www.ele.me" target="_blank"
                                                class="iconfont icon-guanyuwomen">关于我们</a>
        </el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-menu-item index="3" class="iconfont icon-blog1" @click="blog">博客</el-menu-item>
    <el-menu-item index="4" class="login-button">
      <div v-if="isLoggedIn" class="right-menu">
        <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
          <div class="avatar-wrapper">
            <img :src="userAvatar" class="user-avatar">
            <i class="el-icon-caret-bottom"/>
          </div>
          <el-dropdown-menu slot="dropdown">
            <router-link to="/user">
              <el-dropdown-item>个人信息</el-dropdown-item>
            </router-link>
            <el-dropdown-item divided @click.native="logout">
              <span>退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div v-else @click="handleLogin" class="login-text login-text-hover">登</div>
    </el-menu-item>
  </el-menu>
</template>

<script>
  import {getToken} from '@/utils/auth';
  import {mapGetters} from 'vuex'
  import Breadcrumb from '@/components/Breadcrumb'
  import TopNav from '@/components/TopNav'
  import Hamburger from '@/components/Hamburger'
  import Screenfull from '@/components/Screenfull'
  import SizeSelect from '@/components/SizeSelect'
  import Search from '@/components/HeaderSearch'
  import KitGit from '@/components/Kit/Git'
  import KitDoc from '@/components/Kit/Doc'
  import {getUserProfile} from '@/api/system/user';
  import store from '../../store'

  export default {
    name: "Head",
    components: {
      Breadcrumb,
      TopNav,
      Hamburger,
      Screenfull,
      SizeSelect,
      Search,
      KitGit,
      KitDoc
    },
    props: {
      backgroundColor: {
        type: String,
        default: 'rgba(0,0,0,0)'
      },
      textColor: {
        type: String,
        default: '#fff'
      },
      activeTextColor: {
        type: String,
        default: '#fff'
      }
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'avatar',
        'device'
      ]),
      setting: {
        get() {
          return this.$store.state.settings.showSettings
        },
        set(val) {
          this.$store.dispatch('settings/changeSetting', {
            key: 'showSettings',
            value: val
          })
        }
      },
      topNav: {
        get() {
          return this.$store.state.settings.topNav
        }
      }
    },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',

        activeMenu: '1',
        isLoggedIn: false, // 用户登录状态
        userAvatar: '', // 用户头像地址
        userName: '', // 用户名

        headers: {Authorization: null},

        loginTo: '/login'
      }
    },
    mounted() {
      this.checkLoginStatus();
    },
    methods: {
      async logout() {
        this.$confirm('确定注销并退出系统吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('LogOut').then(() => {
            location.href = '/index';
          })
        }).catch(() => {
        });
      },

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
      },
      handleLogin() {
        window.location.href = this.loginTo; // 假设登录页面为 /login
      },
      checkLoginStatus() {
        // 检查用户是否登录，可以通过检查 token 或调用后端 API
        const token = store.getters.token;// 示例中从 localStorage 获取 token
        if (token) {
          // 假设存在 token，则用户已登录，获取用户信息
          this.headers.Authorization = "Bearer " + token;
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
            if (200 === response.code) {
              this.loginTo = '/user';
              this.userAvatar = response.data.avatar;
              this.userAvatar = (this.userAvatar == "" || this.userAvatar == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + this.userAvatar;
              this.userName = response.data.nickName;
            }
          });
        }
      },
    }
  }
</script>

<style scoped lang="scss">
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
    width: 50px; /* 设置为正方形，宽度 */
    height: 50px; /* 设置为正方形，高度 */
    margin-top: 3px;
    margin-right: 3px;
    padding: 0; /* 移除默认的内边距 */
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    /*border-radius: 50%; !* 确保头像是圆形 *!*/

  }

  .login-button > .avatar-wrapper,
  .login-button > .login-text {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  /*.login-button:hover {*/
  /*  background: #303133;*/
  /*}*/

  .avatar-wrapper {
    border-radius: 10px;
    /*border-radius: 50%; !* 确保头像是圆形 *!*/
    overflow: hidden;
    background: #303133;
    transition: transform 0.5s ease;
  }

  .avatar {
    width: 100%; /* 头像填充整个容器 */
    height: 100%;
    object-fit: cover;
  }

  .login-text {
    border-radius: 10px;
    /*border-radius: 50%; !* 使登录按钮的文字容器也是圆形 *!*/
    background-color: #FFD700;
    color: rgb(84, 92, 100);
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
    line-height: 1; /* 确保文字居中 */
    transition: transform 0.5s ease;
  }

  /*.login-text-hover:hover {*/
  /*  font-weight: bolder;*/
  /*  transform: scale(1.05);*/
  /*  box-shadow: 0 0 10px 0 #FFD700;*/
  /*}*/

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

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      /*padding: 0 8px;*/
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      /*margin-right: 30px;*/

      .avatar-wrapper {
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 100%;
          height: 100%;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
</style>
