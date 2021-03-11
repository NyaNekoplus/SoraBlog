<template>
  <section id="main-container">
    <div class="scrollbar" id="bar" style="width: 29%; background: orange none repeat scroll 0% 0%;"></div>

    <div id="page" class="site-wrapper" style="background-repeat: repeat;background-image: url(https://view.moezx.cc/images/2018/01/10/star02.png);">

      <sora-bar :is-index="false"/>


      <sora-view/>


      <sora-footer/>

      <sora-setting/>

    </div>

  </section>
</template>

<script>
export default {
  name: 'SoraLayout',
  components: {
    SoraView: () => import('./View'),
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
.centerbg{
  background-image: url("../../../public/bg.jpg");
}
.headertop-down{
  position: absolute;
  bottom: 50px;
  left: 50%;
  cursor: pointer;
  z-index: 5;
}
.headertop-down i {
  font-size: 28px;
  color:
      #fff;
  -ms-transform: scale(1.5,1);
  -webkit-transform: scale(1.5,1);
  transform: scale(1.5,1);
}
</style>