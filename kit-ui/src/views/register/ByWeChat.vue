<template>
  <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
    <h3 class="title unable-select-element">哈喽！你来啦？快用你的WeChat登陆</h3>

    <el-forn-item>
      <LoginType @login-type="readLoginType" init="wechat"/>
    </el-forn-item>
  </el-form>
</template>

<script>
  import Cookies from "js-cookie";
  import LoginType from "./LoginType";
    export default {
        name: "ByWeChat",
      components: {LoginType},
      data() {
        return {
          codeUrl: "",
          loginForm: {
            username: "admin",
            password: "admin123",
            rememberMe: false,
            code: "",
            uuid: ""
          },
          loginRules: {
            username: [
              {required: true, trigger: "blur", message: "请输入您的账号"}
            ],
            password: [
              {required: true, trigger: "blur", message: "请输入您的密码"}
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
        this.getCode();
        this.getCookie();
      },
      methods: {
        getCode() {
          getCodeImg().then(res => {
            this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
            if (this.captchaEnabled) {
              this.codeUrl = "data:image/gif;base64," + res.img;
              this.loginForm.uuid = res.uuid;
            }
          });
        },
        getCookie() {
          const username = Cookies.get("username");
          const password = Cookies.get("password");
          const rememberMe = Cookies.get('rememberMe')
          this.loginForm = {
            username: username === undefined ? this.loginForm.username : username,
            password: password === undefined ? this.loginForm.password : decrypt(password),
            rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
          };
        },
        handleLogin() {
          this.$refs.loginForm.validate(valid => {
            if (valid) {
              this.loading = true;
              if (this.loginForm.rememberMe) {
                Cookies.set("username", this.loginForm.username, {expires: 30});
                Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
                Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
              } else {
                Cookies.remove("username");
                Cookies.remove("password");
                Cookies.remove('rememberMe');
              }
              this.$store.dispatch("Login", this.loginForm).then(() => {
                this.$router.push({path: this.redirect || "/"}).catch(() => {
                });
              }).catch(() => {
                this.loading = false;
                if (this.captchaEnabled) {
                  this.getCode();
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

