<template>
  <footer id="colophon" class="site-footer" role="contentinfo">
    <div class="site-info">
      <div class="footertext">
        <div class="img-preload">
          <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/wordpress-rotating-ball-o.svg">
          <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/disqus-preloader.svg">
        </div>
        <p style="color: #666666;">Copyright &copy;{{new Date().getUTCFullYear()}} Vincent Tsai All Rights Reserved.</p>
      </div>
      <div class="footer-device">
        <p style="font-family: 'Ubuntu', sans-serif;">
					<span style="color: #b9b9b9;">
            Theme <a href="https://2heng.xin/theme-sakura/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Sakura</a> <i class="iconfont icon-sakura rotating" style="color: #ffc0cb;display:inline-block"></i> by <a href="https://2heng.xin/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Mashiro</a>
					</span>
        </p>
        <p style="font-family: 'Ubuntu', sans-serif;">
					<span style="color: #b9b9b9;">
            Refactored<i class="iconfont icon-sakura rotating" style="color: #ffc0cb;display:inline-block"></i> by <a href="https://2heng.xin/" target="_blank" style="color: #b9b9b9;;text-decoration: underline dotted rgba(0, 0, 0, .1);">Vincent Tsai</a> using Vue
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