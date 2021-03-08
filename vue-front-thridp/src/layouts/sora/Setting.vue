<template>
  <div>
    <!-- m-nav-center -->
    <div id="mo-nav">
      <div class="m-avatar">
        php $ava = akina_option('focus_logo') ? akina_option('focus_logo') : get_template_directory_uri().'/images/avatar.jpg';
        <img src="<?php echo $ava ?>">
      </div>
      <div class="m-search">
        <form class="m-search-form" method="get" action="<?php echo home_url(); ?>" role="search">
          <input class="m-search-input" type="search" name="s" placeholder="<?php _e('Search...', 'sakura') /*搜索...*/?>" required>
        </form>
      </div>
      <ul id="menu-menu-2" class="menu"><li class="current-menu-item"><a href="https://sakura.2heng.xin/" aria-current="page">Home</a></li>
        <li><a href="https://sakura.2heng.xin/archiv/">Timeline</a></li>
        <li><a href="#">Categories</a>
          <ul class="sub-menu">
            <li><a href="https://sakura.2heng.xin/father/">Father</a></li>
            <li><a href="https://sakura.2heng.xin/father/child/">Child</a></li>
          </ul>
        </li>
        <li><a href="https://sakura.2heng.xin/links/">Links</a></li>
        <li><a href="https://sakura.2heng.xin/about/">About</a></li>
        <li><a href="https://sakura.2heng.xin/sample-page/">More</a>
          <ul class="sub-menu">
            <li><a href="https://sakura.2heng.xin/sample-page/">Sample</a></li>
            <li><a href="https://sakura.2heng.xin/privacy-policy/">Privacy</a></li>
            <li><a href="https://sakura.2heng.xin/wp-login.php">Login</a></li>
          </ul>
        </li>
        <li><a href="https://sakura.2heng.xin/login/">Login</a></li>
        <li><a href="https://sakura.2heng.xin/music/">Music</a></li>
      </ul>
    </div><!-- m-nav-center end -->
    <a class="cd-top faa-float animated "></a>
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

    <div class="changeSkin-gear no-select" style="bottom: -999px;">
      <div class="keys">
        <span id="open-skinMenu">
		<i class="iconfont icon-gear inline-block rotating"></i>&nbsp; 切换主题 | SCHEME TOOL
        </span>
      </div>
    </div>
    <div class="skin-menu no-select">
      <div class="theme-controls row-container">
        <ul class="menu-list">
          <li id="white-bg">
            <i class="fa fa-television" aria-hidden="true"></i>
          </li><!--Default-->
          <li id="sakura-bg">
            <i class="iconfont icon-sakura"></i>
          </li><!--Sakura-->
          <li id="gribs-bg">
            <i class="fa fa-slack" aria-hidden="true"></i>
          </li><!--Grids-->
          <li id="KAdots-bg">
            <i class="iconfont icon-dots"></i>
          </li><!--Dots-->
          <li id="totem-bg">
            <i class="fa fa-superpowers" aria-hidden="true"></i>
          </li><!--Orange-->
          <li id="pixiv-bg">
            <i class="iconfont icon-pixiv"></i>
          </li><!--Start-->
          <li id="bing-bg">
            <i class="iconfont icon-bing"></i>
          </li><!--Bing-->
          <li id="dark-bg">
            <i class="fa fa-moon-o" aria-hidden="true"></i>
          </li><!--Night-->
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
export default {
  name: "SoraSetting",
  data: () => ({
    liveSearch: true,
    siteStatistics: false,
    sakuraWidget: true,
    aplayerServer: true,
  }),
}
</script>

<style scoped>

</style>