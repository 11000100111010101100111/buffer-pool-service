<template>
  <mavon-editor class="md-page"
                :s_readmodel="true"
                v-model="markdownContent"></mavon-editor>
</template>

<script>
  //编辑 博客
    export default {
        name: "EditBlog",
      data() {
        return {
          toolbars: {},
          markdownContent: '# Hello MavonEditor!'
        }
      },
      mounted() {
        this.$message.info("这里暂时在加工，拼命手撸中...")
      },
      beforeDestroy() {

      },
      beforeUnmount() {
        this.editor.destroy()
      },
    }
</script>

<style scoped>

</style>
