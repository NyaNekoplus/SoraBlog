<template>
  <div class="carousel-wrap" @mouseenter="enter()" @mouseleave="leave()">
    <transition-group
        name="list"
        tag="ul"
        @after-leave="autoPlay"
        class="ac_scroll-ul"
    >
      <li
          v-for="(item, index) in slides"
          :key="item"
          v-show="currentIndex === index"
          class="ac_scroll-item"
      >
        <img class="banner" :src="slides[currentIndex].src">
      </li>
    </transition-group>
    <!-- 向左按钮 -->
    <img v-show="isShowIocn" class="icon icon-left" src="" @click="handlePre()"/>
    <!-- 向右按钮 -->
    <img v-show="isShowIocn" class="icon icon-right" src="" @click="handleNext()"/>
    <!-- 底下的白线 -->
    <div class="block-wrap">
      <div @click="choosed(index)" v-for="(item, index) in slides" :key="index" :class="{active: index === currentIndex}">
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "index",
  props: {
    // 要从父组件接收的传值
    slides: {
      type: Array,
      //default: []
    }
  },
  data() {
    return {
      currentIndex: 0,
      //leftIcon: require('../assets/left-f.png'),
      //rightIcon: require('../assets/right-r.png'),
      isShowIocn: false
    }
  },
  mounted(){
    this.autoPlay()
  },
  methods: {
    choosed(index) {
      this.currentIndex = index
    },
    handlePre() {
      if(this.currentIndex===0){
        this.currentIndex = this.slides.length-1
      }else{
        this.currentIndex -= 1
      }
    },
    handleNext() {
      if(this.currentIndex === this.slides.length-1){
        this.currentIndex = 0
      }else{
        this.currentIndex += 1
      }
    },
    enter() {
      this.isShowIocn = true
    },
    leave() {
      this.isShowIocn = false
    },

    autoPlay() {
      setTimeout(()=> {
        this.currentIndex++;
        if (this.currentIndex > this.slides.length-1) {
          this.currentIndex = 0
        }
      }, 3000)
    }
  }
}
</script>
<style scoped>
.carousel-wrap {
  position: relative;
  width: 100%;
  height: 550px;
  margin-top: 73px;
  background: #51CDCB;
  overflow: hidden;
}

.banner {
  width: 100%;
}

.icon {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 25px;
}
.icon.icon-left{
  left: 10px;
}

.icon.icon-right {
  right: 10px;
}

.block-wrap {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
}
.block-wrap div {
  height: 1px;
  width: 40px;
  border: 1px solid #ccc;
  background: #ccc;
  display: inline-block;
  margin-left: 5px;
  border-radius: 2px;
  cursor: pointer;
}
.block-wrap .active {
  border: 1px solid #fff;
  background: #fff;
}
/** 滚动动画 */
.ac_scroll-ul {
  position: relative;
  border: 1px solid springgreen;
}
.ac_scroll-item {
  position: absolute;
}
.list-enter-to {
  transition: all 2s ease;
  transform: translateX(0);
}
.list-leave-active {
  transition: all 2s ease;
  transform: translateX(-100%)
}
.list-enter {
  transform: translateX(100%)
}
.list-leave {
  transform: translateX(0)
}
</style>
