<template>
  <div>
    <!-- m-nav-center -->

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
        icon: 'iconfont icon-sakura',
      },
      {
        name: 'Bing',
        param: 'snow',
        icon: 'iconfont icon-bing',
      },
      {
        name: '夜间',
        param: 'night',
        icon: 'fa fa-moon-o',
      },
    ],
    liveSearch: true,
    siteStatistics: false,
    sakuraWidget: true,
    aplayerServer: true,
  }),
  methods: {
    ...mapMutations(['setWideScreenCover']),
    showThemePanel(){
      this.displayTheme = !this.displayTheme;
    },
    changeTheme(param){
      switch (param){
        case 'wide-screen':
          this.setWideScreenCover(true);
          break;
        case 'narrow-screen':
          this.setWideScreenCover(false);
          break;
        case 'snow':
          break;
      }
    }
  },
  computed: {
    isTop(){
      return this.$store.getters.isThemeWidgetShow;
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>