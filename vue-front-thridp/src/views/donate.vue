<template>
  <div>
    <cover title="功德箱" cover-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/donate.jpg"/>
    <div id="content" class="site-content">
      <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
          <article id="post-202"
                   class="post-202 post type-post status-publish format-standard hentry category-uncategorized tag-bilibili tag-3 tag-5 tag-4">
            <div class="entry-content">
              <p style="color: #3598db; font-size: 24pt;">
                积累功德的有效方法：<br>
              </p>
              <p>
                × 放生<br>
                × 念经<br>
                × 戒色<br>
                × 戒赌<br>
                √ 和Vincent交个朋友<br><br><br><br>
              </p>
              <p style="text-align: center;">支付宝 / 微信</p>
              <div class="single-reward">
                <div class="reward-open">赏
                  <div class="reward-main">
                    <ul class="reward-row">
                      <li class="alipay-code">
                        <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/alipay.png">
                      </li>
                      <li class="wechat-code">
                        <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/wechat.png">
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <p>
                <strong>USDT(ERC20)：</strong><span style="margin-left: 25px">{{usdt}}</span>
                <a v-clipboard:copy="usdt"
                   v-clipboard:success="copy"
                   class="copy-code" href="javascript:" title="复制">
                  <i class="fa fa-clipboard" aria-hidden="true"></i>
                </a>
                <br><br>
                <strong>ETH：</strong><span style="margin-left: 100px">{{eth}}</span>
                <a v-clipboard:copy="btc"
                   v-clipboard:success="copy"
                   class="copy-code" href="javascript:" title="复制">
                  <i class="fa fa-clipboard" aria-hidden="true"></i>
                </a>
                <br><br>
                <strong>BTC：</strong><span style="margin-left: 100px">{{btc}}</span>
                <a v-clipboard:copy="btc"
                   v-clipboard:success="copy"
                   class="copy-code" href="javascript:" title="复制">
                  <i class="fa fa-clipboard" aria-hidden="true"></i>
                </a>
                <br><br>

                任意金额，交个朋友
              </p>
              <p></p>
              <p>
                <a href="https://www.vultr.com/?ref=7196564" target="_blank" rel="nofollow">
                  <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/gd.jfif"
                       alt="谢谢饲主了喵~"
                       title="谢谢饲主了喵~"
                       width="100%">
                </a>
              </p>
              <noscript>
                <img src="https://www.vultr.com/media/banner_1.png" alt="谢谢饲主了喵~" title="谢谢饲主了喵~" width="100%">
              </noscript>
            </div>
          </article>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import {message} from "../components/Message";

export default {
  name: "donate",
  components: {
    Cover: () => import('@/layouts/sora/widgets/Cover'),
  },
  data: () => ({
    btc:'3ASXf5SASK9oNMRsUMei99N76QDCs7dhYz',
    eth:'0x60a5D53CAd05c3CcC6434E9Eb01794E98Ee0a5EC',
    usdt:'0xf44a129bd5b215730cdedc65c3707ebbb46a9e4b',
  }),
  methods: {
    copy(){
      message('复制成功！<br>阿弥陀佛，施主目前的功德为18446744073709551616点！<br>已经多到溢出 unsigned __int64 了！')
    }
  }
}
</script>

<style scoped>

</style>