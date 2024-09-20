<template>
  <video
    ref="videoPlayer"
    id="my-video"
    class="video-js vjs-default-skin"
    controls
    preload="auto"
    autoplay
    data-setup="{}"
  >
    <source :src="videoSrc" type="video/mp4" />
    <source :src="videoSrc" type="video/mp4" />
    <p class="vjs-no-js">
      To view this video please enable JavaScript, and consider upgrading to a
      web browser that
      <a href="https://videojs.com/html5-video-support/" target="_blank"
      >supports HTML5 video</a
      >.
    </p>
  </video>
</template>

<script>
  import videojs from "video.js";
  import "video.js/dist/video-js.css";
    export default {
        name: "MyVideo",
        props:{
          videoSrc: {
            type: String,
            default: '/viedo/35a012b41d93ec9de8058bf47c028851.mp4'
          }
        },
        data() {
          return {
            muted: true
          }
        },
        mounted() {
          this.player = videojs(this.$refs.videoPlayer, {
            autoplay: true,
            controls: true,
            preload: "auto",
            muted: true,
            loop: true
          }, function onPlayerReady() {
            console.log("Player is ready!");
          });
        },
      beforeDestroy() {
        if (this.player) {
          this.player.dispose();
        }
      },
        methods: {},
    }
</script>

<style scoped>
  .video-container,#my-video {
    width: 100%;
    height: 100%;
    max-width: 100vw;
    max-height: 100vh;
    margin: 0;
    object-fit: contain;
  }

  .video-js .vjs-control-bar {
    background-color: rgba(19, 255, 171, 0.6); /* 改变控制栏颜色 */
  }

  .video-js .vjs-play-control {
    color: #1dffb4; /* 改变播放按钮颜色 */
  }
</style>


