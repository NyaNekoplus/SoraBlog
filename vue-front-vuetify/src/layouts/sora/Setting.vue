<template>
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
  name: "SoraSetting",
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>