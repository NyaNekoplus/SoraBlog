<template>
  <footer id="colophon" class="site-footer" role="contentinfo">
    <div class="site-info">
      <div class="footertext">
        <div class="img-preload">
          <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/wordpress-rotating-ball-o.svg">
          <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/disqus-preloader.svg">
        </div>
        <p style="color: #666666;">Copyright &copy;{{new Date().getUTCFullYear()}} Vincent Tsai All Rights Reserved.</p>
      </div>
      <div class="footer-device">
        <!-- 底部图标 -->
        <a href="https://www.jsdelivr.com/" target="_blank" alt="Free CDN" rel="nofollow">
          <img src="https://www.yremp.live/images/2019/08/17/JsDelivr_Logo0bd9a.png" alt="jsDelivr" style="height: 2em; max-height: 2em;padding-bottom: 0px;">
        </a>

        <a href="https://github.com/" target="_blank" alt="Github" rel="nofollow">
          <img src="https://yremp.live/wp-content/uploads/2019/07/Github.png" alt="github" style="height: 2.2em; max-height: 2.5em;padding-bottom: 0px;">
        </a>
      </div>
      <div class="footer-device">
        <p style="font-family: 'Ubuntu', sans-serif;">
					<span style="color: #b9b9b9;">
            Theme <a href="https://2heng.xin/theme-sakura/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Sakura</a> <i class="iconfont icon-sakura rotating" style="color: #ffc0cb;display:inline-block"></i> by <a href="https://2heng.xin/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Mashiro</a>
					</span>
        </p>
        <p style="font-family: 'Ubuntu', sans-serif;">
					<span style="color: #b9b9b9;">
            Refactored<i class="iconfont icon-sakura rotating" style="color: #ffc0cb;display:inline-block"></i> by <a href="https://sora.vin/About" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Vincent Tsai</a> using Vue
					</span>
        </p>
      </div>
    </div><!-- .site-info -->
  </footer><!-- #colophon -->
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
