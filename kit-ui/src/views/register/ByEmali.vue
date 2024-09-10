<template>
  <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
    <h3 class="title unable-select-element">哈喽！你来啦？快用你的邮箱登陆</h3>
    <el-form-item prop="username">
      <el-input
        v-model="loginForm.username"
        type="text"
        auto-complete="off"
        placeholder="您的电子邮箱，例如：xxx@xxx.xxx"
      >
        <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
      </el-input>
    </el-form-item>
    <el-form-item prop="code" v-if="captchaEnabled">
      <el-input
        v-model="loginForm.code"
        auto-complete="off"
        placeholder="邮箱验证码"
        style="width: 63%"
        @keyup.enter.native="handleLogin"
      >
      </el-input>
      <div class="login-code">
        <el-button @click="getCode" type="primary">发送验证码</el-button>
      </div>
    </el-form-item>
    <el-form-item style="width:100%;">
      <el-button
        :loading="loading"
        size="medium"
        type="primary"
        style="width:100%;"
        @click.native.prevent="handleLogin"
      >
        <span v-if="!loading">登 录</span>
        <span v-else>登 录 中...</span>
      </el-button>
    </el-form-item>
    <el-forn-item>
      <LoginType @login-type="readLoginType" init="email"/>
    </el-forn-item>
  </el-form>
</template>

<script>
  import Cookies from "js-cookie";
  import LoginType from "./LoginType";
  import {getEmailCode} from '@/api/login'
    export default {
        name: "ByEmali",
      components: {LoginType},
      data() {
        return {
          codeUrl: "",
          loginForm: {
            username: "",
            code: "",
            uuid: "",
            type: 'email'
          },
          loginRules: {
            username: [
              {required: true, trigger: "blur", message: "请输入您的账号"},{
                type: 'email',
                message: '请输入正确的邮箱格式',
                trigger: ['blur', 'change']
              }
            ],
            code: [{required: true, trigger: "change", message: "请输入验证码"}]
          },
          loading: false,
          // 验证码开关
          captchaEnabled: true,
          // 注册开关
          register: true,
          redirect: undefined
        };
      },
      watch: {
        $route: {
          handler: function (route) {
            this.redirect = route.query && route.query.redirect;
          },
          immediate: true
        }
      },
      created() {
        this.getCookie();
      },
      methods: {
        getCode() {
          this.$refs.loginForm.validate(valid => {
            if (valid) {
              getEmailCode(this.loginForm.username).then(res => {
                this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
                if (this.code === 200) {
                  this.$message.success(res.data);
                  this.registerForm.uuid = res.uuid;
                } else {
                  this.$message.warning("验证码发送失败，请重试");
                }
              });
            }
          });
        },
        getCookie() {
          const username = Cookies.get("username");
          this.loginForm = {
            username: username === undefined ? this.loginForm.username : username,
          };
        },
        handleLogin() {
          this.$refs.loginForm.validate(valid => {
            if (valid) {
              this.loading = true;
              Cookies.set("username", this.loginForm.username, {expires: 30});

              this.$store.dispatch("Login", this.loginForm).then(() => {
                this.$router.push({path: this.redirect || "/"}).catch(() => {
                });
              }).catch(() => {
                this.loading = false;
                if (this.captchaEnabled) {
                  this.$message.warning("验证码有误，请重试");
                }
              });
            }
          });
        },
        readLoginType(data) {
          this.$emit('login-type', data); // 将数据传递给祖先组件
        }
      }
    }
</script>

<style rel="stylesheet/scss" lang="scss">
  *{
    padding: 0;
    margin: 0;
  }

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .login-form {
    opacity: 0.9;
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;

    .el-input {
      height: 38px;

      input {
        height: 38px;
      }
    }

    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }

  .login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }

  .login-code {
    width: 33%;
    height: 38px;
    float: right;

    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }

  .login-code-img {
    height: 38px;
  }

</style>

