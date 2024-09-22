<template>
  <div class="support-item" @mousemove="handleFor3DMouseMove"  @mouseleave="resetFor3DRotation">
    <el-image :src="src" class="support-a" :style="boxStyle"/>
    <div>
      <p class="p-title unable-select-element">{{title}}</p>
      <p class="p-text unable-select-element">{{text}}</p>
    </div>
  </div>
</template>

<script>
    export default {
        name: "img3D",
        props: {
          src: {
            type: String,
            required: true,
          },
          title: {
            type: String,
            default: ''
          },
          text: {
            type: String,
            default: ''
          }
        },
        data() {
          return {
            for3D: {
              rotateX: 0,
              rotateY: 0,
              topLeft: 0,
              topRight: 0,
              bottomRight: 0,
              bottomLeft: 0
            }
          }
        },
      mounted() {
      },
      computed: {
        boxStyle() {
          return {
            borderRadius: `${this.for3D.topLeft}% ${this.for3D.topRight}% ${this.for3D.bottomRight}% ${this.for3D.bottomLeft}%`,
            transform: `rotateX(${this.for3D.rotateX}deg) rotateY(${this.for3D.rotateY}deg)`,
            transition: 'all 0.3s ease-out'
          };
        }
      },
      methods:{
        handleFor3DMouseMove(event) {
          const container = event.currentTarget;
          const width = container.offsetWidth;
          const height = container.offsetHeight;
          const mouseX = event.clientX - container.getBoundingClientRect().left;
          const mouseY = event.clientY - container.getBoundingClientRect().top;

          this.for3D.rotateY = (mouseX / width - 0.5) * 20;
          this.for3D.rotateX = (mouseY / height - 0.5) * -20;

          this.for3D.topLeft = this.calculateBorderRadius(mouseX, mouseY, 0, 0, width, height);
          this.for3D.topRight = this.calculateBorderRadius(mouseX, mouseY, width, 0, width, height);
          this.for3D.bottomRight = this.calculateBorderRadius(mouseX, mouseY, width, height, width, height);
          this.for3D.bottomLeft = this.calculateBorderRadius(mouseX, mouseY, 0, height, width, height);
        },
        resetFor3DRotation() {
          this.for3D.rotateX = 0;
          this.for3D.rotateY = 0;
          this.for3D.topLeft = 0;
          this.for3D.topRight = 0;
          this.for3D.bottomRight = 0;
          this.for3D.bottomLeft = 0;
        },
        calculateBorderRadius(mouseX, mouseY, cornerX, cornerY, width, height) {
          const distance = Math.sqrt(Math.pow(Math.abs(mouseX - cornerX), 2) + Math.pow(Math.abs(mouseY - cornerY), 2));
          const maxDistance = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
          const radius = (1 - distance / maxDistance) * 20.00 ;
          return Math.max(0, radius);
        }
      }
    }
</script>

<style scoped>
  .support-item {
    border-radius: 4px;
    width: 500px;
    max-width: 80vw;
    min-width: 40vw;
    aspect-ratio: 6 / 5;
    margin: 1rem;
    background-color:rgb(190, 62, 80);
    perspective: 1000px; /* 控制3D透视效果 */
  }

  .support-a {
    position: relative;
    width: 100%;
    background-color: #fff;
    aspect-ratio: 3 / 2;
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat;

    transition: transform 0.1s ease-out;
    transform-style: preserve-3d;
    transform: rotateX(0deg) rotateY(0deg);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
  }

  .support-a:hover {
    box-shadow: 0 0 5px #fff;
  }

  .p-title {
    color: #fff;
    font-family: "Raleway", sans-serif;
    font-weight: bold;
    transition: all 0.3s ease;
    cursor: pointer;
  }
  .p-title:hover {
    border-left: 0.2rem solid #fff;
    padding-left: 0.2rem;
  }

  .p-text {
    color: #fff;
    font-family: "Courier New", Courier, monospace;
  }
</style>
