<template>
    <div class="about-auth">
      <div class="about-auth-title-con" style="margin-bottom: 1rem">
        <div class="about-auth-title">
          <span class="unable-select-element">{{authInfo.title}}</span>
        </div>
      </div>
      <el-row :gutter="0" offset="0" justify="space-between">
        <el-col class="about-auth-item-col" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <el-image :src="authInfo.url" class="about-auth-img"/>
        </el-col>
        <el-col class="about-auth-item-col" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <div class="auth-info-list" v-for="i in authInfo.info" :key="i.key">
            <div><p class="title-text unable-select-element">{{i.title}}</p></div>
            <div><p class="label-text unable-select-element">{{i.label}}</p></div>
          </div>
        </el-col>
        <el-col class="about-auth-item-col"  :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
          <div class="auth-message">
            <p class="unable-select-element">{{authInfo.message}}</p>
          </div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "aboutAuth",
        props: {
          authInfo: {
            type: Object,
            required: true
          }
        },
        data() {
          return {

          }
        }
    }
</script>

<style scoped>
  .about-auth {
    width: 90vw;
    height: 50vh;
    background-color: rgb(190, 62, 80);
    background-color: #fff;
  }

  .about-auth-title {
    font-family: "Raleway", sans-serif;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: unset;
    -ms-flex-pack: unset;
    justify-content: flex-start;
    justify-items: inherit;
    flex-direction: column;
    padding-top: 2rem;
    padding-left: 5vw;
    font-size: 2rem;
  }

  .about-auth-title-con {

  }

  .about-auth-item-col {

  }

  .auth-info-list {
    display: flex;
    justify-items: center;
    justify-content: space-between;
    text-align: center;
    width: 25vw;
    max-width: 16rem;
    min-width: 8rem;
    height: auto;
  }

  .auth-info-list>div{
    width: 50%;
  }

  .title-text{
    font-family: "Courier New", Courier, monospace;
    color: #a7a5ad;
    text-align: right;
    margin-right: 0.5rem;
  }

  .label-text {
    font-family: "Courier New", Courier, monospace;
    text-align: left;
  }

  .about-auth-img {
    width: 8rem;
    max-height: 16rem;
    mim-width: 8rem;
    aspect-ratio: 1/1;
    margin-left: 5vw;
  }

  .auth-message {
    color: #a7a5ad;
    text-align: center;
    display: unset;
    font-size: 1rem;
    font-family: "Courier New", Courier, monospace;
  }

</style>
