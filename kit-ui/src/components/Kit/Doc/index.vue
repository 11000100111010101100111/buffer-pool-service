<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'KitDoc',
  data() {
    return {
      url: 'http://wwww.seeuagain.vip/doc'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
