<template>
  <div>
    <Profile :simple="true"/>
  </div>
</template>

<script>
  import Profile from "./profile/index";

  export default {
    components: {Profile},
    name: "userPage"
  }
</script>

<style scoped>

</style>
