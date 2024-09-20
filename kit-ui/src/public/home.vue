<template>
  <el-container class="el-container">
    <el-header class="el-header">
      <Head background-color="#545c64" active-text-color="#ffd04b" text-color="#fff"/>
    </el-header>
    <el-main class="el-main">
      <ul class="infinite-list" style="overflow:auto">
        <li class="infinite-list-item top-li" ref="vantaRef">
          <div class="panel unable-select-element">
            <h1 class="title title-h">You have arrived at a very interesting place</h1>
            <p class="title title-p">This is an interesting beginning now. We will continue to provide and create many creative ideas. At the same time, we welcome you to our <font style="font-weight: bold">Github</font> repository to provide your creative points, which can be executable code, an issue, or your requests. Our team will provide you with the most suitable response at the appropriate time.  We welcome great tasks to join us and submit bugs together</p>
            <a class="title title-a" ref="https://github.com/11000100111010101100111/SmallQuestionNotes">
              <p class="title title-a">Just wanted to see the BUG you wrote</p>
            </a>
<!--            <p class="title title-video">Let's first enjoy a short video</p>-->
            <div class="video-div">
              <my-video class="my-video" :video-src="videoSrc"/>
            </div>
          </div>
        </li>
        <li class="infinite-list-item">
          <div class="panel-2th">
              <h1 class="tip-font" style="font-weight: bolder">下面没有内容了，马上安排</h1>
          </div>
        </li>
        <li class="infinite-list-item-button">
         <div class="infinite-list-item-button-context">
           <ul class="button-logos">
             <li class="button-logo-item">
               <svg class="icon" aria-hidden="true">
                 <use xlink:href="#icon-github-fill"></use>
               </svg>
             </li>
             <ul class="button-logo-item"></ul>
             <ul class="button-logo-item"></ul>
           </ul>
           <h1 class="tip-font" style="font-weight: bolder">这里也一样</h1>
         </div>
        </li>
      </ul>
      <Top/>
    </el-main>
  </el-container>

</template>

<script>
  import * as THREE from 'three'
  import VANTA from 'vanta/src/vanta.halo'
  import BaiDuMap from '../views/wather/BaiDuMap.vue';
  import Top from './Top.vue';
  import Head from './common/Head';
  import MyVideo from "../views/tool/kit/video/MyVideo";

  export default {
    name: "Home",
    components: {
      MyVideo,
      BaiDuMap, Top, Head
    },
    data(){
      return {
        videoSrc: "/video/fa7656626c94bea3af3ec0d90ff7b47f.mp4",
        videoSources: [
          {
            src: '/video/fa7656626c94bea3af3ec0d90ff7b47f.mp4',
            type: 'video/mp4'
          }
        ],
        videoOptions: {
          autoplay: true,
          controls: true,
          poster: '/icon/weather/weather_Cloudy.png'
        },

        playerPlaying: false,
        plyrOptions: {
          autoplay: false,
          controls: ['play', 'progress', 'volume', 'fullscreen'],
          muted: false
        },

        vantaEffect: null,
      }
    },
    mounted() {
      // this.vantaEffect = VANTA({
      //   el: this.$refs.vantaRef,
      //   THREE: THREE,
      //   mouseControls: true,
      //   touchControls: true,
      //   gyroControls: false,
      //   minHeight: 200.00,
      //   minWidth: 200.00,
      //   amplitudeFactor: 0.50,
      //   size: 1.50
      // })
    },
    methods: {
    },
    beforeDestroy() {
      if (this.vantaEffect) {
        this.vantaEffect.destroy()
      }
    }
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
    z-index: 1000;
    background-color: rgba(0, 0, 0, 0.0);
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.0);
  }

  .el-main {
    top: 0;
  }

  .infinite-list-item {
    top: 0;
    left: 0;
    width: 100vw; /* 宽度占满父容器 */
    height: 100vh; /* 高度为可视区高度减去 header 的高度 */
    list-style: none;
  }

  .infinite-list-item-button{
    top: 0;
    left: 0;
    width: 100vw;
    height: 30vh;
    list-style: none;
  }

  .top-li {
    width: 100vw;
    height: 100vh;
    /*background-color: rgb(238 79 101);*/
    background-color: #fff;
    background-image: linear-gradient(
       125deg, #2c3e50, #0aae9c, #2663b9, #e70f4b, #8e44ad
    );
    background-size: 400%;
    animation: runningBackground 20s infinite;
  }

  @keyframes runningBackground {
    0%{
      background-position: 0 50%;
    }
    50%{
      background-position: 100% 50%;
    }
    100%{
      background-position: 0 50%;
    }
  }

  .panel {
    width: 80%;
    height: 80%;
    transform: translateY(15%) translateX(12.5%);
    background-color: rgba(255,255,255,0);
  }
  .title {
    width: 100%;
    color: #fff;
    text-align: center;
    margin-bottom: 20px;
    -webkit-font-smoothing: antialiased;
    font-family: Inter,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica Neue,Noto Sans,ui-sans-serif,sans-serif,Apple Color Emoji,Segoe UI Emoji,Segoe UI Symbol,Noto Color Emoji,Arial;
  }

  .title-h {
    font-weight: bolder;
    font-size: 36px;
  }

  .title-p {

  }

  .title-video {
    margin-bottom: 5px;
  }

  .title-a {
    color: rgb(238 79 101);
    font-weight: bold;
  }

  .video-div {
    position: absolute;
    width: 80%;
    height: 60%;
    background-color: rgba(22, 198, 161, 0);
    margin-left: 10%;
    /*padding-bottom: 1%;*/
    bottom: 0.5%;
  }

  .my-video {
    position: relative;
    width: 100%;
    height: 100%;
    background-color: rgba(22, 198, 161, 0);
  }

  .panel-2th {
    width: 100%;
    height: 100%;
    background-color: #fff;
    display: flex;
    justify-content: center;
    justify-items: center;
  }

  .tip-font {
    width: 100%;
    text-align: center;
    /*transform: translateY(50%);*/
  }

  .infinite-list-item-button{
    background-color: #b4b4b4;
  }


  .button-logos{
    display: flex;
    justify-content: left;
    list-style: none;
    transform: translateY(-50%);
  }

  .button-logo-item {
    text-align: center;
    width: 40px;
    height: 40px;
    border-radius: 20px;
    background-color: #b4b4b4;
    margin: 5px;
  }
  .infinite-list-item-button-context {
    width: 100%;
    height: 100%;
  }
</style>
