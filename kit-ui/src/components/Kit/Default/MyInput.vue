<template>
  <el-input ref="input"
            class="blog-search-input"
            :placeholder="placeholder"
            @input="sendValue"
            :disabled="disabled"
            v-model="inputValue"/>
</template>

<script>
    export default {
        name: "MyInput",
        props: {
          placeholder: {
            type: String,
            default: '请输入内容......'
          },
          value: {
            type: String,
            default: null
          },
          disabled: {
            type: Boolean,
            default: false
          }
        },
        data(){
          return {
            inputValue: this.value
          }
        },
        beforeDestroy() {
          // 移除事件监听器
          window.removeEventListener('keydown', this.handleKeyDown);
        },
        methods: {
          sendValue() {
            this.$emit('update-value', this.inputValue);
          },
          handleKeyDown(event) {
            if (event.key === 'Enter') {
              this.$emit('enter-pressed', this.inputValue);
            }
          }
        },
      mounted() {
        window.addEventListener('keydown', this.handleKeyDown);
        const input = this.$refs.input.$el.querySelector('.el-input__inner');
        const height = input.offsetHeight;
        input.style.borderRadius = `${height / 2}px`;
      }
    }
</script>

<style scoped>
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
</style>
