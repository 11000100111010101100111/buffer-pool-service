<template>
  <div class="register">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title unable-select-element">注册</h3>
      <el-form-item prop="username">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="您的电子邮箱，例如：xxx@xxx.xxx">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码，必须包含大小写字母、数字、特殊字符"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="确认密码"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="code" v-if="captchaEnabled">
        <el-input
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="6位数字组成的验证码"
          style="width: 63%"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <div class="register-code">
          <el-button @click="getCode" type="primary">发送验证码</el-button>
          <!--          <img :src="codeUrl" @click="getCode" class="register-code-img"/>-->
        </div>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleRegister"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <div style="float: right;">
          <router-link class="link-type unable-select-element" :to="'/login'">使用已有账户登录</router-link>
        </div>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-register-footer unable-select-element">
      <span>Copyright © 2024.8 ~ * seeuagain.vip All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
  import {getCodeImg, register} from "@/api/login";

  export default {
    name: "Register",
    data() {
      const equalToPassword = (rule, value, callback) => {
        if (this.registerForm.password !== value) {
          callback(new Error("两次输入的密码不一致"));
        } else {
          callback();
        }
      };
      return {
        codeUrl: "",
        registerForm: {
          username: "",
          password: "",
          confirmPassword: "",
          code: "",
          uuid: ""
        },
        registerRules: {
          username: [
            {required: true, trigger: "blur", message: "请输入您的账号"},
            {
              type: 'email',
              message: '请输入正确的邮箱格式',
              trigger: ['blur', 'change']
            }
          ],
          password: [
            {required: true, trigger: "blur", message: "请输入您的密码"},
            {min: 5, max: 20, message: "用户密码长度必须介于 8 和 20 之间", trigger: "blur"},
            {
              validator: (rule, value, callback) => {
                const strongPasswordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,20}$/;

                if (!strongPasswordPattern.test(value)) {
                  callback(new Error('密码必须包含大小写字母、数字和特殊字符，且长度介于 8 和 20 之间'));
                } else {
                  callback();
                }
              },
              trigger: ['blur', 'change']
            }
          ],
          confirmPassword: [
            {required: true, trigger: "blur", message: "请再次输入您的密码"},
            {required: true, validator: equalToPassword, trigger: "blur"}
          ],
          code: [{required: true, trigger: "change", message: "请输入验证码"}]
        },
        loading: false,
        captchaEnabled: true
      };
    },
    created() {
      this.getCode();
    },
    methods: {
      getCode() {
        getCodeImg().then(res => {
          this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
          if (this.code === 200) {
            this.$message.success(res.data);
            this.registerForm.uuid = res.uuid;
          } else {
            this.$message.warning("验证码发送失败，请重试");
          }
        });
      },
      handleRegister() {
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.loading = true;
            register(this.registerForm).then(res => {
              const username = this.registerForm.username;
              this.$alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", '系统提示', {
                dangerouslyUseHTMLString: true,
                type: 'success'
              }).then(() => {
                this.$router.push("/login");
              }).catch(() => {
              });
            }).catch(() => {
              this.loading = false;
              if (this.captchaEnabled) {
                this.getCode();
              }
            })
          }
        });
      }
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">
  .register {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/images/register-img.jpg");
    background-size: cover;
  }

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .register-form {
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;
    opacity: 0.85;

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

  .register-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }

  .register-code {
    width: 33%;
    height: 38px;
    float: right;

    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }

  .el-register-footer {
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
