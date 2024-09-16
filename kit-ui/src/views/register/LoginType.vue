<template>
  <el-form>
    <el-divider class="unable-select-element">更多方式</el-divider>
    <el-form-item>
      <el-row justify="center" type="flex" style="width: 100%;">
        <el-button :class="['by-pwd', {'disbaled': !support.default}, {'by-pwd-focus':init === 'default'}]" circle
                   @click="isDefault">
          <svg t="1725920844313" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
               p-id="4179" width="200" height="200">
            <path
              d="M512 1024c282.768 0 512-229.232 512-512C1024 229.232 794.768 0 512 0 229.232 0 0 229.232 0 512c0 282.768 229.232 512 512 512z m-2.752-800c39.92-0.064 78.384 15.568 107.568 43.744 29.456 29.504 45.76 70.336 45.04 112.736v8.144c-2.352 10.88-11.68 18.592-22.448 18.592s-20.096-7.728-22.448-18.592v-8.272c0-62.08-46.944-108.864-109.216-108.864-63.92 0-114 47.472-114 108.512v42.4h258.944c54.816 0.16 99.2 46.112 99.312 102.832v172c-0.112 56.72-44.512 102.656-99.312 102.768H371.36c-54.816-0.112-99.232-46.048-99.376-102.768v-172c0.112-47.552 31.904-88.736 76.704-99.36V380c-0.208-43.024 17.44-84.064 48.448-112.72A162.784 162.784 0 0 1 507.2 224z m2.88 278.096h-0.32c-42.8 0.848-78.56 33.92-84.16 77.824-5.6 43.904 20.688 85.44 61.808 97.712v35.008c1.28 6.16 4.896 11.52 10.016 14.912a22.08 22.08 0 0 0 17.248 3.264c8.96-1.952 15.952-9.184 17.824-18.448V677.44c40.8-12.544 66.752-53.92 61.168-97.584-5.584-43.664-41.024-76.64-83.584-77.76z m-0.256 46.464c22.8-0.112 41.392 18.928 41.488 42.528l-0.064 0.272a43.504 43.504 0 0 1-12.048 30.24 40.608 40.608 0 0 1-29.184 12.56c-22.784 0-41.264-19.088-41.296-42.672-0.112-23.6 18.288-42.816 41.104-42.928z"
              p-id="4180" fill="#707070"></path>
          </svg>
        </el-button>
        <el-button :class="['by-email', {'disbaled': !support.email}, {'by-email-focus':init === 'email'}]" circle
                   @click="isEmail">
          <svg t="1725919659153" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
               p-id="3083" width="200" height="200">
            <path
              d="M32.276 511.935c0 264.757 214.628 479.386 479.386 479.386s479.386-214.628 479.386-479.386c0 0 0 0 0 0 0-264.758-214.628-479.386-479.386-479.386-264.757 0-479.386 214.628-479.386 479.386z"
              fill="#D2966E" p-id="3084"></path>
            <path
              d="M514.737 888.902c17.55 0 34.543-0.982 51.097-3.015 32.592-4.004 63.487-12.099 93.594-24.905 13.523-5.75 20.037-21.203 14.682-34.887l-11.188-28.593c-5.416-13.839-20.934-20.706-34.855-15.501-19.215 7.184-40.949 13.067-62.234 16.559-13.49 2.213-26.8 3.474-39.174 3.474-127.744 0-246.97-74.944-246.97-243.564 0-189.059 143.072-311.693 286.145-311.693 172.028 0 231.641 112.414 231.641 228.235 0 86.865-45.986 144.776-97.085 144.776-34.063 0-45.987-20.439-35.769-63.021l32.42-153.99c4.36-20.708-11.44-40.181-32.602-40.181h-24.547c-14.137 0-26.217 8.805-31.057 21.231l-5.528 14.539h-1.703c-8.727-19.634-20.95-31.574-35.768-37.628-8.318-3.4-17.453-4.952-27.251-4.952-97.085 0-175.434 103.897-175.434 212.905 0 78.35 45.987 131.151 115.822 131.151 27.454 0 62.647-14.391 86.864-37.806 5.831-5.638 11.033-11.792 15.33-18.402h1.703c10.22 40.879 51.949 64.723 102.195 64.723 93.678 0 202.686-78.35 202.686-236.751 0-182.247-120.932-311.693-309.991-311.693-3.988 0-7.962 0.060-11.923 0.17-210.921 5.768-383.228 164.394-383.228 401.797-0.001 224.828 160.104 327.023 332.132 327.023zM565.834 536.487l-6.812 39.019c-18.735 25.549-32.361 34.064-47.692 34.064-25.549 0-39.174-17.883-39.174-49.394 0-45.987 27.251-112.415 71.535-112.415 8.379 0 15.985 2.557 22.143 6.812 5.107 3.529 9.22 8.222 11.923 13.627l-11.923 68.287z"
              fill="" p-id="3085"></path>
            <path
              d="M635.915 837.47c13.523-5.751 20.037-21.203 14.682-34.887l-11.188-28.592c-5.415-13.84-20.934-20.707-34.854-15.502-19.216 7.184-40.949 13.067-62.234 16.559v87.324c32.591-4.003 63.486-12.098 93.594-24.904zM554.244 136.4c-3.988 0-7.962 0.060-11.923 0.17v86.695c172.028 0 231.64 112.415 231.64 228.235 0 86.865-45.986 144.776-97.084 144.776-34.064 0-45.988-20.439-35.769-63.021l32.42-153.99c4.359-20.708-11.441-40.181-32.602-40.181h-24.547c-14.137 0.001-26.217 8.806-31.058 21.231l-5.528 14.539h-1.703c-8.726-19.634-20.95-31.574-35.767-37.628v93.835c5.106 3.529 9.22 8.222 11.923 13.627l-11.923 68.286v125.551c5.83-5.638 11.033-11.792 15.329-18.402h1.703c10.22 40.878 51.949 64.723 102.195 64.723 93.678 0 202.686-78.35 202.686-236.751-0.001-182.247-120.931-311.694-309.991-311.694z"
              fill="#8D0D38" p-id="3086"></path>
            <path
              d="M256.177 534.959c0-189.059 143.072-311.693 286.145-311.693v-86.695c-210.921 5.768-383.229 164.394-383.229 401.797 0 224.828 160.105 327.023 332.132 327.023 17.551 0 34.543-0.982 51.097-3.015v-87.324c-13.491 2.213-26.8 3.474-39.174 3.474-127.745-0.001-246.97-74.944-246.97-243.565z"
              fill="#A9294B" p-id="3087"></path>
            <path
              d="M535.508 551.992c-18.735 25.549-32.362 34.064-47.69 34.064-25.55 0-39.173-17.883-39.173-49.394 0-45.987 27.25-112.415 71.535-112.415 8.379 0 15.985 2.557 22.143 6.812v-93.835c-8.319-3.4-17.453-4.953-27.251-4.953-97.086 0-175.434 103.898-175.434 212.906 0 78.349 45.987 131.15 115.821 131.15 27.455 0 62.647-14.391 86.865-37.806v-125.55l-6.815 39.019z"
              fill="#A9294B" p-id="3088"></path>
          </svg>
        </el-button>
        <el-button :class="['by-csdn', {'disbaled': !support.csdn}, {'by-csdn-focus': init === 'csdn'}]" circle
                   @click="isCSDN">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-csdn"></use>
          </svg>
        </el-button>
        <el-button :class="['by-github', {'disbaled': !support.github}, {'by-github-focus': init === 'github'}]" circle
                   @click="isGitHub">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-github-fill"></use>
          </svg>
        </el-button>
        <el-button :class="['by-google', {'disbaled': !support.google}, {'by-google-focus': init === 'google'}]" circle
                   @click="isGoogle">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-google"></use>
          </svg>
        </el-button>
        <el-button :class="['by-wechat', {'disbaled': !support.wechat}, {'by-wechat-focus':init === 'wechat'}]" circle
                   @click="isWeChat">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-weixin"></use>
          </svg>
        </el-button>
      </el-row>
    </el-form-item>
    <el-form-item>
      <div style="float: right;">
        <router-link class="link-type" :to="'../register'">我要注册</router-link>
      </div>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "LoginType",
    props: {
      init: {
        type: String,
        required: true,
      }
    },
    data() {
      return {
        type: 'default',
        support: {
          'default': true,
          'email': true,
          'google': false,
          'wechat': false,
          'github': false,
          'csdn': false
        }
      };
    },
    mounted() {

    },
    methods: {
      isDefault() {
        if (!this.support.default) {
          this.$message.info("使用账号密码登陆的方式暂时未开放");
          return;
        }
        this.updateValue('default');
      },
      isCSDN() {
        if (!this.support.csdn) {
          this.$message.info("使用CSDN登陆的方式暂时未开放");
          return;
        }
        this.updateValue('csdn');
      },
      isEmail() {
        if (!this.support.email) {
          this.$message.info("使用邮箱登陆的方式暂时未开放");
          return;
        }
        this.updateValue('email');
      },
      isGitHub() {
        if (!this.support.github) {
          this.$message.info("使用GitHub登陆的方式暂时未开放");
          return;
        }
        this.updateValue('github');
      },
      isGoogle() {
        if (!this.support.google) {
          this.$message.info("使用Google登陆的方式暂时未开放");
          return;
        }
        this.updateValue('google');
      },
      isWeChat() {
        if (!this.support.wechat) {
          this.$message.info("使用微信登陆的方式暂时未开放");
          return;
        }
        this.updateValue('wechat');
      },
      updateValue(value) {
        this.type = value;
        this.$emit('login-type', value);
      }
    }
  }
</script>

<style scoped>
  .by-pwd-focus {
    box-shadow: 0 0 5px 0 #707070;
  }

  .by-email-focus {
    box-shadow: 0 0 5px 0 #cc9876;
  }

  .by-csdn-focus {
    box-shadow: 0 0 5px 0 #d72f22;
  }

  .by-github-focus {
    box-shadow: 0 0 5px 0 #6c6d70;
  }

  .by-google-focus {
    box-shadow: 0 0 5px 0 #1ab394;
  }

  .by-wechat-focus {
    box-shadow: 0 0 5px 0 #32c258;
  }

  .disbaled {
    cursor: not-allowed;
  }
</style>
