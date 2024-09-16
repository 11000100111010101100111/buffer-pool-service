<template>
  <div
    :class="['notification', { 'collapsed': !visible, 'left': isLeft}]"
    @mousedown="startDrag"
    @click="toggle"
    :style="topStyle"
  >
    <span v-if="visible" :class="['notification-text', 'unable-select-element']">{{ message }}</span>
    <button v-if="visible" @click.stop="close" class="close-button el-icon-error"/>
  </div>
</template>

<script>
  export default {
    name: "Top",
    props: {
      message: {
        type: String,
        default: '欢迎访问！部分功能维护中...'
      },
      duration: {
        type: Number,
        default: 3000 // 默认持续时间为5000毫秒
      }
    },
    data() {
      return {
        visible: true, // 控制告示的显示与隐藏
        isLeft: false, // 控制告示是否靠左
        position: {
          x: window.innerWidth - 240, // 默认位置（右侧）
          y: window.innerHeight - 100 // 距离顶部20px
        },
        times: this.duration / 1000,
        isDragging: false, // 控制拖动状态
        dragOffset: {x: 0, y: 0}, // 存储拖动的偏移量
        topStyle: {left: `${window.innerWidth - 240}px`, top: `${window.innerHeight - 100}px`},
      };
    },
    mounted() {
      // 设置定时器在指定的持续时间后关闭告示
      setTimeout(() => {
        this.close();
      }, this.duration);
      // 添加鼠标抬起事件监听器
      window.addEventListener('mouseup', this.stopDrag);
      window.addEventListener('mousemove', this.onDrag);
    },
    beforeDestroy() {
      // 移除事件监听器
      window.removeEventListener('mouseup', this.stopDrag);
      window.removeEventListener('mousemove', this.onDrag);
    },
    methods: {
      close() {
        this.visible = false; // 关闭告示
        this.isLeft = this.position.x < window.innerWidth / 2; // 判断停靠方向

        let left = this.isLeft ? 0 : window.innerWidth - 10;
        let radius = this.isLeft ? '0 8px 8px 0' : '8px 0 0 8px';
        this.topStyle = {left: `${left}px`, top: `${this.position.y}px`, 'border-radius': radius};
      },
      toggle() {
        if (!this.visible) {
          this.visible = true; // 再次展开
          this.times = this.duration / 1000;
          this.position.x = this.isLeft ? 0 : window.innerWidth - 240; // 根据方向设置位置
          this.topStyle = {left: `${this.position.x}px`, top: `${this.position.y}px`, 'border-radius': '4px'};
        }
      },
      startDrag(event) {
        this.isDragging = true; // 开始拖动
        this.dragOffset.x = event.clientX - this.position.x; // 计算偏移量
        this.dragOffset.y = event.clientY - this.position.y;
      },
      stopDrag() {
        this.isDragging = false; // 停止拖动
      },
      onDrag(event) {
        if (this.isDragging && this.visible) {
          this.position.x = event.clientX - this.dragOffset.x; // 更新位置
          this.position.y = event.clientY - this.dragOffset.y;

          // 限制告示位置在窗口内
          this.position.x = Math.max(0, Math.min(this.position.x, window.innerWidth - 240));
          this.position.y = Math.max(0, Math.min(this.position.y, window.innerHeight - 100));

          this.topStyle = {left: `${this.position.x}px`, top: `${this.position.y}px`, 'border-radius': '4px'};

          if (this.position.x <= 100 || this.position.y >= window.innerWidth - 100) {
            setTimeout(() => {
              this.close()
            }, this.duration);
          }
        }
      }
    }
  }
</script>

<style scoped>
  .notification {
    position: fixed;
    width: 220px;
    background-color: #ffcc00; /* 背景色 */
    color: #333; /* 字体颜色 */
    text-align: center; /* 文本居中 */
    padding: 20px 20px; /* 内边距 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 阴影效果 */
    z-index: 1000; /* 层级，确保在最上面 */
    transition: all 0.3s ease, opacity 0.3s ease; /* 过渡效果 */
    cursor: grab; /* 鼠标指针样式 */
    border-radius: 4px;
  }

  .notification.collapsed {
    width: 10px;
    /*transform: scale(0.8);*/
    /*opacity: 0.9; !* 半透明效果 *!*/
  }

  .notification.left {
    left: 20px; /* 靠左时的距离 */
    right: auto; /* 取消右边距 */
  }

  .notification-text {
    font-size: 18px;
  }

  .close-button {
    position: absolute; /* 绝对定位 */
    top: 1px; /* 距离顶部5px */
    right: 1px; /* 距离右边10px */
    background: none;
    border: none;
    font-size: 20px;
    color: #333;
    cursor: pointer;
  }
</style>
