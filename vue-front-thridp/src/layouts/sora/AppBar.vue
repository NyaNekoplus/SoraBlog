<template>
  <header :class="`site-header no-select is-homepage gizle ${navClass}`" @mouseenter="isIndex && (navClass='yya')" @mouseleave="isIndex && (navClass='sabit')" role="banner">
    <div class="site-top">
      <div class="site-branding">
        <!-- logo start 左上角logo-->
        <div class="site-title" v-if="akina_logo">
          <a href="<?php bloginfo('url');?>"><img src="<?php echo akina_option('akina_logo'); ?>"></a>
        </div>
        <span class="site-title" v-else :style="`${shownav?'':'display: none;'}`"><!--style="display: none;"-->
          <span class="logolink serif">
            <a href="<?php bloginfo('url');?>">
              <span class="site-name">{{ siteName }}</span>
            </a>
          </span>
        </span>
        <!-- logo end -->
      </div>
      <!-- .site-branding -->




      <!-- 搜索按钮
      <div v-if="topSearch" class="searchbox"><i class="iconfont js-toggle-search iconsearch icon-search"></i></div>
      -->
      <div class="lower-cantiner">
        <div class="lower">
          <div id="show-nav" :class="`mobile-fit ${shownav?'showNav':'hideNav'}`" @click="collapseNav"><!--hideNav-->
            <div class="line line1"></div>
            <div class="line line2"></div>
            <div class="line line3"></div>
          </div>
          <nav :class="`mobile-fit-control ${shownav?'hide':'navbar'}`"><!--navbar-->
            <ul id="menu-new" class="menu">
              <li v-for="item in topBarItems" :key="item.name">
                <a :href="item.link" aria-current="page">
                  <span class="faa-parent animated-hover"><i :class="item.icon" aria-hidden="true"></i> {{ item.name }}</span>
                </a>
                <ul v-if="item.children.length" class="sub-menu">
                  <li v-for="child in item.children" :key="child.name"><a :href="child.link"><i :class="child.icon" aria-hidden="true"></i>{{ child.name }}</a></li>
                </ul>
              </li>
              <!--
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
              <li><a href="/donate"><span class="faa-parent animated-hover"><i
                  class="fa fa-heart faa-pulse" aria-hidden="true"></i> 功德箱</span></a></li>
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
          </nav>
          <!-- #site-navigation -->
        </div>
      </div>

      <div class="header-user-avatar">
        <a href="/login">
          <img class="faa-shake animated-hover"
               src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/none.png"
               width="30" height="30">
        </a>
        <div class="header-user-menu">
          <div class="herder-user-name no-logged">
            <div v-if="isLogin">
              <div>
                <a @click="signOut" href="/login" target="_blank" style="color:#333;font-weight:bold;text-decoration:none">Log out</a>
              </div>
              <div>
                <a href="/profile" target="_blank" style="color:#333;font-weight:bold;text-decoration:none">Profile</a>
              </div>
            </div>
            <div v-else>
              <div>
                Whether to <a href="/login" target="_blank" style="color:#333;font-weight:bold;text-decoration:none">Sign in</a> now?
              </div>
              <div>
                <a href="/login" target="_blank" style="color:#333;font-weight:bold;text-decoration:none">Sign up</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="searchbox"><i class="iconfont js-toggle-search iconsearch icon-search"></i></div>
      <!-- 上方导航栏，用v-for
      <div class="lower-container">
      </div>
      -->

    </div>


  </header><!-- #masthead -->
</template>

<script>
import {mapMutations} from "vuex";
import {authToken} from "../../api/user";
import {getCookie,setCookie,removeCookie} from "../../utils/cookie";

export default {
  name: "SoraBar",
  props: {
    isIndex: {
      type: Boolean,
    }
  },
  data: () => ({
    isLogin: false,

    navClass: 'yya',

    //cd-top faa-float animated cd-is-visible cd-fade-out
    akina_logo: false,
    shownav: true,
    topSearch: true,

    siteName: '悠远的苍穹',

    topBarItems: [
      {
        name: '首页',
        icon: 'fa fa-fort-awesome faa-horizontal',
        link: '/',
        children: []
      },
      {
        name: '归档',
        icon: 'fa fa-archive faa-shake',
        link: '/List',
        children: [
          {
            name: 'Life',
            icon: 'fa fa-commenting-o',
            link: '/Life',
          },
          {
            name: 'Tech',
            icon: 'fa fa-file-text-o',
            link: '/Tech',
          }
        ]
      },
      {
        name: '留言板',
        icon: 'fa fa-pencil-square-o faa-tada',
        link: '/Board',
        children: []
      },
      {
        name: '友情链接',
        icon: 'fa fa-link faa-shake',
        link: '/Link',
        children: []
      },
      {
        name: '功德箱',
        icon: 'fa fa-heart faa-pulse',
        link: '/Donate',
        children: []
      },
      {
        name: '关于',
        icon: 'fa fa-leaf faa-wrench',
        link: '/About',
        children: [
          {
            name: '我',
            icon: 'fa fa-grav',
            link: '/About',
            children: []
          },
          {
            name: '监控',
            icon: 'fa fa-heartbeat',
            link: '/Watch',
            children: []
          },
          {
            name: '统计',
            icon: 'fa fa-area-chart',
            link: '/Statistics',
            children: []
          },
          {
            name: '地图',
            icon: 'fa fa-map-signs',
            link: '/Map',
            children: []
          }
        ]
      },
      {
        name: '标签',
        icon: 'fa fa-android faa-vertical',
        link: '/Tags',
        children: []
      },
      {
        name: '时光轴',
        icon: 'fa fa-film faa-vertical',
        link: '/Timeline',
        children: []
      }
    ],
  }),
  methods: {
    ...mapMutations(['removeToken','setLoginState','setUserInfo','removeUserInfo','setThemeWidgetState']),
    isNavbarShow(){
      const scrollLen = document.documentElement.scrollTop;
      if (scrollLen === 0){
        this.navClass = 'sabit';
      }else {
        this.navClass = 'yya';
      }
    },
    isToTopShow(){
      const scrollLen = document.documentElement.scrollTop;
      if (scrollLen === 0){
        this.setThemeWidgetState(false);
        //this.toTopClass = 'cd-top faa-float animated';
      }else {
        this.setThemeWidgetState(true);
        //this.toTopClass = 'cd-top faa-float animated cd-is-visible cd-fade-out';
      }
    },
    collapseNav(){
      this.shownav = !this.shownav;
    },
    changeScrollBarLenth(){
      let elem = document.documentElement;
/*
      console.log('offsetHeight: '+elem.offsetHeight);
      console.log('offsetTop: '+elem.offsetTop);
      console.log('scrollHeight: '+elem.scrollHeight);
      console.log('scrollTop: '+elem.scrollTop);
      console.log('clientHeight: '+elem.clientHeight);
*/
      let percent = elem.scrollTop/(elem.offsetHeight-elem.clientHeight)*100.0;
      document.getElementById('bar').style.width = percent.toString() + '%';
    },
    loadCover() {
      let imgList = document.getElementsByClassName('lazyload');
      //console.log(imgList.length);
      for (let i=0;i<imgList.length;i++){
        //console.log(imgList[i].hasAttribute('data-isLoaded'));
        if (!imgList[i].hasAttribute('data-isLoaded')){
          let imgToTop = imgList[i].offsetTop;
          let viewportHeight = document.documentElement.clientHeight;
          let scrollHeight = document.documentElement.scrollTop;
          //console.log(imgToTop-scrollHeight);
          if (imgToTop-scrollHeight < viewportHeight + 50){
            imgList[i].setAttribute('src',imgList[i].getAttribute('data-src'))
            imgList[i].setAttribute('data-isLoaded', 1);
          }
        }
      }
    },
    verifyLogin(){
      let token = this.$store.getters.token;
      if (token !== ''){
        console.log('验证store中的token不为空，写入cookie');
        setCookie("token", token, 7);
      }else {
        console.log('store中token为空，从cookie中获取');
        token = getCookie("token");
      }
      console.log('token: '+token);
      if (token !== undefined){
        authToken(token).then(response=>{
          if(response.state === this.$STATE.SUCCESS){
            this.isLogin = true;
            this.setUserInfo(response.data); // TODO
            //this.setToken(response.data);
            console.log(response.data);
          }else {
            this.isLogin = false;
            removeCookie("token");
            console.log(response.data);
            alert("后台消息："+response.data.message);
          }
          this.setLoginState(this.isLogin);
        });
      }else {
        this.isLogin = false;
        this.setLoginState(this.isLogin);
      }
    },
    signOut(){
      this.isLogin = false;
      this.setLoginState(false);
      this.removeToken();
      this.removeUserInfo();
      removeCookie("token");
    }
  },
  created() {
    this.verifyLogin();
  },
  mounted () {
    if (this.isIndex){
      this.navClass = 'sabit';
      //let e = document.documentElement;
      //e.scrollTop = e.offsetHeight;
      window.addEventListener('scroll', this.isNavbarShow)
    }
    window.addEventListener('scroll', this.isToTopShow)
    window.addEventListener('scroll', this.changeScrollBarLenth)
    window.addEventListener('scroll', this.loadCover);
  },
  beforeDestroy() {
    console.log('beforeDestory, remove EventListener was called');
    window.removeEventListener('scroll', this.isNavbarShow);
    window.removeEventListener('scroll', this.isToTopShow);
    window.removeEventListener('scroll', this.changeScrollBarLenth)
    window.removeEventListener('scroll', this.loadCover);
  }
}
</script>

<style scoped>

</style>