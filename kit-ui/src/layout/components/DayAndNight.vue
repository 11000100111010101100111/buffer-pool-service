<template>
  <div class="toggle-switch" :class="{ night: isNight }" @click="toggleMode">
    <div class="toggle-knob">
      <span v-if="isNight">🌙</span>
      <span v-else>☀️</span>
    </div>
  </div>
</template>

<script>
    export default {
        name: "DayAndNight",
      data() {
        return {
          isNight: false // 初始状态为白天
        };
      },
      methods: {
        toggleMode() {
          this.isNight = !this.isNight;
          this.$emit('mode-changed', this.isNight ? 'night' : 'day'); // 触发事件，通知父组件
        }
      }
    }
</script>

<style scoped>
  .toggle-switch {
    width: 60px;
    height: 30px;
    background-color: #f5f5f5;
    border-radius: 15px;
    position: relative;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .toggle-switch.night {
    background-color: #4a4a4a;
  }

  .toggle-knob {
    width: 28px;
    height: 28px;
    background-color: #fff;
    border-radius: 50%;
    position: absolute;
    top: 1px;
    left: 1px;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: left 0.3s ease;
  }

  .toggle-switch.night .toggle-knob {
    left: 31px;
  }
</style>
