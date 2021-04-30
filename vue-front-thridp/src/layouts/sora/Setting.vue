<template>
  <div>
    <div id="mo-nav" :class="sidebarOn?'open':''">
      <div class="m-avatar">
        <img :src="about.avatarUrl">
      </div>
      <p style="text-align: center; color: #333; font-weight: 900; font-family: 'Ubuntu', sans-serif; letter-spacing: 1.5px">
        {{ about.name }}
      </p>
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
      </ul>
      <p style="text-align: center; font-size: 13px; color: #b9b9b9;">© {{new Date().getUTCFullYear()}} Vincent Tsai</p>
    </div>
    <!-- m-nav-center -->
    <a href="#" :class="isTop?'cd-top faa-float animated cd-is-visible cd-fade-out':'cd-top faa-float animated'"></a><!-- javascript:void(0); style="top: 0px;"-->

    <button id="moblieGoTop" title="Go to top"><i class="fa fa-chevron-up" aria-hidden="true"></i></button>
    <button id="moblieDarkLight"><i class="fa fa-moon-o" aria-hidden="true"></i></button>
    <!-- search start -->
    <form class="js-search search-form search-form--modal" method="get" action="<?php echo home_url(); ?>" role="search">
      <div class="search-form__inner">
        <div v-if="liveSearch">
          <div class="micro">
            <i class="iconfont icon-search"></i>
            <input id="search-input" class="text-input" type="search" name="s" placeholder="<?php _e('Want to find something?', 'sakura') /*想要找点什么呢*/?>" required>
          </div>
          <div class="ins-section-wrapper">
            <a id="Ty" href="#"></a>
            <div class="ins-section-container" id="PostlistBox"></div>
          </div>
        </div>
        <div v-else>
          <div class="micro">
            <p class="micro mb-">php _e('Want to find something?', 'sakura') /*想要找点什么呢*/</p>
            <i class="iconfont icon-search"></i>
            <input class="text-input" type="search" name="s" placeholder="<?php _e('Search', 'sakura') ?>" required>
          </div>
        </div>
      </div>
      <div class="search_close"></div>
    </form>
    <!-- search end -->
    <!--?php wp_footer(); ?-->
    <div class="site-statistics" v-if="siteStatistics">
      <!--<script type="text/javascript"><?php echo akina_option('site_statistics'); ?></script>-->
    </div>

    <div class="changeSkin-gear no-select" :style="`background: none; visibility: visible; bottom: ${isTop?`0px`:`-999px`};`">
      <div class="keys">
        <span id="open-skinMenu" @click="showThemePanel">
		      <i class="iconfont icon-gear inline-block rotating"></i>&nbsp; 切换主题 | SCHEME TOOL
        </span>
      </div>
    </div>
    <div :class="displayTheme?'skin-menu no-select show':'skin-menu no-select'">
      <div class="theme-controls row-container">
        <ul class="menu-list">
          <li v-for="theme in themeList" :title="theme.name" @click="changeTheme(theme.param)">
            <i :class="theme.icon" aria-hidden="true"></i>
          </li><!--Default-->
        </ul>
      </div>
      <div class="font-family-controls row-container">
        <button type="button" class="control-btn-serif selected" data-mode="serif"
                onclick="mashiro_global.font_control.change_font()">Serif</button>
        <button type="button" class="control-btn-sans-serif" data-mode="sans-serif"
                onclick="mashiro_global.font_control.change_font()">Sans Serif</button>
      </div>
    </div>
    <canvas id="night-mode-cover"></canvas>

    <aside id="secondary" class="widget-area" role="complementary" style="left: -400px;" v-if="sakuraWidget">
      <div class="heading">php _e('Widgets') /*小工具*/ </div>
      <div class="sakura_widget">
        php if (function_exists('dynamic_sidebar') && dynamic_sidebar('sakura_widget')) : endif;
      </div>
      <div class="show-hide-wrap">
        <button class="show-hide">
          <svg xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 32 32">
          <path d="M22 16l-10.105-10.6-1.895 1.987 8.211 8.613-8.211 8.612 1.895 1.988 8.211-8.613z"></path>
          </svg>
        </button>
      </div>
    </aside>

    <div id="aplayer-float" style="z-index: 100;" v-if="aplayerServer"
         class="aplayer"
         data-id="<?php echo akina_option('aplayer_playlistid', ''); ?>"
         data-server="<?php echo akina_option('aplayer_server'); ?>"
         data-type="playlist"
         data-fixed="true"
         data-theme="orange">
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex';
export default {
  name: "SoraSetting",
  data: () => ({
    displayTheme: false,
    themeList: [
      {
        name: '默认',
        param: 'wide-screen',
        icon: 'fa fa-television',
      },
      {
        name: '窄屏',
        param: 'narrow-screen',
        icon: 'fa fa-fort-awesome faa-horizontal',
      },
      {
        name: '雪原',
        param: 'snow',
        icon: 'iconfont icon-dots',
      },
      {
        name: '樱花飞舞',
        param: 'sakura',
        icon: 'iconfont icon-sakura',
      },
      {
        name: '格子',
        param: 'slack',
        icon: 'fa fa-slack',
      },
      {
        name: 'Bing',
        param: 'bing',
        icon: 'iconfont icon-bing',
      },
      {
        name: 'PIXIV',
        param: 'pixiv',
        icon: 'iconfont icon-pixiv',
      },
      {
        name: '夜间',
        param: 'dark',
        icon: 'fa fa-moon-o',
      }
    ],
    liveSearch: true,
    siteStatistics: false,
    sakuraWidget: true,
    aplayerServer: true,


    toTopClass: 'cd-top faa-float animated',
  }),
  methods: {
    ...mapMutations(['setWideScreenCover','setSakuraEffect','setSnowEffect','setBackground','setDark']),
    showThemePanel(){
      this.displayTheme = !this.displayTheme;
    },
    dynamicLoadJs(){
      // 创建script标签，引入外部文件
      let script = document.createElement('script')

      script.type = 'text/javascript'
      script.src = 'https://cdn.jsdelivr.net/gh/yremp/yremp-js@1.5/sakura.js'

      let effect = this.$refs.dynamicEffect;
      effect.appendChild(script);
    },
    changeTheme(param){
      switch (param){
        case 'wide-screen':
          this.setBackground(0);
          this.setWideScreenCover(true);
          break;
        case 'narrow-screen':
          this.setWideScreenCover(false);
          break;
        case 'snow':
          snow_on = !snow_on;
          this.setSnowEffect(snow_on);
          break;
        case 'sakura':
          if (sakura_on) {
            this.setSakuraEffect(false);stopp();}
          else {
            this.setSakuraEffect(true);startSakura();}
          break;
        case 'slack':
          this.setBackground(2);
          break;
        case 'pixiv':
          this.setBackground(1);
          break;
        case 'dark':
          this.setDark(!this.$store.getters.dark);
          this.setBackground(3);
          break;
        case 'sakura_snow':
          if (sakura_on&&snow_on){
            stopp();snow_on=false;
            this.setSakuraEffect(false);this.setSnowEffect(false);
          }else if (sakura_on===false&&snow_on===false){
            startSakura();snow_on=true;
            this.setSakuraEffect(true);this.setSnowEffect(true);
          }else {
            if (sakura_on){
              snow_on=true;this.setSnowEffect(true);break;
            }else {
              startSakura();this.setSakuraEffect(true);break;
            }
          }
          break;
        default:
          break;
      }
    }
  },
  computed: {
    sidebarOn(){
      return this.$store.getters.sidebarOn
    },
    about(){
      return this.$store.getters.about
    },
    navbarItem(){
      return this.$store.getters.navbarItems;
    },
    isTop(){
      return this.$store.getters.isThemeWidgetShow;
    },
  },
  mounted() {
    console.log('setting.vue mounted '+this.$store.getters.sakuraEffect)
    if (this.$store.getters.sakuraEffect)startSakura();
    if (this.$store.getters.snowEffect)snow_on = true;
    document.body.style = this.$store.getters.background;
    document.getElementById('night-mode-cover').style.visibility = this.$store.getters.dark?'visible':'hidden';
  }
}
</script>

<style scoped>

</style>