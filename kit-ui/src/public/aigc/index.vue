<template>
  <div>
    <ul class="el-container"><!--
 --><li class="el-header flex-top" ref="topLi">
      <ul class="ul-panel ul-panel-head">
        <li class="sub-li sub-li-head left-li-head">

        </li>
        <li class="sub-li sub-li-head mid-li-head">

        </li>
        <li class="sub-li sub-li-head right-li-head">
          <div class="blog-search">
            <my-input @update-value="inSearch"
                      placeholder="描述一下你的想法, 30字以内喔，按下回车键开始生成图片......"
                      @enter-pressed="enter"
                      :disabled="searchDisabled"
                      :value="searchValue"/>
          </div>
        </li>
      </ul>
    </li><!--
 --><li class="el-main">
      <div :class="[processInfo.length > 0 ? 'card' : 'card-empty']">
        <el-empty description="啥也没有" v-if="processInfo.length <= 0" ></el-empty>
        <ul class="img-main" v-if="processInfo.length > 0">
          <li class="img-item" v-for="item in processInfo" v-bind="item" ref="imgSimpleInfo" :key="item.processId">
            <el-tooltip class="item" effect="light" content="点击查看详情">
              <card class="card-item"
                    @remove-img="removeProcess"
                    @show-info="showInfo"
                    :processInfo="item"
                    @click="showInfo"/>
            </el-tooltip>
          </li>
        </ul>
      </div>
      <div class="page" v-if="processInfo.length > 0">
        <el-pagination
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          layout="prev, pager, next"
          :total="total">
        </el-pagination>
      </div>
    </li><!--
 --></ul>
    <el-dialog
      :destroy-on-close="true"
      :title="focusText"
      :visible.sync="centerDialogVisible"
      width="50%"
      @open="handleDialogOpen"
      @close="handleDialogClose"
      center>
      <detail-img ref="dialogContent"
                  :key="dialogKey"
                  :search-value="searchValue"
                  :process-id-str="focusProcessId"/>
    </el-dialog>
  </div>
</template>

<script>
    import {list, deleteOne} from '@/api/ai/img/generator';
    import MyInput from "../../components/Kit/Default/MyInput";
    import Card from "./img/card";
    import DetailImg from "./img/DetailImg";
    export default {
      name: "index",
      components:{
        DetailImg,
        Card,
        MyInput
      },
      data() {
        return {
          emptyImg: process.env.VUE_APP_BASE_API + '/assets/images/empty.png',
          dialogKey:0,
          centerDialogVisible: false,
          onlyShow: false,

          flexTop: false,

          searchValue: '',
          searchDisabled: false,

          focusProcessId: null,
          focusText: '',

          queryParams: {
            pageNum: 1,
            pageSize: 10
          },
          total: 0,
          processInfo: [{
              processId: "1",
              src: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
              description:"小鸟站在树枝上看着远方,我正在思恋他乡",
              status: 'OK', //已完成, 等待处理, 处理中，失败
              message: '[XXX]正在处理中',
              process: 80,
              startTime: '2024-09-20 19:5400.032', //处理时间
              class: 'Height', //图像级别: Upper(50, ...), Height[40, 50)，Middle[30, 40), Low[20, 30), Lower(0, 20)
            },{
            processId: "2",
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            description:"小鸟站在树枝上看着远方，她在眺望故乡",
            status: 'OK', //已完成, 等待处理, 处理中，失败
            message: '[XXX]正在处理中',
            process: 80,
            startTime: '2024-09-20 19:5400.032', //处理时间
            class: 'Height', //图像级别: Upper(50, ...), Height[40, 50)，Middle[30, 40), Low[20, 30), Lower(0, 20)
          },{
            processId: "3",
            src: 'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg',
            description:"白毛猴子一边在水池里洗澡另一边用手趴在石头上",
            status: 'OK', //已完成, 等待处理, 处理中，失败
            message: '[XXX]正在处理中',
            process: 80,
            startTime: '2024-09-20 19:5400.032', //处理时间
            class: 'Height', //图像级别: Upper(50, ...), Height[40, 50)，Middle[30, 40), Low[20, 30), Lower(0, 20)
          },{
            processId: "4",
            src: 'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
            description:"小鸟站在树枝上看着远方小鸟站在树枝上看着远方,我正在思恋他乡小鸟站在树枝上看着远方,我正在思恋他乡小鸟站在树枝上看着远方,我正在思恋他乡小鸟站在树枝上看着远方,我正在思恋他乡",
            status: 'OK', //已完成, 等待处理, 处理中，失败
            message: '[XXX]正在处理中',
            process: 80,
            startTime: '2024-09-20 19:5400.032', //处理时间
            class: 'Height', //图像级别: Upper(50, ...), Height[40, 50)，Middle[30, 40), Low[20, 30), Lower(0, 20)
          }
          ]
        }
      },
      methods: {
        searchOnce() {
          list(this.queryParams).then(res => {
            if (res.code !== 200) {
              return;
            }
            this.processInfo = [];
            this.total = res.total;
            res.rows.forEach(data => {
              this.processInfo.push({
                processId: data.processId,
                src: process.env.VUE_APP_BASE_API + data.imgUrl,
                description: data.text,
                status: data.result,//'OK', //已完成, 等待处理, 处理中，失败
                message: data.resultMessage ,//'[XXX]正在处理中',
                process: 0,
                startTime: data.createTime,//'2024-09-20 19:5400.032', //处理时间
                class: 'Height', //图像级别: Upper(50, ...), Height[40, 50)，Middle[30, 40), Low[20, 30), Lower(0, 20)
              });
            });
          })
        },
        observeTopChange() {
          const targetNode = this.$refs.topLi;
          const observer = new MutationObserver(() => {
            const computedStyle = window.getComputedStyle(targetNode);
            this.flexTop = computedStyle.top;
          });

          observer.observe(targetNode, { attributes: true, attributeFilter: ['style'] });
        },
        inSearch(value) {
          this.searchValue = value;
        },
        enter(value) {
          if ('' === this.searchValue) {
            this.$message.warning("描述一下你的idea, 30字以内喔");
            return ;
          }
          this.onlyShow = false;
          this.searchValue = value;
          this.centerDialogVisible = true;
        },
        removeProcess(processId) {
          this.$confirm('你是不是也想删了这个记录？就像当初你无情删除前女友（前男友）一样再也加不回来了, 确定要删除吗?', '来自长者的慈爱关怀', {
            confirmButtonText: '没事啦，直接删吧',
            cancelButtonText: '有点害怕，不想删了',
            type: 'warning'
          }).then(() => {
            deleteOne(processId).then(res => {
              if (res.code === 200) {
                const index = this.processInfo.findIndex(item => item.processId === processId);
                if (index !== -1) {
                  this.processInfo.splice(index, 1); // 移除数组中的元素
                }
                this.$message({
                  type: 'success',
                  message: '已经删除了~'
                });
                this.searchOnce();
              } else {
                this.$message.warning( '好像删除失败了~');
              }
            }).catch(e => {
              this.$message.warning( '好像删除失败了~');
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '我知道你的故事远远不止这一点'
            });
          });
        },
        handleDialogOpen() {
          this.dialogKey++;
          this.$nextTick(() => {
            if (this.onlyShow) {
              this.$refs.dialogContent.onlyShowInfo(this.focusProcessId, this.focusText);
            } else {
              this.focusText = this.searchValue;
              this.$refs.dialogContent.notOnlyShow(this.searchValue);
            }
          });
        },
        handleDialogClose() {
          if (!this.onlyShow) {
            this.searchOnce();
          }
        },
        showInfo(processId, text) {
          this.onlyShow = true;
          this.focusProcessId = processId;
          this.focusText = text;
          this.centerDialogVisible = true;
        }
      },
      mounted() {
        this.observeTopChange();
        this.searchOnce();
      },
      beforeDestroy() {

      },
      beforeUnmount() {
        this.editor.destroy()
      },
    }
</script>

<style scoped>
  body {
    overflow: hidden;
  }
  .sub-li {
    list-style: none;
  }

  .ul-panel-head {
    width: 100%;
    height: 100%;
  }
  .sub-li-head {
    width: 100%;
  }

  .left-li-head {
    height: 20%;
  }
  .mid-li-head {
    height: 50%;
  }

  .right-li-head {
    height: 30%;
    display: flex;
    justify-items: center;
    justify-content: center;
  }

  .blog-search {
    width: 50%;
  }

  .blog-search-input ::v-deep input {
    border: 0;
    caret-color: #999;
    transition: all 0.3s ease;
  }

  .blog-search-input ::v-deep input:focus {
    border-color: #fff;
    box-shadow: 0 0 0 #999;
    outline: none;
  }

  .blog-search-input ::v-deep input::placeholder {
    color: #b4b4b4;
    font-size: 14px;
  }

  .blog-search-input ::v-deep input:focus::placeholder {
    color: #999;
    font-style: normal;
  }

  .el-container {
    margin: 0;
    padding: 0;
    overflow: no-display;
    overflow-scrolling: auto;
    width: 100vw;
    height: 100vh;
    display: block;
    justify-content: left;
    list-style: none;
  }

  .flex-top {
    width: 100%;
    height: 25%;
    z-index: 1000;
    background-color: rgba(0, 0, 0, 0);
    list-style: none;
  }

  .el-header {
    width: 100%;
    background-color: rgb(13 13 13);
  }

  .el-main {
    width: 100%;
    min-height: 75%;
    height: 75%;
    background-color: #fff;
    list-style: none;

    padding: 0;
    margin: 0;
  }

  .card {
    width: 100%;
    height: 100%;
    background-color: #fff;
    padding: 0;
    margin: 0;


    display: grid;
    grid-template-columns: repeat(5, 1fr); /* 5列 */
    grid-template-rows: repeat(2, 1fr);    /* 2行 */
    gap: 0;  /* 网格之间的间距 */
  }

  .card-empty {
    width: 100%;
    height: 100%;
    background-color: #fff;
    padding: 0;
    margin: 0;


    display: flex;
    justify-content: center;
    justify-items: center;
  }

  .page {
    position: fixed;
    display: flex;
    justify-content: center;
    bottom: 0;
    width: 100%;
    background-color: rgba(0,0,0,0);
  }

  .img-main {
    padding: 0;
    margin: 0;
    width: 100%;
    height: 100%;
    background-color: #fff;

    display: contents;
  }

  .img-item {
    list-style: none;
    width: 100%;
    height: 100%;
    background-color: #fff;

    display: flex;
    justify-content: center;  /* 内容居中 */
    align-items: center;      /* 垂直居中 */
  }

  .card-item {
    width: 65%;
    height: 70%;
    margin: 10px;
    border-radius: 10px;
  }

</style>
