<template>
  <div id="footer">
    <div class="_footer-container">
      <div class="_footer-content">
        <div class="_footer-content-item"><span class="_icon-text">关于pixiv</span><i class="_pico-12 _icon-menu"></i>
          <div class="content-popup about"><img src="https://s.pximg.net/www/images/beta/pixiv_logo.png"
                                                class="footer-pixiv-logo"><span class="footer-pixiv-motto-desc">pixiv是提供插画等作品的投稿、阅览服务的「插画交流网站」。这里有各种各样不同风格的投稿作品，我们还会举办官方、用户企画的各种比赛。</span>
            <div></div>
            <dl class="links">
              <dt>服务</dt>
              <dd>
                <ul>
                  <li><a href="https://comic.pixiv.net/" target="_blank">pixiv COMIC</a></li>
                  <li><a href="https://novel.pixiv.net/" target="_blank">pixiv小说</a></li>
                  <li><a href="https://factory.pixiv.net/" target="_blank">pixivFACTORY</a><a
                      href="https://factory.pixiv.net/books" target="_blank">BOOKS</a></li>
                  <li><a href="https://www.fanbox.cc/" target="_blank" rel="noopener">pixivFANBOX</a></li>
                  <li><a href="https://booth.pm" target="_blank">BOOTH</a></li>
                  <li><a href="https://booth.pm/apollo/" target="_blank">APOLLO</a></li>
                  <li><a href="https://www.pixivision.net/zh/" target="_blank">pixivision</a></li>
                  <li><a href="https://sketch.pixiv.net/" target="_blank">pixiv Sketch</a></li>
                  <li><a href="https://sensei.pixiv.net/" target="_blank">sensei</a></li>
                  <li><a href="https://en-dic.pixiv.net/" target="_blank">pixiv百科全书</a></li>
                  <li><a href="https://studio.vroid.com/" target="_blank">VRoid Studio</a></li>
                  <li><a href="https://hub.vroid.com/" target="_blank">VRoid Hub</a></li>
                </ul>
              </dd>
            </dl>
            <dl class="links">
              <dt>如何使用本站</dt>
              <dd>
                <ul>
                  <li><a href="https://www.pixiv.net/terms.php">使用条款</a></li>
                  <li><a href="https://www.pixiv.net/guideline.php">本站方针</a></li>
                  <li><a href="https://www.pixiv.net/privacy.php">隐私政策</a></li>
                  <li><a href="https://www.pixiv.net/support.php">联系我们</a></li>
                  <li><a
                      href="https://www.pixiv.help/hc/?utm_campaign=accounts_index&amp;utm_medium=help_link&amp;utm_source=www_pixiv"
                      target="_blank" rel="noopener">帮助</a></li>
                </ul>
              </dd>
            </dl>
            <dl class="links">
              <dt>公告</dt>
              <dd>
                <ul>
                  <li><a href="/info.php">公告</a></li>
                  <li><a href="https://inside.pixiv.blog/" target="_blank">pixiv inside</a></li>
                  <li><a href="https://twitter.com/pixiv" target="_blank">Twitter</a></li>
                  <li><a href="https://www.facebook.com/pixiv" target="_blank">Facebook</a></li>
                  <li><a href="https://plus.google.com/+pixiv" target="_blank" rel="publisher">Google+</a></li>
                  <li><a href="https://www.instagram.com/pixiv/" target="_blank">Instagram</a></li>
                  <li><a href="https://www.plurk.com/pixiv_tw" target="_blank">Plurk</a></li>
                  <li><a href="https://www.weibo.com/pixivcn" target="_blank">weibo</a></li>
                </ul>
              </dd>
            </dl>
            <dl class="links">
              <dt>广告</dt>
              <dd>
                <ul>
                  <li><a href="https://www.pixiv.net/ads">刊登广告</a></li>
                  <li><a href="https://www.pixiv.net/ads">官方插画比赛</a></li>
                  <li><a href="https://www.pixiv.net/ads">下载广告资料</a></li>
                </ul>
              </dd>
            </dl>
            <dl class="links">
              <dt>公司信息</dt>
              <dd>
                <ul>
                  <li><a href="https://www.pixiv.co.jp/" target="_blank">运营公司</a></li>
                  <li><a href="https://recruit.jobcan.jp/pixiv" target="_blank" class="js-click-trackable"
                         data-click-category="recruit" data-click-action="From_Footer" data-click-label="">招聘信息</a>
                  </li>
                </ul>
              </dd>
            </dl>
            <div class="footer-pixiv-c">© pixiv</div>
          </div>
        </div>
        <div class="_footer-content-item"><span class="_icon-text">简体中文</span><i class="_pico-12 _icon-menu"></i>
          <ul class="content-popup language">
            <li class="item ja ">
              <form name="setja" method="GET" action="/"><input type="hidden" name="lang" value="ja"><input
                  type="hidden" name="return_to" value=""><input class="button" type="submit" value="日本語"></form>
            </li>
            <li class="item en ">
              <form name="seten" method="GET" action="/"><input type="hidden" name="lang" value="en"><input
                  type="hidden" name="return_to" value=""><input class="button" type="submit" value="English"></form>
            </li>
            <li class="item ko ">
              <form name="setko" method="GET" action="/"><input type="hidden" name="lang" value="ko"><input
                  type="hidden" name="return_to" value=""><input class="button" type="submit" value="한국어"></form>
            </li>
            <li class="item zh current"><i class="_pico-12 _icon-check"></i><span class="_icon-text">简体中文</span></li>
            <li class="item zh_tw ">
              <form name="setzh_tw" method="GET" action="/"><input type="hidden" name="lang" value="zh_tw"><input
                  type="hidden" name="return_to" value=""><input class="button" type="submit" value="繁體中文"></form>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PixivFooter",
  data: () => ({}),
}
</script>

<style scoped>
._footer-container {
  height: 30px;
  background-color: #464a4d;
  width: 100%;
  position: fixed;
  left: 0;
  bottom: 0;
  color: hsla(0, 0%, 100%, .5);
  z-index: 997;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

._footer-content {
  position: relative;
  height: 100%;
  margin: 0 auto;
  padding-left: 10px;
}

._footer-content-item {
  display: inline-block;
  position: relative;
  z-index: 997;
  line-height: 30px;
  height: 30px;
  padding: 0 10px;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

._footer-content-item .about {
  box-sizing: border-box;
  padding: 20px 20px 0;
  width: 890px;
  max-width: 80vw;
  color: #fff;
}

._footer-content-item .content-popup {
  display: none;
  position: absolute;
  left: 0;
  bottom: 30px;
  padding: 10px;
  background-color: #757c80;
  border-radius: 5px 5px 0 0;
  min-width: 100%;
  cursor: default;
  -webkit-user-select: auto;
  -moz-user-select: auto;
  -ms-user-select: auto;
  user-select: auto;
}

._footer-content-item .about .links {
  width: 170px;
  display: inline-block;
  vertical-align: top;
  padding: 0 10px;
  box-sizing: border-box;
  white-space: nowrap;
  overflow: hidden;
  margin-top: 15px;
}
</style>