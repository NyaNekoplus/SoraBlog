<template>
  <header class="site-header no-select is-homepage gizle" :class="navClass" role="banner">
    <div class="site-top">
      <div class="site-branding">
        <!-- logo start 左上角logo-->
        <div class="site-title" v-if="akina_logo">
          <a href="<?php bloginfo('url');?>"><img src="<?php echo akina_option('akina_logo'); ?>"></a>
        </div>
        <span class="site-title" v-else>
          <span class="logolink serif">
            <a href="<?php bloginfo('url');?>">
              <span class="site-name">{{ siteName }}</span>
            </a>
          </span>
        </span>
        <!-- logo end -->
      </div>
      <!-- .site-branding -->


      <div class="header-user-avatar">
        <a href="https://2heng.xin/wp-login.php">
        <img class="faa-shake animated-hover"
             src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/none.png"
             width="30" height="30">
        </a>
        <div class="header-user-menu">
          <div class="herder-user-name no-logged">
            Whether to <a href="/login" target="_blank" style="color:#333;font-weight:bold;text-decoration:none">log in</a> now?
          </div>
        </div>
      </div>
      <!-- 搜索按钮 -->
      <div v-if="topSearch" class="searchbox"><i class="iconfont js-toggle-search iconsearch icon-search"></i></div>

      <!-- 上方导航栏，用v-for -->
      <div class="lower-container">
        <div class="lower">
          <div id="show-nav" class="showNav" v-if="shownav">
            <div class="line line1"></div>
            <div class="line line2"></div>
            <div class="line line3"></div>
          </div>
          <nav class="mobile-fit-control hide">
            <ul id="menu-new" class="menu">
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
              <li><a href="https://2heng.xin/comment/"><span class="faa-parent animated-hover"><i
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
              <li><a href="https://2heng.xin/client/"><span class="faa-parent animated-hover"><i class="fa fa-android faa-vertical" aria-hidden="true"></i> APP</span></a></li>
              <li><a target="_blank" rel="noopener noreferrer" href="https://hello.2heng.xin/about/more"><span class="faa-parent animated-hover"><i class="fa fa-tree faa-pulse" aria-hidden="true"></i> 小森林</span></a>
              </li>
            </ul>
          </nav>
          <!-- #site-navigation -->
        </div>
      </div>

    </div>
  </header><!-- #masthead -->
  <!--
  轮播图和顶栏之间
  <div class="blank" style="background-color: rgb(255, 255, 255); padding-top: 0px;"></div>
  <?php if (get_post_meta(get_the_ID(), 'cover_type', true) == 'hls') {
              the_video_headPattern_hls();
          } elseif (get_post_meta(get_the_ID(), 'cover_type', true) == 'normal') {
              the_video_headPattern_normal();
          }else {
              the_headPattern();
          } ?>
  -->


</template>

<script>
export default {
  name: "SoraBar",
  props: {
    isIndex: {
      type: Boolean,
    }
  },
  data: () => ({
    navClass: 'yya',
    akina_logo: false,
    shownav: false,
    topSearch: true,

    siteName: '悠远的苍穹',

    topBarItems: [
      {
        name: '',
        link: '',
        childrens: []
      }
    ],
    links: [
      'Dashboard',
      'Messages',
      'Profile',
      'Updates',
    ],
  }),
  methods: {
    isNavbarShow(){
      const scrollLen = document.documentElement.scrollTop;
      if (scrollLen === 0){
        this.navClass = 'sabit';
      }else {
        this.navClass = 'yya';
      }
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
      let precent = elem.scrollTop/(elem.offsetHeight-elem.clientHeight)*100.0;
      //console.log('precent: '+precent);
      document.getElementById('bar').style.width = precent.toString() + '%';
    }
  },
  mounted () {
    if (this.isIndex){
      this.navClass = 'sabit';
      window.addEventListener('scroll', this.isNavbarShow)
    }
    window.addEventListener('scroll', this.changeScrollBarLenth)
  },
  beforeDestroy() {
    console.log('beforeDestory, remove EventListener was called');
    window.removeEventListener('scroll', this.isNavbarShow);
    window.removeEventListener('scroll', this.changeScrollBarLenth)
  }
}
</script>

<style scoped>
.site-top .lower nav {
  display: block !important;
}

.site-top .lower-container{
  display: block;
  position: absolute;
  left: 50%;
  min-width: 758.4px;
  pointer-events: none;
}
.site-top .lower {
  display: inline-block;
  margin: 15px 0 0 10px;
  font-size: 16px;
  position: relative;
  left: -50%;
  pointer-events: auto !important;
}
.site-top .lower nav {
  position: relative;
  float: right;
  display: none;
  animation: searchbox .2s;
}


</style>