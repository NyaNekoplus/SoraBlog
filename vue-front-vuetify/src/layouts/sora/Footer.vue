<template>
  <footer id="colophon" class="site-footer" role="contentinfo">
    <div class="site-info" theme-info="Sakura v<?php echo SAKURA_VERSION; ?>">
      <div class="footertext">
        <div class="img-preload">
          <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/wordpress-rotating-ball-o.svg">
          <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/disqus-preloader.svg">
        </div>
        <p style="color: #666666;"><?php echo akina_option('footer_info', ''); ?></p>
      </div>
      <div class="footer-device">
        <p style="font-family: 'Ubuntu', sans-serif;">
					<span style="color: #b9b9b9;">
						能保留下面两个链接吗？算是我一个小小的心愿吧~
            Theme <a href="https://2heng.xin/theme-sakura/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Sakura</a> <i class="iconfont icon-sakura rotating" style="color: #ffc0cb;display:inline-block"></i> by <a href="https://2heng.xin/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Mashiro</a>
					</span>
        </p>
      </div>
    </div><!-- .site-info -->
  </footer><!-- #colophon -->

  <div class="openNav no-select">
    <div class="iconflat no-select">
      <div class="icon"></div>
    </div>
    <div class="site-branding">
      <div class="site-title" v-if="akina_logo">
        <a href="<?php bloginfo('url');?>" > <!-- bloginfo(): 显示wordpress设置中的值  -->
          <img src="logo_url">
        </a>
      </div>
      <h1 class="site-title" v-else>
        <a href="<?php bloginfo('url');?>" >bloginfo('name')</a>
      </h1>
    </div>
  </div><!-- m-nav-bar -->

  <!-- m-nav-center -->
  <div id="mo-nav">
    <div class="m-avatar">
      <!--
      <?php $ava = akina_option('focus_logo') ? akina_option('focus_logo') : get_template_directory_uri().'/images/avatar.jpg'; ?>

      -->
      <img src="<?php echo $ava ?>">
    </div>
    <div class="m-search">
      <form class="m-search-form" method="get" action="<?php echo home_url(); ?>" role="search">
        <input class="m-search-input" type="search" name="s" placeholder="<?php _e('Search...', 'sakura') /*搜索...*/?>" required>
      </form>
    </div>
    <?php wp_nav_menu( array( 'depth' => 2, 'theme_location' => 'primary', 'container' => false ) ); ?>
  </div><!-- m-nav-center end -->
  <a class="cd-top faa-float animated "></a>
  <button id="moblieGoTop" title="Go to top"><i class="fa fa-chevron-up" aria-hidden="true"></i></button>
  <button id="moblieDarkLight"><i class="fa fa-moon-o" aria-hidden="true"></i></button>
  <!-- search start -->
  <form class="js-search search-form search-form--modal" method="get" action="<?php echo home_url(); ?>" role="search">
    <div class="search-form__inner">
      <div v-if="akina_option('live_search')">
        <div class="micro">
          <i class="iconfont icon-search"></i>
          <input id="search-input" class="text-input" type="search" name="s" placeholder="<?php _e('Want to find something?', 'sakura') /*想要找点什么呢*/?>" required>
        </div>
        <div class="ins-section-wrapper">
          <a id="Ty" href="#"></a>
          <div class="ins-section-container" id="PostlistBox"></div>
        </div>
      </div>
      <div class="micro" v-else>
        <p class="micro mb-"><!--<?php _e('Want to find something?', 'sakura') /*想要找点什么呢*/?>--></p>
        <i class="iconfont icon-search"></i>
        <input class="text-input" type="search" name="s" placeholder="<?php _e('Search', 'sakura') ?>" required>
      </div>
    </div>
    <div class="search_close"></div>
  </form>
  <!-- search end -->
  <!--
  <?php wp_footer(); ?>
  -->

  <div class="site-statistics" v-if="site_statistics">
    <script type="text/javascript"><?php echo akina_option('site_statistics'); ?></script>
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

  <aside v-if="akina_option('sakura_widget')" id="secondary" class="widget-area" role="complementary" style="left: -400px;">
    <div class="heading">
      <!--<?php _e('Widgets') /*小工具*/ ?>
      在WordPress 中，__()函数和__e()函数被用来识别php文件中被标示的、需要被翻译成其它语言或本地化的字符串。-->
    </div>
    <div class="sakura_widget">
      <!--<?php if (function_exists('dynamic_sidebar') && dynamic_sidebar('sakura_widget')) : endif; ?>-->
    </div>
    <div class="show-hide-wrap"><button class="show-hide"><svg xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 32 32"><path d="M22 16l-10.105-10.6-1.895 1.987 8.211 8.613-8.211 8.612 1.895 1.988 8.211-8.613z"></path></svg></button></div>
  </aside>


  <div v-if="akina_option('aplayer_server') != 'off'"
      id="aplayer-float" style="z-index: 100;"
       class="aplayer"
       data-id="<?php echo akina_option('aplayer_playlistid', ''); ?>"
       data-server="<?php echo akina_option('aplayer_server'); ?>"
       data-type="playlist"
       data-fixed="true"
       data-theme="orange">
  </div>

</template>

<script>
export default {
  name: "SoraFooter",
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>