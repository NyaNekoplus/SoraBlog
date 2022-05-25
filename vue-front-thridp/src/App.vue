<template>
  <div>
    <div id="ple-loader-wraps3" v-if="loading">
      <div id="ple-animates">
        <div class="spinner"></div>
      </div>
    </div>
    <router-view/>
    <Mplayer/>
  </div>
</template>

<script>
import Mplayer from "./components/Player/Mplayer";

export default {
  name: 'Sora',
  components: {
    Mplayer
  },
  metaInfo: {
    title: 'Sora',
    titleTemplate: '%s | 悠远的苍穹',
    htmlAttrs: {lang: 'en'},
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
    ],
  },
  computed:{
    loading(){
      return this.$store.getters.loading
    }
  }
}
</script>

<style scoped>
#ple-loader-wraps99, #ple-loader-wraps1, #ple-loader-wraps2, #ple-loader-wraps3, #ple-loader-wraps4 {
  background: #fff;
  color: transparent;
  position: fixed;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  text-indent: -9999px;
  z-index: 99991;
}
#ple-animates {
  display: inline-block;
  position: absolute;
  left: 50%;
  top: 50%;
}
#ple-loader-wraps3 .spinner {
  width: 35px;
  height: 35px;
  padding: 0px;
  border-radius: 100%;
  border: 2px solid rgba(0, 0, 0, 0.95);
  border-right-color: rgba(0, 0, 0, 0.25);
  border-bottom-color: rgba(0, 0, 0, 0.25);
  -webkit-animation: pleloader3 0.8s linear infinite;
  animation: pleloader3 0.8s linear infinite;
}
@keyframes pleloader3 {
  from {transform: rotate(0deg);}
  to {transform: rotate(360deg);}
}
@-webkit-keyframes pleloader3 {
  from {-webkit-transform: rotate(0deg);}
  to {-webkit-transform: rotate(360deg);}
}
</style>
