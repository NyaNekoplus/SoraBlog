<template>
  <transition name="slide-fade">

    <div>
      <material-alert
        v-if="notifyFlag"
        color="info"
      >
        {{message}}
      </material-alert>
    </div>

  </transition>
</template>

<script>
  export default {
    name: 'message',
    data () {
      return {
        notifyFlag: true, // 是否显示
        time: 1500,//取消按钮是否显示
        message: '', // 文本内容
        type: '', // 类型
        timer: '',
        timeFlag: false,
      }
    },
    watch:{
      timeFlag(){
        if(this.timeFlag){
          this.notifyFlag = false;
          window.clearTimeout(this.timer);
        }
      }
    },
    created(){
      this.timer = setTimeout(() => {
        this.timeFlag = true;
      }, this.time);

    },
    beforeDestroy(){
      window.clearTimeout(this.timer);
    }
  }

</script>
<style scoped>
.slide-fade-leave-active {
  transition: all .2s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to{
  transform: translateX(10px);
  opacity: 0;
}

@keyframes show{
  0%{
    right: -350px;
  }
  100%{
    right: 10px;
  }
}
</style>
