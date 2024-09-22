<template>
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
                     @enter-pressed="enter"
                     :disabled="searchDisabled"
                     :value="searchValue"/>
          </div>
        </li>
      </ul>
    </li><!--
 --><li class="flex-top"></li><!--
 --><li class="el-main">
      <ul class="ul-panel ul-panel-main">
        <li class="sub-li sub-li-main left-li left-li-main">

        </li>
        <li class="sub-li mid-li sub-li-main mid-li-main">

        </li>
        <li class="sub-li right-li sub-li-main right-li-main">

        </li>
      </ul>
    </li><!--
 --><li class="el-footer">
    </li><!--
 --></ul>
</template>

<script>
  import MyInput from "../components/Kit/Default/MyInput";
  export default {
    name: "blog",
    components:{
      MyInput
      // myMD
    },
    data() {
      return {
        flexTop: false,

        searchValue: '',
        searchDisabled: false
      }
    },
    methods: {
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
        this.searchValue = value;
        this.$message.warning("暂时不能查询任何博客，现在还没有准备好数据和内容")
      }
    },
    mounted() {
      this.observeTopChange();
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

  .ul-panel-main {
    width: 100vw;
    height: 200vh;
    background-color: #fff;
    display: flex;
    justify-content: left;
    list-style: none;
  }

  .sub-li {
    /*height: 100%;*/
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
    /* 默认样式 */
    border: 0;
    caret-color: #999; /* 光标颜色 */
    transition: all 0.3s ease;
  }

  .blog-search-input ::v-deep input:focus {
    /* 获取焦点时的样式 */
    border-color: #fff; /* 设置边框颜色 */
    box-shadow: 0 0 0 #999; /* 添加阴影 */
    outline: none; /* 移除默认的浏览器轮廓 */
  }

  .blog-search-input ::v-deep input::placeholder {
    color: #b4b4b4;
    font-size: 14px;
  }

  /* 调整焦点时 placeholder 样式 */
  .blog-search-input ::v-deep input:focus::placeholder {
    color: #999;
    font-style: normal;
  }

  /*.left-li {*/
  /*  width: 20%;*/
  /*  background-color: #1c84c6;*/
  /*}*/

  /*.mid-li {*/
  /*  width: 60%;*/
  /*  background-color: #7a6df0;*/
  /*}*/

  /*.right-li {*/
  /*  width: 20%;*/
  /*  background-color: #0aae9c;*/
  /*}*/

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
    position: fixed;
    background-color: #9a6e3a;
  }

  .el-main {
    width: 100%;
    min-height: 70%;
    background-color: #fff;
    list-style: none;
  }

  .el-footer {
    width: 100%;
    height: 20%;
    background-color: #7a6df0;
    list-style: none;
  }

  .blog-container {

  }

</style>
