<template>
  <div ref="vantaRef" class="input-container">
    <div class="form-con">
      <div class="info-form">
        <div>
          <div class="block">
            <my-input placeholder="描述一下你的想法, 30字以内喔"
                      :show-word-limit="true"
                      @enter-pressed="submit"
                      :value="inputValue"
                      :disabled="inLoading"/>
<!--            <el-input placeholder="描述一下你的想法, 30字以内喔"-->
<!--                      class="custom-input"-->
<!--                      maxlength="30"-->
<!--                      show-word-limit-->
<!--                      :disabled="inLoading"-->
<!--                      @keydown.enter="submit"-->
<!--                      v-model="inputValue">-->
<!--              <el-button slot="append" icon="el-icon-search" :disabled="inLoading" class="search" @click="submit" :loading="inLoading"/>-->
<!--            </el-input>-->
          </div>
        </div>
        <div>
          <el-progress :percentage="percentage"
                       v-if="startGenerator"
                       :status="percentageStatus"
                       class="wait"/>
          <el-progress :percentage="percentage1"
                       v-if="startGenerator"
                       :status="percentageStatus1"
                       class="wait"/>
          <div class="unable-select-element tip-msg" v-if="startGenerator">
            <span>{{message}}</span>
          </div>
        </div>
        <div>
          <el-image :src="src" class="img" v-if="imgCanBeShow && src">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
          </el-image>
        </div>
      </div>
    </div>
    <div class="el-login-footer unable-select-element">
      <span>Copyright © 服务器资源有限，生成过程可能要等待一定时间哦</span>
    </div>
  </div>
</template>
<script>
  import * as THREE from 'three'
  import RINGS from 'vanta/src/vanta.rings'
  import {generator, generatorInfo, generatorStepInfo, remainingUsageTimes} from '@/api/ai/img/generator';
  import MyInput from "../../../components/Kit/Default/MyInput";
  export default {
    name: 'SelfInput',
    components: {MyInput},
    data() {
      return {
        inputValue: '',
        startGenerator: false,
        imgCanBeShow: false,
        src: null,
        processId: '',
        inLoading: false,

        percentage: 50,
        percentageStatus: 'warn', //success, exception
        percentage1: 50,
        percentageStatus1: 'warn',

        vantaEffect: null,
        message: '',
        timer: null, // 用于存储定时器 ID,


        remainingUsage: {
          usage: 10,
          maxTimes: 10,
          message: ''
        }
      };
    },
    mounted() {
      document.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
          this.submit();
        }
      });

      this.vantaEffect = RINGS({
        el: this.$refs.vantaRef,
        THREE: THREE
      })
    },
    methods: {
      reset() {
        this.inLoading = false;
        this.percentage = 0;
        this.percentage1 = 0;
        this.percentageStatus = 'warn';
        this.percentageStatus1 = 'warn';
        this.startGenerator = false;
        this.imgCanBeShow = false;
      },
      submit(value) {
        this.inputValue = value;
        if ('' === this.inputValue) {
          this.$message.warning("描述一下你的idea, 30字以内喔");
          return ;
        }
        this.message = "初始化检查，核对您的配额情况...";
        remainingUsageTimes().then(r => {
          if (r.code === 200) {
            this.remainingUsage.usage = r.data.remainingUsageTimes;
            this.remainingUsage.maxTimes = r.data.maxTimes;
            this.remainingUsage.message = r.data.message;
          }

          if (this.remainingUsage.usage > 0) {
            this.message = "检查结果表示：您的配额情况正常, " + this.remainingUsage.message;
            this.doGenerator();
          } else {
            this.message = "检查结果表示：您的配额情况异常，" + this.remainingUsage.message;
            this.$message.warning(this.remainingUsage.message);
          }
        });
      },
      doGenerator() {
        this.inLoading = true;
        this.percentage = 0;
        this.percentage1 = 0;
        this.percentageStatus = 'warn';
        this.percentageStatus1 = 'warn';
        this.startGenerator = true;
        this.imgCanBeShow = false;
        this.message = "正在申请服务器资源...";
        generator({
          text: this.inputValue,
          width: 512,
          height: 512
        }).then(res => {
          if (res.code !== 200) {
            this.reset();
            return;
          }
          this.message = "服务器正在初始化，连接获取中...";
          this.processId = res.data.processId;
          this.startGenerator = true;
          this.timer = setInterval(() => {
            this.fetchData();
          }, 3000);
        }).catch(e => this.reset());
      },
      fetchData: function () {
        if (!this.processId) {
          return;
        }
        generatorStepInfo(this.processId).then(res => {
          if (200 !== res.code) {
            this.message = "获取实时执行进度失败，3秒后重试，请稍后...";
            return;
          }
          if (!res.data || res.data.lengths <= 0) {
            this.message = "远程正在服务器等待处理机接管，请稍后...";
            return;
          }
          let data = this.groupAndSort(res.data);

          let result = data['模型准备'];
          let topOne;
          if (result) {
            topOne = result[0];
            this.percentage = parseFloat(topOne.percentage.replace('%', ''));
            if (this.percentage >= 100) {
              this.percentageStatus = 'success';
            }
          }
          let result1 = data['深度推理'];
          if (result1) {
            topOne = result1[0];
            this.percentage = 100;
            this.percentageStatus = 'success';

            this.percentage1 = parseFloat(topOne.percentage.replace('%', ''));
            if (this.percentage1 >= 100) {
              this.percentageStatus1 = 'success';
            }
          }

          let message = '';
          if (topOne) {
            message = `正在执行[${topOne.title}]阶段, ${topOne.percentage}(${topOne.currentStep}/${topOne.totalSteps}), 预计剩余：${topOne.estimatedTime}，已执行：${topOne.elapsedTime}, ${this.formatTimestamp(topOne.time)}，${topOne.mark}`
          } else {
            message = "图像生成中，请稍后...";
          }

          let succeed = data['生成成功'];
          if (succeed) {
            let topOne = succeed[0];
            if (topOne) {
              this.message = "生成已完成，服务器资源已释放，正在拉取生成结果，请稍后";
              this.stopSearch();
              generatorInfo(this.processId).then(resData => {
                this.percentage = 100;
                this.percentage1 = 100;
                this.percentageStatus = 'success';
                this.percentageStatus1 = 'success';
                 if (200 !== resData.code) {
                   this.message = "正在拉取生成结果拉取失败：" + resData.message;
                   return;
                 }
                this.message = "图像获取成功";
                 this.src = process.env.VUE_APP_BASE_API +  resData.data.imgUrl;
                 this.imgCanBeShow = true;
                 this.inLoading = false;
              });
            }

            return;
          }

          let failed = data['生成失败'];
          if (failed) {
            let topOne = failed[0];
            if (this.percentage < 100) {
              this.percentageStatus = 'exception';
            }
            if (this.percentage1 < 100) {
              this.percentageStatus1 = 'exception';
            }
            this.message = "生成失败 " + topOne.mark;
            this.inLoading = false;
            this.stopSearch();
            return;
          }

          this.message = message;
        });
      },
      formatTimestamp(timestamp) {
        const date = new Date(timestamp);
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2); // 补0
        const day = ('0' + date.getDate()).slice(-2);           // 补0
        const hours = ('0' + date.getHours()).slice(-2);         // 补0
        const minutes = ('0' + date.getMinutes()).slice(-2);     // 补0
        const seconds = ('0' + date.getSeconds()).slice(-2);     // 补0
        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      },
      groupAndSort(data) {
        const groupedData = data.reduce((acc, item) => {
          if (!acc[item.title]) {
            acc[item.title] = [];
          }
          acc[item.title].push(item);
          return acc;
        }, {});

        for (let title in groupedData) {
          groupedData[title].sort((a, b) => b.time- a.time);
        }
        return groupedData;
      },
      stopSearch() {
        if (this.timer) {
          clearInterval(this.timer);
        }
      }
    },
    beforeDestroy() {
      if (this.vantaEffect) {
        this.vantaEffect.destroy()
      }
      // 组件销毁前，清除定时器
      if (this.timer) {
        clearInterval(this.timer);
      }
    }
  };
</script>

<style  rel="stylesheet/scss" lang="scss">

  .input-container {
    width: 100vw;
    height: 100vh;
    background-color: rgba(26,179,148,0);
    border-radius: 10px;
    opacity: 0.95;
  }

  @media (max-width: 2000px) {
    .form-con {
      width: 80vw; /* 显示器宽度较小时占80% */
    }
  }

  .form-con {
    position: absolute;
    opacity: 0.9;
    border-radius: 6px;
    background: rgba(0,0,0,0);
    width: 80vw;
    height: 85vh;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
  }
  .info-form {
    width: 100%;
    height: 100%;
    border-radius: 6px;
    background: rgba(255,255,255);
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



  .block {
    z-index: 99;
    width: 100%;
    background-color: rgba(0,0,0,0);
    margin-top: 20px;
  }

  .wait {
    margin-top: 20px;
    width: 100%;
    z-index: 99;
    background-color: rgba(0,0,0,0);
  }

  .search {
  }

  .tip-msg {
    background-color: rgba(0,0,0,0);
    z-index: 99;
    width: 100%;
    margin-top: 10px;
    font-size: 8px;
    color: gray;
  }


  .el-input {
    border: 0;
    border-radius: 10px;
    width: 100%;
    transition: top 1s ease-in-out 0s;
  }

  .el-input:focus {
    outline: none;
    box-shadow: 0 0 5px #1ab394, 0 0 10px #1ab394;
  }

  .custom-input-start {
    top: 20%;
  }

  .submit-button {
    padding: 10px 15px;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer; /* 鼠标悬停时显示手型 */
  }

  .img {
    margin-top: 5px;
    max-width: 384px;
    max-height: 384px;
    /*object-fit: cover;*/
  }

  .center-form-item {
    display: grid;
    place-items: center;
    height: 100%;
    text-align: center;
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
