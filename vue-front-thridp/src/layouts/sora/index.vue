<template>
  <div>
    <section id="main-container" :class="sidebarOn?'open':''">
      <div class="scrollbar" id="bar" style="width: 19%; background: #00bbff none repeat scroll 0% 0%;"></div>
      <div class="headertop filter-dot" style="height: auto;"> <!-- headertop-bar : banner处的一行白色-->

        <div id="banner_wave_1"></div>
        <div id="banner_wave_2"></div>

        <figure id="centerbg" class="centerbg"><!-- style="height: 938px;"-->
          <div class="focusinfo no-select" >
            <div class="header-tou">
              <a :href="about.link">
                <img :src="about.avatarUrl">
              </a>
            </div>
            <!--<h1 class="center-text glitch is-glitching Ubuntu-font" data-text="遠い空へ">遠い空へ</h1>-->
            <div class="header-info">
              <!--
              <i class="fa fa-quote-left"></i>
              <i class="fa fa-quote-right"></i>
              -->
              <p> {{ about.motto }} </p>
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
                  <a :title="link.title">
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
            <h1 class="site-title"><a href="https://www.lovewx.club"></a>悠遠的蒼穹</h1>
          </div>
        </div> <!--在section内 -->


      </div>

    </section>

    <div id="mo-nav" :class="sidebarOn?'open':''">
      <div class="m-avatar"><img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png"></div>
      <p style="text-align: center; color: #333; font-weight: 900; font-family: 'Ubuntu', sans-serif; letter-spacing: 1.5px">
        Mashiro Sama...</p>
      <p style="text-align: center; word-spacing: 20px;">
        <a href="http://twitter.com/2hengxin" class="fa fa-twitter" target="_blank" style="color: #00aced"></a>
        <a href="http://weibo.com/2960337711" class="fa fa-weibo" target="_blank" style="color: #dd4b39"></a>
        <a href="http://github.com/mashirozx" class="fa fa-github" target="_blank" style="color: #333"></a>
        <a onclick="mail_me()" class="fa fa-envelope" style="color: #ffbf00"></a>
      </p>
      <div class="m-search">
        <form class="m-search-form" method="get" action="https://2heng.xin" role="search">
          <input class="m-search-input" type="search" name="s" placeholder="搜索..." required="">
        </form>
      </div>
      <ul id="menu-new-1" class="menu">
        <li v-for="item in navbarItem" :key="item.name">
          <a :href="item.link" aria-current="page">
            <span class="faa-parent animated-hover"><i :class="item.icon" aria-hidden="true"></i> {{ item.name }}</span>
          </a>
          <ul v-if="item.children.length" class="sub-menu">
            <li v-for="child in item.children" :key="child.name"><a :href="child.link"><i :class="child.icon" aria-hidden="true"></i>{{ child.name }}</a></li>
          </ul>
        </li>
        <!--
        <li class="current-menu-item">
          <a href="/" aria-current="page">
            <span class="faa-parent animated-hover"><i class="fa fa-fort-awesome faa-horizontal" aria-hidden="true"></i> 首页</span>
          </a>
        </li>
        <li>
          <a href="/time-series/">
                  <span class="faa-parent animated-hover">
                    <i class="fa fa-archive faa-shake" aria-hidden="true"></i> 归档
                  </span>
          </a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/archives/hacking/"><i class="icon-uni304F" aria-hidden="true"></i>极客</a></li>
            <li><a href="https://2heng.xin/archives/article/"><i class="fa fa-file-text-o" aria-hidden="true"></i>文章</a></li>
            <li><a href="https://2heng.xin/archives/review/"><i class="fa fa-quote-right" aria-hidden="true"></i>影评</a></li>
            <li><a href="https://2heng.xin/archives/thingking/"><i class="fa fa-commenting-o" aria-hidden="true"></i> 随想</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://mashiro.top/"><i class="fa fa-book" aria-hidden="true"></i>笔记</a></li>
          </ul>
        </li>
        <li><a><span class="faa-parent animated-hover"><i class="fa fa-list-ul faa-vertical" aria-hidden="true"></i> 清单</span></a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/book-list/"><i class="fa fa-th-list faa-bounce" aria-hidden="true"></i>书单</a></li>
            <li><a href="https://2heng.xin/bangumi/"><i class="fa fa-film faa-vertical" aria-hidden="true"></i>番组</a></li>
            <li><a href="https://2heng.xin/music/"><i class="fa fa-headphones" aria-hidden="true"></i> 歌单</a></li>
            <li><a href="https://2heng.xin/hearthstone-deck-index/"><i class="iconfont icon-Hearthstone" aria-hidden="true"></i> 卡组</a></li>
          </ul>
        </li>
        <li><a href="/board"><span class="faa-parent animated-hover"><i
            class="fa fa-pencil-square-o  faa-tada" aria-hidden="true"></i> 留言板</span></a></li>
        <li><a href="https://2heng.xin/friends/"><span class="faa-parent animated-hover"><i
            class="fa fa-link faa-shake" aria-hidden="true"></i> 友人帐</span></a></li>
        <li><a href="https://2heng.xin/donate/"><span class="faa-parent animated-hover"><i
            class="fa fa-heart faa-pulse" aria-hidden="true"></i> 赞赏</span></a></li>
        <li><a href="#"><span class="faa-parent animated-hover"><i class="fa fa-leaf faa-wrench" aria-hidden="true"></i> 关于</span></a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/about/"><i class="fa fa-grav" aria-hidden="true"></i> 我？</a></li>
            <li><a href="https://2heng.xin/analytics/"><i class="fa fa-area-chart" aria-hidden="true"></i> 统计</a></li>
            <li><a href="https://2heng.xin/server-status/"><i class="fa fa-heartbeat" aria-hidden="true"></i>监控</a></li>
            <li><a href="https://2heng.xin/theme-sakura/"><i class="iconfont icon-sakura" aria-hidden="true"></i>主题</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://2heng.xin/sitemap_index.xml"><i class="fa fa-map-signs" aria-hidden="true"></i> MAP</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://2heng.xin/feed"><i class="fa fa-rss " aria-hidden="true"></i>RSS</a></li>
          </ul>
        </li>
        <li><a href="/tags"><span class="faa-parent animated-hover"><i class="fa fa-android faa-vertical" aria-hidden="true"></i> 标签</span></a></li>
        <li>
          <a target="_blank" rel="noopener noreferrer" href="https://hello.2heng.xin/about/more">
            <span class="faa-parent animated-hover"><i class="fa fa-tree faa-pulse" aria-hidden="true"></i> 时光轴</span>
          </a>
        </li>
        -->
      </ul>
      <p style="text-align: center; font-size: 13px; color: #b9b9b9;">© 2021 Vincent Tsai</p></div>
    <sora-setting/>


  </div>
</template>

<script>
import {mapMutations} from "vuex";
import {getAboutMe} from "../../api/user";

export default {
  name: 'SoraLayout',
  components: {
    SoraView: () => import('./View'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
  },
  data: () => ({
    coverHeight: 300,
    sidebarOn: false,

    about: {},

  }),
  methods: {
    ...mapMutations(['setAbout']),
    showSidebar(){
      this.sidebarOn = !this.sidebarOn;
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
    navbarItem(){
      return this.$store.getters.navbarItems;
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
    getAboutMe().then(response => {
      if (response.state === this.$STATE.SUCCESS){
        this.about = response.data;
        this.setAbout(this.about);
      }
    })
  }


}
</script>
<style>
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
    display:inline-block;

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