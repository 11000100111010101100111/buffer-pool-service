<template>
  <div id="app">
    <BaiDuMap :is-public="false"/>
  </div>
</template>

<script>
  import BaiDuMap from './wather/BaiDuMap.vue';
  export default {
    name: "Index",
    components: {
      BaiDuMap,
    },
    data() {
      return {
        // 版本号
        version: "3.8.7"
      };
    },
    methods: {
      goTarget(href) {
        window.open(href, "_blank");
      }
    }
  };
</script>

<style scoped>

</style>
