<template>
  <div class="login">
    <ByDefault v-show="'default' === this.loginType"  @login-type="readLoginType"/>
    <ByEmali v-show="'email' === this.loginType"  @login-type="readLoginType"/>
    <ByCSDN v-show="'csdn' === this.loginType"  @login-type="readLoginType"/>
    <ByGoogle v-show="'google' === this.loginType" @login-type="readLoginType"/>
    <ByGitHub v-show="'github' === this.loginType" @login-type="readLoginType"/>
    <ByWeChat v-show="'wechat' === this.loginType" @login-type="readLoginType"/>
    <!--  底部  -->
    <div class="el-login-footer unable-select-element">
      <span>Copyright © 2024.8-* seeuagain.vip All Rights Reserved</span>
    </div>
  </div>
</template>

<script>
  import ByDefault from "./register/ByDefault";
  import ByEmali from "./register/ByEmali";
  import ByCSDN from "./register/ByCSDN";
  import ByGitHub from "./register/ByGitHub";
  import ByGoogle from "./register/ByGoogle";
  import ByWeChat from "./register/ByWeChat";
  import Cookies from "js-cookie";

  export default {
    name: "Login",
    components: {ByWeChat, ByGoogle, ByGitHub, ByCSDN, ByEmali, ByDefault},
    data() {
      return {
        loginType: 'default'
      };
    },
    created() {
      const type = Cookies.get("type");
      if (type) {
        this.loginType = type;
      } else {
        this.loginType = 'default';
      }
    },
    methods:{
      readLoginType(type) {
        this.loginType = type;
      }
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">
  *{
    padding: 0;
    margin: 0;
  }
  .login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/images/login-img.png");
    background-size: cover;
  }

  .el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }

</style>
