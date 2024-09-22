<template>
  <el-container class="el-container">
    <div class="head-top">
      <div class="logo unable-select-element">
        <p class="logo-font unable-select-element" v-for="l in text.logo" :key="l.key">{{l.value}}</p>
        <p class="logo-font divider-span unable-select-element">+</p>
      </div>
    </div>
    <el-header class="el-header">
      <div class="contain">
        <div class="head-bottom">
          <div class="menu">
            <div class="menu-item go-more">
              <button class="go-more-button" @click="openMenu">
                <div class="go-for-it unable-select-element">
                  <span class="font-go-for-it unable-select-element"
                        v-for="char in menu.showChar" :key="char.key">{{char.first ? ' ': ''}}{{char.label}}</span>
                </div>
              </button>
            </div>
            <div class="menu-item login">
              <div class="has-login"
                   v-if="userInfo.beLogin"
                   @mouseenter="onUserInfoMouseEnter"
                   @mouseleave="onUserInfoMouseLeave">
                <canvas ref="userInfoCanvas" class="user-info"/>
              </div>
              <botton class="wait-login" v-if="!userInfo.beLogin" @click="handleLogin">
                <svg t="1726992272492" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4961" width="200" height="200"><path d="M928 512c0 229.76-186.24 416-416 416S96 741.76 96 512 282.24 96 512 96s416 186.24 416 416zM480 256v192h64V256h-64z m-160 256a191.872 191.872 0 0 1 93.088-164.576l-33.056-54.848a256 256 0 1 0 263.904 0l-32.992 54.816A192 192 0 1 1 320 512z" fill="#BE3E50" p-id="4962"></path></svg>
              </botton>
            </div>
            <div class="menu-item sound-switch"
                 @click="switchRound"
                 @mouseenter="onSoundMouseEnter"
                 @mouseleave="onSoundMouseLeave">
              <canvas ref="soundCanvas" class="sound-open"/>
            </div>
          </div>
        </div>
        <my-menu class="my-menu" v-if="menu.show"></my-menu>
      </div>

    </el-header>
    <el-main class="el-main" style="height: auto;">
      <ul class="ul-panel" style="height: auto">
        <li class="li-panel top-li">
        </li>
        <li class="li-panel video-li">
          <div class="first-contais">
            <div>
              <div class="video-container" @mouseenter="hoverVideoMenu" @mouseleave="leaveVideoMenu">
                <canvas ref="videoCanvas" class="video-canvas"/>
                <div :class="['video-play-icon', videoInfo.needShowVideoMenu ? 'show-menu' : 'un-show-menu']">
                  <svg v-if="videoInfo.needShowVideoMenu" @click="playLastVideo" t="1727021769483" :class="['icon','last-video', videoInfo.needShowVideoMenu ? 'show-menu' : 'un-show-menu']" viewBox="0 0 1171 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9175" width="200" height="200"><path d="M49.486529 0.618582a49.486529 49.486529 0 0 0-49.486529 49.486529v923.789778a49.486529 49.486529 0 0 0 98.973058 0V50.105111a49.486529 49.486529 0 0 0-49.486529-49.486529zM872.818654 27.58874l-533.217349 313.744593a197.946116 197.946116 0 0 0 0 341.333334l533.217349 313.744593a197.946116 197.946116 0 0 0 298.40377-170.728525V197.946116A197.946116 197.946116 0 0 0 872.818654 27.58874z m199.430712 798.093995v8.783859a98.973058 98.973058 0 0 1-148.459587 76.580404L389.706415 597.302404l-8.165277-5.319802a98.973058 98.973058 0 0 1 8.165277-165.285006L923.047481 112.953002l7.670412-4.082638A98.973058 98.973058 0 0 1 1072.249366 197.946116z" p-id="9176" fill="#BE3E50"></path></svg>
                  <svg v-if="videoInfo.needShowVideoMenu && !videoPlay" @click="clickToPlayVideo" t="1726998892925" :class="['icon','play-video', videoInfo.needShowVideoMenu ? 'show-menu' : 'un-show-menu']" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6102" width="200" height="200"><path d="M772.7 217.7a32.2 32.1 0 1 0 64.4 0 32.2 32.1 0 1 0-64.4 0Z" fill="#BE3E50" p-id="6103"></path><path d="M415.8 679.9c5.9 0 11.5-1.6 16.2-4.5l231.1-134.6c10.9-5.2 18.5-16.3 18.5-29.2 0-11.9-6.4-22.3-16-27.8L439.7 352.2c-5.8-6.7-14.4-10.9-23.9-10.9-17.6 0-31.8 14.4-31.8 32.1 0 0.6 0 1.2 0.1 1.8l-0.4 0.2 0.5 269c-0.1 1.1-0.2 2.2-0.2 3.4 0 17.7 14.3 32.1 31.8 32.1z" fill="#BE3E50" p-id="6104"></path><path d="M909.8 306.6c-5.4-10.5-16.3-17.8-28.9-17.8-17.8 0-32.2 14.4-32.2 32.1 0 6 1.7 11.7 4.6 16.5l-0.1 0.1c26.9 52.4 42.1 111.8 42.1 174.7 0 211.6-171.6 383.2-383.2 383.2S128.8 723.8 128.8 512.2 300.4 129.1 512 129.1c62.5 0 121.5 15 173.6 41.5l0.2-0.4c4.6 2.6 10 4.1 15.7 4.1 17.8 0 32.2-14.4 32.2-32.1 0-13.1-7.9-24.4-19.3-29.4C653.6 81.9 584.9 64.5 512 64.5 264.7 64.5 64.3 265 64.3 512.2S264.7 959.9 512 959.9s447.7-200.4 447.7-447.7c0-74.1-18-144-49.9-205.6z" fill="#BE3E50" p-id="6105"></path></svg>
                  <svg v-if="videoInfo.needShowVideoMenu && videoPlay" @click="clickToPauseVideo" t="1727022666759" :class="['icon','pause-video', videoInfo.needShowVideoMenu ? 'show-menu' : 'un-show-menu']" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1815" width="200" height="200"><path d="M247.731946 0a49.546389 49.546389 0 0 0-49.546389 49.546389v924.907222a49.546389 49.546389 0 0 0 99.092779 0V49.546389a49.546389 49.546389 0 0 0-49.54639-49.546389zM776.268054 0a49.546389 49.546389 0 0 0-49.54639 49.546389v924.907222a49.546389 49.546389 0 0 0 99.092779 0V49.546389a49.546389 49.546389 0 0 0-49.546389-49.546389z" p-id="1816" fill="#BE3E50"></path></svg>
                  <svg v-if="videoInfo.needShowVideoMenu" @click="playNextVideo" t="1727021849924" :class="['icon','next-video', videoInfo.needShowVideoMenu ? 'show-menu' : 'un-show-menu']" viewBox="0 0 1171 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1599" width="200" height="200"><path d="M1121.735895 0.618582a49.486529 49.486529 0 0 0-49.486529 49.486529v923.789778a49.486529 49.486529 0 0 0 98.973058 0V50.105111a49.486529 49.486529 0 0 0-49.486529-49.486529zM831.621119 341.333333L298.280053 27.58874l-9.649873-5.319802A197.946116 197.946116 0 0 0 0 197.946116v638.871088a197.946116 197.946116 0 0 0 298.280053 159.47034l533.341066-313.744594 11.134469-7.05183a197.946116 197.946116 0 0 0-11.134469-334.157787z m-49.486529 255.969071L248.051226 911.046998a98.973058 98.973058 0 0 1-148.459587-85.364263V197.946116a98.973058 98.973058 0 0 1 148.459587-84.993114l533.464782 313.744594a98.973058 98.973058 0 0 1 0 170.604808z" p-id="1600" fill="#BE3E50"></path></svg>
                </div>
              </div>
              <div class="card-description">
                <div class="card-text-font unable-select-element">{{text.videoTitle}}</div>
                <div class="card-title">
                  <span class="card-title-font unable-select-element" :class="[font.isUpper ? 'upperChar' : '']" v-for="font in text.video" :key="font.key">{{font.value}}</span>
                </div>
              </div>
            </div>
          </div>
          <el-divider style="margin-left: 0.5rem;margin-right: 0.5rem"><span class="divider-span unable-select-element">+</span></el-divider>
          <div class="main-description">
            <div class="title-description">
              <h2 class="unable-select-element" v-for="title in text.title">{{title.value}}</h2>
            </div>
            <el-divider style="margin-left: 0.5rem;margin-right: 0.5rem" direction="vertical"></el-divider>
            <div class="text-description">
              <div v-for="line in text.description">
                <p class="unable-select-element text-description-p" v-if="line.type === 'text'">{{line.value}}</p>
                <a v-else-if="line.type === 'url'" class="a-text unable-select-element" :href="line.src"
                   target="_blank">{{line.value}}</a>
              </div>
            </div>
          </div>
        </li>
        <li class="li-panel support-li" style="height: auto;">
          <div class="support-title-con" style="margin-bottom: 1rem">
            <div class="support-title">
              <div v-for="t in text.text2" :class="[t.isUpper ? 'upperChar' : '']" class="unable-select-element">{{t.value}}</div>
            </div>
          </div>
          <el-row :gutter="0" offset="0" justify="center">
            <el-col class="support-item-col"
                    v-for="img in support" :key="img.key"
                    :xs="24" :sm="24" :md="12" :lg="12" :xl="12" >
              <img3D :src="img.src" :title="img.title" :text="img.message"/>
            </el-col>
          </el-row>
        </li>
        <li class="li-panel about-auth">
          <about-auth :auth-info="auth"/>
        </li>
        <li class="li-panel end-li">

        </li>
      </ul>
    </el-main>
  </el-container>
</template>

<script>
  import {mapGetters} from "vuex";
  import store from "../store";
  import {getUserProfile} from '@/api/system/user';
  import img3D from "./common/img3D";
  import AboutAuth from "./common/aboutAuth";
  import MyMenu from "./common/MyMenu";
  import MyVideo from "../views/tool/kit/video/MyVideo";

  export default {
    name: "homeIndex",
    components: {
      MyVideo,
      MyMenu,
      AboutAuth,
      img3D
    },
    data() {
      return {
        menu:{
          openChar:[
            {key:1, label: 'G', first: false},
            {key:2, label: 'O', first: false},
            {key:3, label: 'F', first: true},
            {key:4, label: 'O', first: false},
            {key:5, label: 'R', first: false},
            {key:6, label: 'I', first: true},
            {key:7, label: 'T', first: false}
          ],
          closeChar:[
            {key:1, label: 'C', first: false},
            {key:2, label: 'L', first: false},
            {key:3, label: 'O', first: false},
            {key:4, label: 'S', first: false},
            {key:5, label: 'E', first: false},
            {key:6, label: 'N', first: true},
            {key:7, label: 'O', first: false},
            {key:8, label: 'W', first: false}
          ],
          show: false,
          showChar: [],
        },
        auth: {
          title: 'ABOUT ME',
          url: 'https://lh3.googleusercontent.com/a/ACg8ocJI_t-HovExKI47DG0NegpqCVu72dtZNTUhDzZ4a3yCbHkpTGA=s576-c-no',
          info: [
            {title: 'Real Name', key:1, label: 'XiaoJiaHai'},
            {title: 'Nick Name', key:2, label: 'Gavin\'Xiao'},
            {title: 'Pers Skil', key:3, label: 'Understand stories related to Java'}
          ],
          message: 'Has high self requirements for coding\r\n' +
            'As you can see, IKun is skilled in singing, dancing, rap, basketball, even though he doesn\'t know how to do it'
        },
        rootFontSize: 0,
        videoInfo: {
          srcMatch: '/video/video',
          currentVideoIndex:0,
          transitionDuration: 1000,  // 过渡时长
          fadeOpacity: 1,  // 视频透明度
          video: null, // 用于存储视频元素
          videoSrc: [],
          needShowVideoMenu: true
        },
        userInfo: {
          beLogin: false,
          activeMenu: '1',
          userAvatar: require("@/assets/images/profile.jpg"), // 用户头像地址
          userName: '', // 用户名
          loginTo: '/login',
          headers: {Authorization: null},
        },

        intoUserInfo: false,
        userInfoConfig: {
          lineColor: 'rgb(190,62,80)',
          lineWidth: 0,
          maxLineWidth: 0,
          minLineWidth: 0
        },
        soundOpen: false,
        videoPlay: true,
        firstIn:true,

        soundSince: {
          maxAmplitude: 16,
          amplitude: 16,
          frequency: 0.16,
          phase: 2,
          lineColor: 'rgb(190,62,80)'
        },
        support: [
          {key: 1, src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg', title: 'Weather In Map', message: 'You can perform weather queries on the map for the cities you see, including city/county level and current and future weather conditions'},
          {key: 2, src: 'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg', title: 'From Text to Image by AIGC', message: 'After you input some descriptive text, AI can help you generate an image, which may take you 2-5 minutes to wait. I guess you will like it, right'},
          {key: 3, src: 'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg', title: 'Blog of Individual or Public', message: 'Anyone can write a blog, including Markdown files. This feature will be launched soon, and you are looking forward to it'},
          {key: 4, src: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg', title: 'More and More in the near Future', message: 'By the way, I almost forgot to tell you that there will be more small features or plugins here, just wait and see'},
        ],
        text: {
          logo: [
            {key: 1, value: 'B'},
            {key: 2, value: 'u'},
            {key: 3, value: 'f'},
            {key: 4, value: 'f'},
            {key: 5, value: 'e'},
            {key: 6, value: 'r'},
            {key: 7, value: '\''},
            {key: 8, value: 'P'},
            {key: 9, value: 'o'},
            {key: 10, value: 'o'},
            {key: 11, value: 'l'},
          ],
          videoTitle: 'Video design by canvas • circular button in the rightmost circular button in the upper left corner can turn/off the sound',
          video: [
            {key: '1', value: 'A', isUpper: true},
            {key: '2', value: 'p', isUpper: false},
            {key: '3', value: 'p', isUpper: false},
            {key: '4', value: 'r', isUpper: false},
            {key: '5', value: 'e', isUpper: false},
            {key: '6', value: 'c', isUpper: false},
            {key: '7', value: 'i', isUpper: false},
            {key: '8', value: 'a', isUpper: false},
            {key: '9', value: 't', isUpper: false},
            {key: '10', value: 'e', isUpper: false},
            {key: '11', value: ' ', isUpper: false},
            {key: '121', value: 'S', isUpper: true},
            {key: '122', value: 'o', isUpper: false},
            {key: '123', value: 'm', isUpper: false},
            {key: '124', value: 'e', isUpper: false},
            {key: '13', value: ' ', isUpper: false},
            {key: '14', value: 'S', isUpper: true},
            {key: '15', value: 'h', isUpper: false},
            {key: '16', value: 'o', isUpper: false},
            {key: '17', value: 'r', isUpper: false},
            {key: '18', value: 't', isUpper: false},
            {key: '19', value: ' ', isUpper: false},
            {key: '20', value: 'V', isUpper: true},
            {key: '21', value: 'i', isUpper: false},
            {key: '22', value: 'd', isUpper: false},
            {key: '23', value: 'e', isUpper: false},
            {key: '24', value: 'o', isUpper: false},
          ],
          title: [
            {key: '1', value: 'G'},
            {key: '2', value: 'E'},
            {key: '3', value: 'T'},
            {key: '4', value: ' '},
            {key: '5', value: 'S'},
            {key: '6', value: 'T'},
            {key: '7', value: 'A'},
            {key: '8', value: 'R'},
            {key: '9', value: 'T'},
          ],
          description: [
            {key: '1', type: 'text', value: "Welcome to visit Buffer'Pool!"},
            {
              key: '2',
              type: 'text',
              value: "This is a space for me to share my life experiences, learning insights, and technical insights"
            },
            {
              key: '3',
              type: 'text',
              value: "In the blog, you will see original content about programming, reading notes, travel experiences, and more"
            },
            {
              key: '4',
              type: 'text',
              value: "I hope to provide some inspiration and assistance to visitors through these sharing experiences"
            },
            {
              key: '5',
              type: 'text',
              value: "Whether it's exchanging ideas or exploring new areas of knowledge together, this is an open platform for discussion"
            },
            {
              key: '6',
              type: 'text',
              value: "Looking forward to your reading and interaction! Looking forward to your reading and interaction!"
            },
            {key: '7', type: 'text', value: "More content will be added here indefinitely"},
            {key: '8', type: 'text', value: "If it's important to you"},
            {
              key: '9',
              type: 'text',
              value: "Please remember to have more GitHub addresses, as the website's IP address may be changed in the near future"
            },
            {
              key: '10',
              type: 'url',
              value: "Access GitHub",
              src: "https://github.com/11000100111010101100111/SmallQuestionNotes"
            }
          ],
          text2: [
            {key: 1, value: 'Now', isUpper: true},
            {key: 2, value: 'We', isUpper: true},
            {key: 3, value: 'Support', isUpper: true}
          ]
        },
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
      },
    },
    methods: {
      initVideoList() {
        this.videoInfo = {
          needShowVideoMenu: true,
            srcMatch: '/video/video',
            currentVideoIndex: 0,
            transitionDuration: 3000,  // 过渡时长
            fadeOpacity: 1,  // 视频透明度
            video: null, // 用于存储视频元素
            videoSrc: [
            {src: '/video/ikun.mp4'},
            {src: '/video/ikun_01.mp4'},
            {src: '/video/video_02.mp4'},
            {src: '/video/video_03.mp4'},
            {src: '/video/video_04.mp4'},
            {src: '/video/video_05.mp4'},
            {src: '/video/video_06.mp4'},
          ],
        };
      },
      openMenu() {
        this.menu.show = !this.menu.show;
        this.menu.showChar = this.menu.show ? this.menu.closeChar : this.menu.openChar;
      },
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
      handleLogin() {
        window.location.href = this.userInfo.loginTo; // 假设登录页面为 /login
      },
      switchRound() {
        this.soundOpen = !this.soundOpen;
        this.videoInfo.video.muted = !this.soundOpen;
      },
      drawSineWave() {
        const width = this.canvas.width;
        const height = this.canvas.height;
        const centerY = height / 2;
        this.ctx.clearRect(-10, -10, width + 10, height + 10);
        this.ctx.beginPath();
        this.ctx.moveTo(0, centerY);
        for (let x = 0; x < width; x++) {
          const y = centerY + this.soundSince.amplitude * Math.sin((x * this.soundSince.frequency) + this.soundSince.phase);
          this.ctx.lineTo(x, y);
        }
        this.ctx.strokeStyle = this.soundSince.lineColor;
        this.ctx.stroke();
      },
      animateSineWave() {
        this.drawSineWave();
        this.soundSince.phase += 0.08;
        if (!this.soundOpen) {
          if (this.soundSince.amplitude > 0) {
            this.soundSince.amplitude -= 0.5;
          } else {
            this.soundSince.amplitude = 0;
          }
        } else {
          if (this.soundSince.amplitude < this.soundSince.maxAmplitude) {
            this.soundSince.amplitude += 0.5;
          } else {
            this.soundSince.amplitude = this.soundSince.maxAmplitude;
          }
        }
        requestAnimationFrame(this.animateSineWave);
      },
      onSoundMouseEnter() {
        this.soundSince.lineColor = '#fff';
      },
      onSoundMouseLeave() {
        this.soundSince.lineColor = 'rgb(190,62,80)';
      },
      checkLoginStatus() {
        // 检查用户是否登录，可以通过检查 token 或调用后端 API
        const token = store.getters.token;// 示例中从 localStorage 获取 token
        if (token) {
          // 假设存在 token，则用户已登录，获取用户信息
          this.userInfo.headers.Authorization = "Bearer " + token;
          this.userInfo.beLogin = true;
          this.getUserInfo();
        } else {
          this.userInfo.beLogin = false;
        }
      },
      getUserInfo() {
        // 调用后端 API 获取用户信息
        // 示例中使用了 async/await 和 fetch API，你可以根据你的项目配置使用 axios 或其他请求库
        if (this.userInfo.headers.Authorization) {
          getUserProfile().then(response => {
            if (200 === response.code) {
              this.userInfo.loginTo = '/user';
              this.userInfo.userAvatar = response.data.avatar;
              this.userInfo.userAvatar = (this.userInfo.userAvatar == "" || this.userInfo.userAvatar == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + this.userInfo.userAvatar;
              this.userInfo.userName = response.data.nickName;
            }
          });
        }
      },
      initSound() {
        this.canvas = this.$refs.soundCanvas;
        this.ctx = this.canvas.getContext('2d');
        this.ctx.imageSmoothingEnabled = true;
        this.canvas.width = this.$refs.soundCanvas.parentElement.clientWidth;
        this.canvas.height = this.$refs.soundCanvas.parentElement.clientHeight;
        this.ctx.lineWidth = 0.3 * this.rootFontSize;
        this.animateSineWave();
      },
      initUserInfo() {
        if (!this.userInfo.beLogin) {
          return
        }
        this.userInfo.canvas = this.$refs.userInfoCanvas;
        this.userInfo.ctx = this.userInfo.canvas.getContext('2d');
        this.userInfo.ctx.imageSmoothingEnabled = true;
        this.userInfo.canvas.width = this.$refs.userInfoCanvas.parentElement.clientWidth;
        this.userInfo.canvas.height = this.$refs.userInfoCanvas.parentElement.clientHeight;
        this.userInfo.ctx.lineWidth = 3 * this.rootFontSize;
        this.userInfoConfig.maxLineWidth = this.userInfo.ctx.lineWidth;
        this.userInfoConfig.minLineWidth = this.userInfo.ctx.lineWidth / 9;
        this.drawRoundedLine();
      },
      drawOneRoundedLine(local, type, color) {
        this.userInfo.ctx.lineCap = type;
        this.userInfo.ctx.strokeStyle = color;
        this.userInfo.ctx.moveTo(local.sx, local.sy);
        this.userInfo.ctx.lineTo(local.ex, local.ey);
      },
      drawRoundedLine() {
        this.userInfo.ctx.clearRect(0, 0, this.userInfo.canvas.width, this.userInfo.canvas.height);
        if (!this.intoUserInfo) {
          if (this.userInfoConfig.lineWidth > this.userInfoConfig.minLineWidth) {
            this.userInfoConfig.lineWidth -= 0.5;
          } else {
            this.userInfoConfig.lineWidth = this.userInfoConfig.minLineWidth;
          }
        } else {
          if (this.userInfoConfig.lineWidth < this.userInfoConfig.maxLineWidth) {
            this.userInfoConfig.lineWidth += 0.5;
          } else {
            this.userInfoConfig.lineWidth = this.userInfoConfig.maxLineWidth;
          }
        }
        this.userInfo.ctx.lineWidth = this.userInfoConfig.lineWidth;

        const local = {
          top: {
            w: this.userInfo.canvas.width,
            sx: 0,
            sy: this.userInfo.ctx.lineWidth,
            ex: this.userInfo.canvas.width,
            ey: this.userInfo.ctx.lineWidth
          },
          mid: {
            w: this.userInfo.canvas.width,
            sx: 0,
            sy: this.userInfo.canvas.height / 2,
            ex: this.userInfo.canvas.width,
            ey: this.userInfo.canvas.height / 2
          },
          bottom: {
            w: this.userInfo.canvas.width,
            sx: 0,
            sy: this.userInfo.canvas.height - this.userInfo.ctx.lineWidth,
            ex: this.userInfo.canvas.width,
            ey: this.userInfo.canvas.height - this.userInfo.ctx.lineWidth
          }
        };


        this.userInfo.ctx.beginPath();
        this.drawOneRoundedLine(local.top, 'round', '#fff');
        this.drawOneRoundedLine(local.mid, 'square', '#fff');
        this.drawOneRoundedLine(local.bottom, 'round', '#fff');
        this.userInfo.ctx.stroke();

        // if ( this.userInfoConfig.lineWidth=== this.userInfoConfig.minLineWidth
        //   || this.userInfoConfig.lineWidth === this.userInfoConfig.maxLineWidth) {
        //   return
        // }
        requestAnimationFrame(this.drawRoundedLine);
      },
      onUserInfoMouseEnter() {
        this.intoUserInfo = true;
        this.userInfoConfig.lineColor = '#fff';
      },
      onUserInfoMouseLeave() {
        this.intoUserInfo = false;
        this.userInfoConfig.lineColor = '#fff';
      },


      initVideo() {
        this.videoInfo.video = document.createElement('video');
        this.videoInfo.video.src = this.videoInfo.srcMatch + '_' + this.videoInfo.currentVideoIndex+'.mp4';//this.videoInfo.videoSrc[0].src;
        this.videoInfo.video.autoplay = true;
        this.videoInfo.video.loop = false;
        this.videoInfo.video.muted = !this.soundOpen;

        this.videoInfo.video.addEventListener('loadedmetadata', () => {
          const canvas = this.$refs.videoCanvas;
          canvas.width = this.videoInfo.video.videoWidth;
          canvas.height = this.videoInfo.video.videoHeight;
        });

        this.videoInfo.video.addEventListener('ended', this.fadeOutAndNextVideo);
        this.videoInfo.video.addEventListener('error', () => {
          this.videoInfo.currentVideoIndex = -1;
          this.fadeOutAndNextVideo();
        });

        this.videoInfo.video.addEventListener('play', () => {
          const canvas = this.$refs.videoCanvas;
          const context = canvas.getContext('2d');
          this.drawFrame(context);
        });
      },
      fadeOutAndNextVideo() {
        this.play(true);
      },
      play(isNext) {
        this.videoInfo.fadeOpacity = 1;
        const fadeOutInterval = setInterval(() => {
          this.videoInfo.fadeOpacity -= 0.05;
          if (this.videoInfo.fadeOpacity <= 0) {
            clearInterval(fadeOutInterval);
            if (isNext) {
              this.playNextVideo();
            } else {

            }
          }
        }, this.videoInfo.transitionDuration / 50);
      },
      playVideoAfterHandleCurrentVideoIndex() {
        let src = this.videoInfo.srcMatch + '_' + this.videoInfo.currentVideoIndex+'.mp4';
        this.videoInfo.video.src = src;//this.videoInfo.videoSrc[this.videoInfo.currentVideoIndex].src;

        // 开始淡入
        this.videoInfo.fadeOpacity = 0;
        const fadeInInterval = setInterval(() => {
          this.videoInfo.fadeOpacity += 0.05;  // 每次增加透明度
          if (this.videoInfo.fadeOpacity >= 1) {
            clearInterval(fadeInInterval);  // 完成淡入
          }
        }, this.videoInfo.transitionDuration / 50);

        this.videoInfo.video.play();
        this.videoPlay = true;
      },
      playNextVideo () {
        this.videoPlay = true;
        this.videoInfo.currentVideoIndex++;
        this.playVideoAfterHandleCurrentVideoIndex();
      },
      playLastVideo () {
        if (this.videoInfo.currentVideoIndex <= 0) {
          this.$message.warning("已经是最开始的视频了，不能再退了喔");
          return;
        }
        this.videoPlay = true;
        this.videoInfo.currentVideoIndex--;
        this.playVideoAfterHandleCurrentVideoIndex();
      },
      drawFrame(context) {
        if (!this.videoInfo.video.paused && !this.videoInfo.video.ended) {
          context.drawImage(this.videoInfo.video, 0, 0, context.canvas.width, context.canvas.height);
          context.globalAlpha = this.videoInfo.fadeOpacity;  // 应用透明度
          context.drawImage(this.videoInfo.video, 0, 0, context.canvas.width, context.canvas.height);
          requestAnimationFrame(() => this.drawFrame(context));
        }
      },
      clickToPlayVideo() {
        this.playVideo();
      },
      clickToPauseVideo() {
        this.videoInfo.video.pause();
        this.videoPlay = false;
      },
      playVideo() {
        this.videoInfo.video.play();
        this.videoPlay = true;
        this.videoInfo.needShowVideoMenu = !this.videoPlay;
      },
      hoverVideoMenu(e) {
        this.videoInfo.needShowVideoMenu = true;
      },
      leaveVideoMenu(e) {
        this.videoInfo.needShowVideoMenu = false;
      },
    },
    mounted() {
      this.videoInfo.needShowVideoMenu = true;
      this.initVideoList();
      this.menu.showChar = this.menu.show ? this.menu.closeChar : this.menu.openChar;
      this.rootFontSize= parseFloat(getComputedStyle(this.$root.$el).fontSize);
      this.initVideo();
      this.initSound();
      this.initUserInfo();
      this.videoInfo.video.play();
    },
    beforeDestroy() {
      this.videoInfo.video.removeEventListener('play', this.drawFrame);
      this.videoInfo.video.removeEventListener('error');
      this.videoInfo.video.removeEventListener('ended', this.playNextVideo);
      this.videoInfo.video.removeEventListener('loadedmetadata');
      this.videoInfo.video.pause();
      this.videoInfo.video.src = '';
      this.videoInfo.video.load();
    }
  }
</script>

<style scoped lang="scss">
  * {
    margin: 0;
    padding: 0;

  }

  .upperChar {
    margin-left: 1rem;
  }


  /* 整个滚动条 */
  ::-webkit-scrollbar {
    background: rgb(190, 62, 80);
    width: 1rem; /* 设置滚动条的宽度 */
  }

  /* 滚动条轨道 */
  .el-container::-webkit-scrollbar-track {
    background: rgba(0,0,0,0); /* 滚动条轨道的颜色 */
    border-radius: 1rem; /* 圆角 */
  }

  /* 滚动条滑块 */
  .el-container::-webkit-scrollbar-thumb {
    background: rgb(190, 62, 80); /* 滚动条滑块的颜色 */
    border-radius: 1rem; /* 圆角 */
  }

  /* 滚动条滑块在悬停时 */
  .el-container::-webkit-scrollbar-thumb:hover {
    background: rgb(190, 62, 80); /* 悬停时的颜色 */
  }

  .el-container {
    scrollbar-width: thin; /* 'auto' 或 'thin' */
    scrollbar-color: rgb(190, 62, 80) #f1f1f1; /* 滚动条颜色和轨道颜色 */
  }


  ::-webkit-scrollbar-thumb {
    background-color: rgb(190, 62, 80) !important;
    border-radius: 3px;
  }

  .el-container {
    overflow-x: hidden;
    overflow-y: hidden;
  }


  .el-header {
    position: fixed;
    justify-content: center;
    justify-items: center;
    justify-self: center;
    width: 100vw;
    z-index: 9999999;
    transform: translateY(100%);
    background-color: rgba(0, 0, 0, 0);
  }

  .el-main {
    top: 0;
  }

  .contain {
    display: inline;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
    background-color: rgba(0, 0, 0, 0);
    transform: translateY(100%);
    overflow-y: hidden;
  }

  .my-menu {
    width: auto;
    /*max-width: 35vw;*/
    /*min-width: 35vw;*/
    /*transform: translateY(1rem);*/
  }

  .head-top{
    position: fixed;
    /*display: flex;*/
    /*justify-content: left;*/
    /*justify-items: center;*/
    /*justify-self: center;*/
    top: 1rem;
    right: 1rem;
  }

  .head-bottom{
    display: flex;
    justify-content: left;
    justify-items: center;
    justify-self: center;
  }

  .logo {
    width: 20%;
    display: flex;
    justify-content: center;
    justify-items: center;
    border-radius: 1rem;
    min-width: 10rem;
    min-height: 2rem;
    padding-top: .5rem;
    padding-bottom: .5rem;
    background-color: rgb(190, 62, 80);
    z-index: 9999999;
  }

  .logo-font {
    font-size: 1.5rem;
    font-weight: bold;
    color: #fff;
  }

  .menu {
    display: flex;
    justify-content: left;
    justify-items: center;
    width: 40%;
    background-color: rgba(0, 0, 0, 0);
  }

  .menu-item {
    margin-right: 0.5rem;
  }

  .sound-switch {
    display: flex;
    justify-items: center;
    justify-content: center;
    width: 3rem;
    height: 3rem;
    border-radius: 1.5rem;
    background-color: #f0f0f0;
    cursor: pointer;

    min-width: 3rem;
    min-height: 3rem;
    max-width: 3rem;
    max-height: 3rem;

    transition: all 1s ease; /* 背景颜色的过渡效果 */
  }

  .sound-switch:hover {
    background-color: rgb(190, 62, 80);
    border: 0.1rem solid #fff;
  }

  .sound-switch:hover > .sound-open {

  }

  .sound-open {
    width: 1.5rem;
    height: 1.5rem;
    transform: translateY(0.75rem);
    transition: all 0.8s ease-in-out;
  }


  .go-more {
    display: flex;
    justify-content: center;
    justify-items: center;
    width: 8rem;
    height: 3rem;
    border-radius: 0 3rem 3rem 0;
    background-color: rgb(190, 62, 80);

    min-width: 8rem;
    min-height: 3rem;
    max-width: 8rem;
    max-height: 3rem;
    border: 0.1rem #fff solid;
  }

  .go-more > button {
    border: 0;
    outline: none;
    width: 100%;
    height: 100%;
    border-radius: 0 3rem 3rem 0;
    background-color: rgb(190, 62, 80);
  }

  .font-go-for-it {
    transition: all linear .3s;
  }

  .font-go-for-it:hover {
    font-size: 21px;
  }

  .go-more-button {
    transition: all 0.5s ease-in-out;
  }

  .go-more-button:hover {
    cursor: pointer;
    border: 0.1rem #fff solid;
  }


  .go-for-it {
    border: 0;
    outline: none;
    color: #fff;
    text-decoration: none;
    font-weight: bold;
  }

  .login {
    width: 3rem;
    height: 3rem;
    border-radius: 1.5rem;
    background-color: rgb(190, 62, 80);
    cursor: pointer;
    /*margin-right: 2rem;*/
  }

  .avatar-container {
    width: 100%;
    height: 100%;
  }

  .has-login {
    width: 3rem;
    height: 3rem;
    display: flex;
    justify-content: center;
    justify-items: center;
  }
  .wait-login {
    width: 3rem;
    height: 3rem;
    display: flex;
    justify-content: center;
    justify-items: center;
    color: #fff;
    transition: all 0.5s ease-in-out;
    cursor: pointer;
    text-align: center;
    border-radius: 1.5rem;
    background-color: #f0f0f0;
  }
  .wait-login> * {
    transform: translateY(calc(1.5rem - 50%));
  }

  .wait-login:hover {
    box-shadow: 0 0 0.2rem #fff;
  }

  .user-info {
    width: 1.5rem;
    height: 1.5rem;
    transform: translateY(50%);
  }

  .ul-panel {
    overflow-y: hidden;
    overflow-x: hidden;
  }

  .li-panel {
    list-style: none;
    width: 100vw;
    height: auto;
    overflow: hidden;
  }

  .top-li {
    height: 25vh;
    width: 100vw;
    background-color: rgb(190, 62, 80);
  }

  .video-li {
    width: 100%;
    padding: 2rem;
    background-color: rgba(190, 62, 80, 0);
  }

  .videoContainer {
    width: 100%;
    height: 100%;
  }

  .video-container {
    width: 48rem;
    height: auto;
    margin: 0;
    max-width: 80vw;
    transition: all 1s ease-out;
    aspect-ratio: 5 / 3;
    animation: initVideo 1.5s cubic-bezier(0.25, 0.46, 0.45, 0.94);

    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(0,0,0,0.6);
    position: relative;
    cursor: pointer;
  }



  @keyframes initVideo {
    0% {
      opacity: 0;
      width: 44rem;
    }
    70% {
      width: 48rem;
    }
    100% {
      opacity: 1;
      width: 48rem;
    }
  }

  .video-canvas {
    width: 100%;
    height: 100%;

    cursor: pointer;
  }



  .last-video{
    width: 5rem;
    height: 5rem;
    transition: all 0.3s ease;
  }
  .play-video {
    width: 5rem;
    height: 5rem;
    transition: all 0.3s ease;
    border-radius: 2.5rem;
  }
  .pause-video {
    width: 5rem;
    height: 5rem;
    transition: all 0.3s ease;
  }
  .next-video{
    width: 5rem;
    height: 5rem;
    transition: all 0.3s ease;
  }

  .show-menu {
    opacity: 1;
  }
  .un-show-menu {
    opacity: 0;
  }
  .video-play-icon{
    width: 60%;
    display: flex;
    justify-items: center;
    justify-content: center;
    position: absolute;
    cursor: pointer;
  }

  .video-play-icon>svg {
    margin: 1rem;
  }
  .video-play-icon>svg:hover {
    transform: scale(1.1);
  }

  .play-video:hover {

    box-shadow: 0 0 5px #BE3E50 ;
  }

  .videoContainer-li {
    width: 100vw;
    height: 100vh;
    top: 0;
    z-index: -100;

    background-color: #fff;
    position: fixed;
    text-align: center;
  }

  .videoContainer-li > canvas {
    width: 100%;
    height: 100%;
    /*background-color: rgba(190, 62, 80, 1);*/
  }

  .first-contais {
    display: flex;
    justify-content: center;
    justify-items: center;
    width: 100%;
  }

  .card-description {
    position: relative;
  }

  .card-text-font {
    font-size: 0.9vw;
    color: #5a5e66;
    margin-top: 0.1rem;
    margin-left: 0;
    text-align: center;
  }

  .card-title {
    display: flex;
    justify-content: center;
    margin-bottom: 2rem;
  }

  .card-title-font {
    align-items: baseline;
    font-size: 2.2vw;
    color: #000;
    height: 1rem;
  }

  .main-description {
    display: flex;
    justify-content: space-around;
    margin-top: 1rem;
    border-radius: 0.5rem;
  }

  .divider-span {
    margin-left: 0.2rem;
    margin-right: 0.2rem;
    color: rgb(190, 62, 80);
    animation: spin 2s linear infinite;
    background-color: rgba(0,0,0,0);
  }

  @keyframes spin {
    0% {
      transform: rotate(0deg); /* 初始角度 */
    }
    100% {
      transform: rotate(360deg); /* 旋转一整圈 */
    }
  }

  .text-description {
    display: unset;
    font-size: 1rem;
    font-family: "Courier New", Courier, monospace;
  }

  .text-description > * {
    margin-bottom: 0.5rem;
    text-align: center;
  }

  .title-description {
    font-family: "Raleway", sans-serif;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: unset;
    -ms-flex-pack: unset;
    justify-content: flex-start;
    justify-items: inherit;
    align-items: center;
    flex-direction: column;
    font-size: 2rem;
  }

  .a-text {
    border: 1px solid rgb(190, 62, 80);
    padding: 2px;
    border-radius: 5px;
    color: rgb(190, 62, 80);
    transition: all 0.5s ease;
  }

  .a-text:hover {
    /*box-shadow: 0 0 2px rgb(190, 62, 80);*/
    background-color: rgb(190, 62, 80);
    color: #fff;
    box-shadow: 2px 4px 5px rgb(190, 62, 80);
  }

  .other-li {
    width: 100vw;
    height: auto;
    background-color: rgb(190, 62, 80);
  }

  .support-title-con {
    padding-top: 2rem;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  .support-li{
    width: 100vw;
    height: auto;
    background-color: rgb(190, 62, 80);
  }
  .support-title {
    width: 35%;
    display: flex;
    justify-content: center;
    font-family: "Raleway", sans-serif;
    color: #fff;
    font-size: 2rem;
  }

  .el-col {
    display: flex;
    justify-content: space-evenly;
    justify-items: center;
  }

  .end-li {
    width: 100vw;
    height: 30vh;
    background-color: #191823;
  }

</style>
