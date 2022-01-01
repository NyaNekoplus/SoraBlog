<template>
  <div>
    <section id="main-container" :class="sidebarOn?'open':''">
      <div class="scrollbar" id="bar" style="width: 19%; background: #00bbff none repeat scroll 0% 0%;"></div>
      <div class="headertop filter-dot headertop-bar" style="height: auto;"> <!-- headertop-bar : banner处的一行白色-->

        <div id="banner_wave_1"></div>
        <div id="banner_wave_2"></div>

        <figure id="centerbg" class="centerbg"><!-- style="height: 938px;"-->
          <div class="focusinfo no-select" >
            <div class="header-tou">
              <a href="https://sora.vin/About">
                <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png">
              </a>
            </div>
            <!--<h1 class="center-text glitch is-glitching Ubuntu-font" data-text="遠い空へ">遠い空へ</h1>-->
            <div class="header-info">
              <!--
              <i class="fa fa-quote-left"></i>
              <i class="fa fa-quote-right"></i>
              -->
              <p> 思绪如风，来得快去得也快，偶尔在这里停留 </p>
              <div class="top-social_v2">
                <!--
                <li id="bg-pre">
                  <img class="flipx" src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg">
                </li>
                <li id="bg-next">
                  <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg">
                </li>
                -->
                <li v-for="link in links" :key="link.title">
                  <a :title="link.title" :href="link.link" target="_blank" rel="noopener">
                    <img :src="link.icon"></a>
                </li>
              </div>
            </div>
          </div>

        </figure>
        <div id="video-container" style="">
          <video id="bgvideo" class="video" video-name="" src="" preload="auto" width="auto"></video><!-- style="min-height: 1344px;"-->
          <div id="video-btn" class="loadvideo videolive"></div>
          <!--<div id="video-add"></div>-->
          <div class="video-stu"></div>
        </div>

        <div class="headertop-down faa-float animated" @click="headertop_down0">
          <span> <i class="fa fa-chevron-down" aria-hidden="true"></i> </span>
        </div>
      </div>

      <div id="page" class="site-wrapper">

        <sora-bar :is-index="true"/>

        <div class="blank" style="padding-top: 0px;"></div>

        <sora-view/>

        <sora-footer/>

        <div :class="`openNav no-select ${sidebarOn?'open sidebar-on':'sidebar-off'}`">
          <div class="iconflat no-select control-icon" @click="showSidebar">
            <div class="icon"></div>
          </div>
          <div class="site-branding">
            <h1 class="site-title">
              <brand/>
            </h1>
          </div>
        </div> <!--在section内 -->
      </div>
    </section>

    <sora-setting/>

  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: 'SoraLayout',
  components: {
    SoraView: () => import('./View'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
    Brand: () => import('@/layouts/sora/widgets/brand'),
  },
  data: () => ({
    coverHeight: 300,
    //sidebarOn: false,
    about: {},
  }),
  methods: {
    ...mapMutations(['setAbout','setSidebarOn']),
    showSidebar(){
      this.setSidebarOn(!this.$store.getters.sidebarOn);
    },
    headertop_down() {
      let b = document.querySelector(".site-content")
      console.log(b);
      this.startMove(b, {'scroll-margin-top': this.coverHeight});
      //var coverOffset=$('#content').offset().top;$('html,body').animate({scrollTop:coverOffset},600);}
    },
    headertop_down0() {
      let pos = document.documentElement.scrollTop;
      let h = this.coverHeight
      let distance = h - pos;
      let step = 200;
      let part = distance / step;
      if (pos < h) {
        setTimeout(function () {
          smoothDown();
        }, 10);
      }
      function smoothDown() {
        if (pos < h) {
          pos += part;
          document.documentElement.scrollTop = pos
          setTimeout(smoothDown, 10 * Math.sin(Math.random()))
        }
      }
    },
  },
  computed: {
    sidebarOn(){
      return this.$store.getters.sidebarOn
    },
    links(){
      return this.$store.getters.links;
    }
  },
  mounted() {
    if (window.innerHeight>860){
      this.coverHeight = window.innerHeight;
      document.getElementById('centerbg').style.height = this.coverHeight.toString() + 'px';
    }else {
      document.getElementById('centerbg').style.height = 'auto;'
    }
  },
  created() {
    this.$store.state.loading = true
    /*
    getAboutMe().then(response => {
      if (response.state === this.$STATE.SUCCESS){
        this.about = response.data;
        this.setAbout(this.about);
      }
    })*/
    this.$store.state.loading = false
  }
}
</script>
<style>
@media (max-width: 860px) {
  .headertop-down {
    display: none;
  }
}
.headertop-down {
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


.centerbg{
  background-image: url('https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/landscape.jfif');
  background-position: center center;
  background-attachment: inherit;
}



@-webkit-keyframes wave1/*Safari and Chrome*/
{
  0% { left:-1500px;bottom:-5px}
  50%{ left:0px;bottom:0px}
  100%{ left:-1500px;bottom:-5px}
}

@-webkit-keyframes wave2
{
  0% { left:-1450px}
  50%{ left:0px}
  100%{left:-1450px}

}
@keyframes wave1/*Safari and Chrome*/
{
  0% { left:-1500px;bottom:-5px}
  50%{ left:0px;bottom:0px}
  100%{ left:-1500px;bottom:-5px}
}

@keyframes wave2
{
  0% { left:-1450px}
  50%{ left:0px}
  100%{left:-1450px}

}
#banner_wave_1 {
  width:auto;
  height:65px;
  background:url("https://cdn.jsdelivr.net/gh/yremp/resource@2.0/img/other/wave1.png") repeat-x;
  _filter:alpha(opacity=80);
  position:absolute;
  bottom:0;
  width:400%;
  left:-236px;
  z-index:5;
  opacity:1;
  transiton-property:opacity,bottom;
  animation:wave1 90s infinite;
  -webkit-animation:wave1 90s infinite;
}
#banner_wave_2 {
  width:auto;
  height:80px;
  background:url("https://cdn.jsdelivr.net/gh/yremp/resource@2.0/img/other/wave2.png") repeat-x;
  _filter:alpha(opacity=80);
  position:absolute;
  bottom:0;
  width:400%;
  left:0;
  z-index:4;
  opacity:1;
  transiton-property:opacity,bottom;
  animation:wave2 90s infinite;
  -webkit-animation:wave2 90s infinite;
}
.banner_wave_hide,.banner_wave_hide_fit_skin {
  opacity:0 !important;
  bottom:-999px !important
}
@media(max-width:860px) {
  #banner_wave_1,#banner_wave_2 {
    display:none;/*inline-block;*/
  }
  @-webkit-keyframes wave1/*Safari and Chrome*/
  {
    0% { left:-750px;bottom:-2px}
    50%{ left:0px;bottom:0px}
    100%{ left:-750px;bottom:-2px}
  }

  @-webkit-keyframes wave2
  {
    0% { left:-750px}
    50%{ left:0px}
    100%{left:-750px}

  }
  @keyframes wave1/*Safari and Chrome*/
  {
    0% { left:-750px;bottom:-2px}
    50%{ left:0px;bottom:0px}
    100%{ left:-750px;bottom:-2px}
  }

  @keyframes wave2
  {
    0% { left:-750px}
    50%{ left:0px}
    100%{left:-750px}

  }
  #banner_wave_1 {
    width:auto;
    height:65px;
    background:url("https://cdn.jsdelivr.net/gh/yremp/resource@2.0/img/other/wave1.png") repeat-x;
    _filter:alpha(opacity=80);
    position:absolute;
    bottom:0;
    width:400%;
    left:750px;
    z-index:5;
    opacity:1;
    transiton-property:opacity,bottom;
    animation:wave1 90s infinite;
    -webkit-animation:wave1 60s infinite;
  }
  #banner_wave_2 {
    width:auto;
    height:80px;
    background:url("https://cdn.jsdelivr.net/gh/yremp/resource@2.0/img/other/wave2.png") repeat-x;
    _filter:alpha(opacity=80);
    position:absolute;
    bottom:0;
    width:400%;
    left:0;
    z-index:4;
    opacity:1;
    transiton-property:opacity,bottom;
    animation:wave2 90s infinite;
    -webkit-animation:wave2 60s infinite;
  }
}
</style>