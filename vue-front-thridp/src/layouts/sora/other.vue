<template>
  <section id="main-container">
    <!--
    <div class="scrollbar" id="bar" style="width: 29%; background: orange none repeat scroll 0% 0%;"></div>
    -->

    <div id="page" class="site-wrapper"
         style="background-repeat: repeat;background-image: url('https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/snow.png');">

      <sora-bar :is-index="false"/>


      <sora-other-view/>


      <sora-footer/>



      <sora-setting/>

    </div>

  </section>
</template>

<script>
export default {
  name: 'SoraLayout',
  components: {
    SoraOtherView: () => import('./OtherView'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
  },
  data: () => ({
    coverHeight: 1440,

  }),
  methods: {
    /*
    scrollBar(){
      if(document.body.clientWidth>860){
        window.scroll(function(){
          let s=$(window).scrollTop();
          let a=$(document).height();
          let b=$(window).height();
          let result=parseInt(s/(a-b)*100);$("#bar").css("width",result+"%");
        }
      }else {

      }
    }
    */
  },
  mounted() {
    //this.coverHeight =
  }

}
</script>
<style>


</style>