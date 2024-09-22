<template>
  <div class="input-container">
    <div class="form-con">
      <div class="info-form">
        <div>
          <div class="process-div" v-for="pro in detail.allProcess"
                :key="pro.key">
            <span v-if="startGenerator" class="unable-select-element">{{pro.name}}:</span>
            <el-progress :percentage="pro.percentage"
                         v-if="startGenerator"
                         :status="pro.status"
                         class="wait"/>
          </div>
        </div>
        <div class="img-div">
          <el-image :src="src" class="img" v-if="imgCanBeShow && src">
            <div slot="placeholder" class="image-slot">
              加载中<span class="dot">...</span>
            </div>
            <svg slot="error" t="1726858143497" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="12397" width="256" height="256"><path d="M532.48 529.105455H349.905455L523.636364 128H93.905455A94.021818 94.021818 0 0 0 0 221.905455V768a94.021818 94.021818 0 0 0 93.905455 93.905455h361.30909z" fill="#F2F6FF" p-id="12398"></path><path d="M930.094545 162.094545h-379.345454l-55.854546 290.21091 189.207273-0.698182L480.349091 896h449.745454A94.021818 94.021818 0 0 0 1024 802.094545V256a94.021818 94.021818 0 0 0-93.905455-93.905455z" fill="#F2F6FF" p-id="12399"></path><path d="M930.094545 162.094545h-379.345454l-9.890909 51.2h389.236363A42.705455 42.705455 0 0 1 972.8 256v235.985455c-61.090909-96-176.058182-102.4-256-82.385455a376.436364 376.436364 0 0 0-101.701818 42.24h69.003636L480.349091 896h449.745454A94.021818 94.021818 0 0 0 1024 802.094545V256a94.021818 94.021818 0 0 0-93.905455-93.905455z" fill="#B1BECE" p-id="12400"></path><path d="M532.48 529.105455H489.890909c-11.636364 14.894545-22.458182 29.556364-31.534545 43.403636a291.956364 291.956364 0 0 0-58.181819-43.403636h-50.26909l8.378181-19.316364a230.981818 230.981818 0 0 0-93.789091-14.894546c-134.050909 9.658182-192.349091 102.981818-213.294545 139.636364V221.905455a42.705455 42.705455 0 0 1 42.705455-42.705455h407.272727L523.636364 128H93.905455A94.021818 94.021818 0 0 0 0 221.905455V768a94.021818 94.021818 0 0 0 93.905455 93.905455h361.30909z" fill="#B1BECE" p-id="12401"></path><path d="M298.705455 324.305455m-76.8 0a76.8 76.8 0 1 0 153.6 0 76.8 76.8 0 1 0-153.6 0Z" fill="#B1BECE" p-id="12402"></path><path d="M193.745455 428.218182v31.069091h38.4v30.487272c0 7.796364 0 16.058182-0.581819 24.785455s0 17.105455-0.581818 24.669091 0 14.196364 0 19.665454v9.89091a26.414545 26.414545 0 0 1-3.723636 15.476363 20.712727 20.712727 0 0 1-14.778182 6.167273l-15.010909 1.396364-4.654546-14.661819 16.407273-1.512727a7.447273 7.447273 0 0 0 5.934546-3.025454 16.756364 16.756364 0 0 0 1.629091-8.145455v-9.076364-16.64-20.247272-20.247273-16.407273-8.727273h-23.97091c-0.698182 16.058182-1.629091 29.905455-3.141818 41.658182a195.723636 195.723636 0 0 1-6.050909 31.418182 131.84 131.84 0 0 1-9.774545 25.483636 200.610909 200.610909 0 0 1-13.847273 23.272728l-12.8-6.865455a158.254545 158.254545 0 0 0 13.381818-21.410909 129.396364 129.396364 0 0 0 9.425455-23.272727 195.374545 195.374545 0 0 0 5.934545-29.789091c1.396364-11.636364 2.443636-24.32 3.025455-39.563636v-0.93091h-22.341818v-13.847272h23.272727v-9.309091-9.192727V427.52z m113.687272 21.061818v143.476364h-13.963636v-15.36h-32.930909v15.36h-13.730909V449.28z m-13.963636 114.501818v-100.770909h-32.930909v100.770909zM429.381818 566.225455l-12.8 1.396363-17.105454 1.512727v25.949091h-13.381819V570.181818l-14.42909 1.047273-13.498182 1.047273-11.636364 0.814545h-9.774545l-1.396364-11.636364h10.24l12.567273-0.698181 13.963636-0.930909 13.963636-1.28v-13.73091h-34.90909v-11.636363c4.538182-6.865455 8.494545-13.614545 11.636363-20.247273h-19.781818v-11.636364h26.298182c1.28-2.443636 2.443636-4.887273 3.490909-6.981818s1.978182-4.305455 3.025454-6.4l12.8 3.141818c-0.814545 1.861818-1.745455 3.607273-2.56 5.236364s-1.512727 3.374545-2.327272 5.003636h46.545454v11.636364h-52.48l-5.236363 9.658182c-1.861818 3.258182-3.84 6.632727-6.050909 10.24h19.54909v-13.381818h13.381819v13.032727h28.858181v11.636364h-28.858181v13.265454l16.989091-1.512727 12.8-1.396364z m21.643637-80.64a382.603636 382.603636 0 0 0 7.214545 50.967272 149.294545 149.294545 0 0 0 11.636364-20.130909 217.483636 217.483636 0 0 0 9.541818-23.272727l11.636363 5.003636a267.636364 267.636364 0 0 1-13.265454 29.789091 167.098182 167.098182 0 0 1-15.825455 24.901818c1.978182 6.050909 3.956364 11.636364 5.818182 15.36a38.167273 38.167273 0 0 0 4.887273 8.494546 6.283636 6.283636 0 0 0 4.770909 2.443636c1.745455 0 3.025455-1.163636 3.956364-3.490909a99.374545 99.374545 0 0 0 2.792727-11.636364c0.581818-3.025455 0.930909-5.469091 1.163636-7.563636s0-4.538182 0.698182-7.563636l11.636364 5.469091V558.545455a29.556364 29.556364 0 0 1 0 3.374545c0 1.163636 0 2.443636-0.698182 3.84s-0.581818 3.374545-1.047273 5.701818a85.294545 85.294545 0 0 1-3.258182 11.636364 23.272727 23.272727 0 0 1-4.072727 7.098182 11.636364 11.636364 0 0 1-5.003636 3.607272 16.756364 16.756364 0 0 1-5.934546 1.047273 17.454545 17.454545 0 0 1-11.636363-4.072727 43.869091 43.869091 0 0 1-9.774546-14.429091c-1.396364-2.909091-2.792727-6.632727-4.421818-10.938182a267.962182 267.962182 0 0 1-27.578182 26.065455l-9.658182-10.821819a218.181818 218.181818 0 0 0 17.803637-14.778181 200.145455 200.145455 0 0 0 14.778182-15.127273 264.378182 264.378182 0 0 1-6.516364-29.323636c-1.861818-10.938182-3.258182-23.272727-4.305455-35.49091h-97.629091v-11.636363h43.403637v-17.803637h-33.861818v-11.636363h33.861818v-17.92l13.265454 0.581818v17.687273h32.581819v11.636363H395.636364v16.989091h40.727272v-4.770909-4.887273l-1.28-38.167272h13.381819l1.745454 40.261818v7.098182h52.48v11.636363z m31.883636-17.92c-2.210909-2.676364-4.421818-5.469091-6.749091-8.145455a91.810909 91.810909 0 0 0-7.214545-7.68c-2.327273-2.327273-4.654545-4.421818-6.981819-6.4s-4.072727-3.490909-5.701818-4.770909l8.378182-8.378182A121.6 121.6 0 0 1 477.090909 442.181818a135.214545 135.214545 0 0 1 14.196364 15.010909zM616.727273 522.24a89.018182 89.018182 0 0 0 10.24 19.2 79.476364 79.476364 0 0 0 14.778182 15.941818 122.414545 122.414545 0 0 0 20.247272 13.498182 221.090909 221.090909 0 0 0 26.530909 11.636364l-8.727272 13.381818a174.545455 174.545455 0 0 1-44.567273-24.901818 95.650909 95.650909 0 0 1-27.229091-34.094546A106.938182 106.938182 0 0 1 581.818182 568.436364a188.392727 188.392727 0 0 1-46.545455 28.043636l-9.076363-12.567273a169.890909 169.890909 0 0 0 48.989091-28.16 72.843636 72.843636 0 0 0 23.272727-33.512727h-69.585455v-13.963636h70.865455v-1.163637-11.636363-21.061819h-39.447273a203.054545 203.054545 0 0 1-14.312727 27.578182l-12.334546-6.749091a133.352727 133.352727 0 0 0 8.145455-13.963636c2.56-5.12 4.887273-10.24 7.214545-15.476364s4.189091-10.589091 6.050909-15.825454 3.374545-10.472727 4.654546-15.592727l13.614545 3.141818c-2.210909 7.796364-4.770909 15.476364-7.563636 23.272727h34.909091v-32.232727l14.894545 0.581818v31.301818H674.909091v13.730909h-59.461818v34.094546h67.723636v13.963636zM755.432727 521.076364a116.363636 116.363636 0 0 1-1.745454 21.294545 59.810909 59.810909 0 0 1-6.516364 17.803636 81.454545 81.454545 0 0 1-12.916364 16.756364 194.443636 194.443636 0 0 1-20.712727 18.152727l-9.658182-9.541818a166.632727 166.632727 0 0 0 20.596364-16.523636 67.84 67.84 0 0 0 11.636364-15.243637 48.756364 48.756364 0 0 0 5.352727-15.243636 95.650909 95.650909 0 0 0 1.28-16.872727v-56.901818h12.334545zM835.141818 546.909091a128 128 0 0 0 17.687273 20.014545 205.498182 205.498182 0 0 0 23.970909 19.083637l-8.96 10.821818a247.156364 247.156364 0 0 1-23.272727-19.432727 155.810909 155.810909 0 0 1-17.454546-18.850909 152.32 152.32 0 0 1-19.549091 20.363636 266.589091 266.589091 0 0 1-25.832727 19.781818l-9.309091-11.636364a221.090909 221.090909 0 0 0 27.578182-19.781818A123.345455 123.345455 0 0 0 819.432727 546.909091a127.185455 127.185455 0 0 1-11.636363-25.250909 181.061818 181.061818 0 0 1-7.098182-28.16 182.923636 182.923636 0 0 1-11.636364 17.338182L779.636364 500.363636v44.8h-13.032728V453.818182h-34.909091v91.927273H719.127273v-104.727273H779.636364v56.901818a106.472727 106.472727 0 0 0 10.938181-16.174545 168.96 168.96 0 0 0 8.61091-17.92c2.443636-6.050909 4.538182-11.636364 6.4-18.152728s3.374545-11.636364 4.770909-17.570909l14.08 1.163637c-1.28 4.770909-2.676364 9.541818-3.956364 14.312727s-2.792727 9.425455-4.421818 13.963636h58.181818v12.8h-15.709091a246.225455 246.225455 0 0 1-8.261818 42.356364 139.636364 139.636364 0 0 1-15.127273 34.210909z m-62.254545 37.352727a77.381818 77.381818 0 0 0-4.887273-7.098182c-1.629091-2.094545-3.374545-4.072727-5.12-6.050909l-5.585455-5.469091-5.934545-5.585454 7.447273-9.658182c4.072727 3.607273 8.029091 7.330909 11.636363 11.636364s7.68 8.029091 11.636364 12.567272z m36.887272-110.196363a206.08 206.08 0 0 0 6.283637 32.465454 137.192727 137.192727 0 0 0 11.636363 28.043636 115.2 115.2 0 0 0 11.636364-29.090909 288.465455 288.465455 0 0 0 5.818182-34.909091H811.054545v1.745455z" fill="#1F3041" opacity=".7" p-id="12403"></path></svg>
          </el-image>
        </div>
        <div class="unable-select-element tip-msg" v-if="startGenerator">
          <span>{{message}}</span>
        </div>
      </div>
    </div>
    <div class="el-login-footer unable-select-element">
      <span>Copyright © 服务器资源有限，生成过程可能要等待一定时间哦</span>
    </div>
  </div>
</template>

<script>
    import MyInput from "../../../components/Kit/Default/MyInput";
    import {generator, generatorInfo, generatorStepInfo, remainingUsageTimes} from '@/api/ai/img/generator';

    export default {
        name: "DetailImg",
      components: {MyInput},
      props: {
        processIdStr: {
          type: String,
          default: ''
        },
        searchValue: {
          type: String,
          default: ''
        }
      },
      data() {
        return {
          inputValue: 'XXX',
          startGenerator: false,
          imgCanBeShow: false,
          src: null,//'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',

          message: '',
          timer: null, // 用于存储定时器 ID,


          remainingUsage: {
            usage: 10,
            maxTimes: 10,
            message: ''
          },


          detail: {
            allProcess: [
              {
                name: '加载组件',
                key:'LoadKey',
                percentage: 0,
                status: "warning"
              },{
                name: '深度推演',
                key: 'depthLoad',
                percentage: 0,
                status: "warning"
              }
            ]
          },

          onlyShow: false,
          showText: ''
        };
      },
      methods: {
        onlyShowInfo(pid, text){
          this.onlyShow = true;
          this.showText = text;
          this.showInfo(pid);
        },
        notOnlyShow(value) {
          this.onlyShow = false;
          this.showText = '';
          this.submit(value);
        },
        initAllProcess() {
          let processInfo = [];
          let loadKey = {
            name: '模型准备',
            key:'LoadKey',
            percentage: 0,
            status: "warning"
          };
          let depthLoad = {
            name: '深度推演',
            key: 'depthLoad',
            percentage: 0,
            status: "warning"
          };
          processInfo.push(loadKey);
          processInfo.push(depthLoad);
          this.detail.allProcess = processInfo;
        },
        reset() {
          this.initAllProcess();
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
          this.initAllProcess();
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
            this.processIdStr = res.data.processId;
            this.startGenerator = true;
            this.timer = setInterval(() => {
              this.fetchData();
            }, 3000);
          }).catch(e => this.reset());
        },
        showInfo(processId) {
          this.initAllProcess();
          this.imgCanBeShow = false;
          this.message = "正在来连接请稍后...";
          this.processIdStr = processId;
          this.startGenerator = true;
          this.timer = setInterval(() => {
            this.fetchData();
          }, 3000);
        },
        fetchData: function () {
          if (!this.processIdStr) {
            return;
          }
          generatorStepInfo(this.processIdStr).then(res => {
            if (200 !== res.code) {
              this.message = "获取实时执行进度失败，3秒后重试，请稍后...";
              return;
            }
            if (!res.data || res.data.lengths <= 0) {
              this.message = "远程正在服务器等待处理机接管，请稍后...";
              return;
            }
            let data = this.groupAndSort(res.data);

            let processInfo = [];
            let loadKey = {
              name: '模型准备',
              key:'LoadKey',
              percentage: 0,
              status: ""
            };
            let depthLoad = {
              name: '深度推演',
              key: 'depthLoad',
              percentage: 0,
              status: ""
            };
            processInfo.push(loadKey);
            processInfo.push(depthLoad);
            this.detail.allProcess = processInfo;

            let result = data['模型准备'];
            let topOne;
            if (result) {
              topOne = result[0];
              let percentage = parseFloat(topOne.percentage.replace('%', ''));
              if (percentage >= 100) {
                loadKey.status = 'success';
              }
              loadKey.percentage = percentage;
            }


            let result1 = data['深度推理'];
            if (result1) {
              topOne = result1[0];
              loadKey.percentage = 100;
              loadKey.status = 'success';

              let percentage = parseFloat(topOne.percentage.replace('%', ''));
              if (percentage >= 100) {
                depthLoad.status = 'success';
              }
              depthLoad.percentage = percentage;
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
                generatorInfo(this.processIdStr).then(resData => {
                  let loadKey = this.detail.allProcess[0];
                  let depthLoad = this.detail.allProcess[1];
                  loadKey.percentage = 100;
                  depthLoad.percentage = 100;
                  loadKey.status = 'success';
                  loadKey.status = 'success';
                  if (200 !== resData.code) {
                    this.message = "正在拉取生成结果拉取失败：" + resData.message;
                    return;
                  }
                  this.message = "图像生成成功";
                  this.src = process.env.VUE_APP_BASE_API +  resData.data.imgUrl;
                  this.imgCanBeShow = true;
                });
              }
              return;
            }

            let failed = data['生成失败'];
            if (failed) {
              let topOne = failed[0];
              if (loadKey.percentage < 100) {
                loadKey.status = 'exception';
              }
              if (depthLoad.percentage < 100) {
                depthLoad.status = 'exception';
              }
              this.message = "生成失败 " + topOne.mark;
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
        // 组件销毁前，清除定时器
        if (this.timer) {
          clearInterval(this.timer);
        }
      }
    }
</script>

<style  rel="stylesheet/scss" lang="scss">
  * {
    margin: 0;
    padding: 0;
  }
  .input-container {
    width: 100%;
    background-color: rgba(26,179,148, 1);
  }

  @media (max-width: 2000px) {
    .form-con {
      width: 80%;
    }
  }

  .form-con {
    background: rgba(0,0,0,0);
    width: 100%;
    top: 50%;
    left: 50%;
  }
  .info-form {
    width: 100%;
    height: 100%;
    background: rgba(255,255,255);

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



  .process-div {
    margin-top: 10px;
    display: flex;
    justify-content: left;
    justify-items: center;
  }
  .wait {
    margin-left: 5px;
    width: 80%;
    z-index: 99;
    background-color: rgba(0,0,0,0);
  }

  .img-div {
    display: flex;
    justify-content: center;
  }
</style>
