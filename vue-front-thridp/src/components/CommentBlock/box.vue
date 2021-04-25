<template>
  <div id="respond" class="comment-respond">
    <h3 id="reply-title" class="comment-reply-title">
      <small><a rel="nofollow" id="cancel-comment-reply-link" style="display:none;" @click="cancelReply">Cancel Reply</a></small><!--href="/theme-sakura/#respond" -->
    </h3>
    <div id="commentform" class="comment-form" novalidate="">
      <p>
        <i class="iconfont icon-markdown"></i> Markdown Supported while
        <i class="fa fa-code" aria-hidden="true"></i>Forbidden
      </p>
      <div class="comment-textarea">
        <textarea placeholder="你是我一生只会遇见一次的惊喜 ..." name="comment" class="commentbody" id="comment" rows="5" tabindex="4" v-model="content"></textarea>
        <label class="input-label active">你是我一生只会遇见一次的惊喜 ...</label>
      </div>
      <div id="upload-img-show"></div>
      <p id="emotion-toggle" class="no-select">
        <span class="emotion-toggle-off">戳我试试 OωO</span>
        <span class="emotion-toggle-on">嘿嘿嘿 ヾ(≧∇≦*)ゝ</span>
      </p>
      <div class="emotion-box no-select">
        <table class="motion-switcher-table">
          <tbody>
          <tr>
            <th onclick="motionSwitch('.bili')" class="bili-bar on-hover">bilibili~</th>
            <th onclick="motionSwitch('.menhera')" class="menhera-bar">(✪ω✪)</th>
            <th onclick="motionSwitch('.tieba')" class="tieba-bar">Tieba</th>
          </tr>
          </tbody>
        </table>
        <div class="bili-container motion-container">
            <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('baiyan',type = 'Math')"
                  style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_baiyan.png);">
              <div class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/baiyan.png);
                animation-duration: 1800ms;
                animation-timing-function: steps(45);
                transform: translateY(-1408px);
                height: 1440px;"></div>
            </span>
          <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('fadai',type = 'Math')"
                style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_fadai.png);">
              <div class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/fadai.png);
                 animation-duration: 1080ms;
                 animation-timing-function: steps(27);
                 transform: translateY(-832px);
                 height: 864px;"></div>
            </span>
          <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('koubi',type = 'Math')"
                style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_koubi.png);">
              <div class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/koubi.png);
 animation-duration: 1200ms;
 animation-timing-function: steps(30);
 transform: translateY(-928px);
 height: 960px;"></div>
            </span>
          <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('qinqin',type = 'Math')"
                style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_qinqin.png);">
              <div class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/qinqin.png);
 animation-duration: 280ms;
 animation-timing-function: steps(7);
 transform: translateY(-192px);
 height: 224px;"></div>
            </span>
          <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('weiqu',type = 'Math')"
                style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_weiqu.png);">
              <div class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/weiqu.png);
 animation-duration: 800ms;
 animation-timing-function: steps(20);
 transform: translateY(-608px);
 height: 640px;"></div>
            </span>
          <span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('bishi',type = 'Math')"
                style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_bishi.png);"><div
              class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/bishi.png);animation-duration: 360ms;animation-timing-function: steps(9);transform: translateY(-256px);height: 288px;">
          </div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('fanu',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_fanu.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/fanu.png);
 animation-duration: 1320ms;
 animation-timing-function: steps(33);
 transform: translateY(-1024px);
 height: 1056px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('kun',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_kun.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/kun.png);
 animation-duration: 1760ms;
 animation-timing-function: steps(44);
 transform: translateY(-1376px);
 height: 1408px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('se',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_se.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/se.png);
 animation-duration: 400ms;
 animation-timing-function: steps(10);
 transform: translateY(-288px);
 height: 320px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('weixiao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_weixiao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/weixiao.png);
 animation-duration: 800ms;
 animation-timing-function: steps(20);
 transform: translateY(-608px);
 height: 640px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('bizui',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_bizui.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/bizui.png);
 animation-duration: 1240ms;
 animation-timing-function: steps(31);
 transform: translateY(-960px);
 height: 992px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('ganga',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_ganga.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/ganga.png);
 animation-duration: 1520ms;
 animation-timing-function: steps(38);
 transform: translateY(-1184px);
 height: 1216px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('lengmo',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_lengmo.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/lengmo.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent"
                      onclick="grin('shengbing',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_shengbing.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/shengbing.png);
 animation-duration: 1400ms;
 animation-timing-function: steps(35);
 transform: translateY(-1088px);
 height: 1120px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('wunai',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_wunai.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/wunai.png);
 animation-duration: 920ms;
 animation-timing-function: steps(23);
 transform: translateY(-704px);
 height: 736px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('chan',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_chan.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/chan.png);
 animation-duration: 1600ms;
 animation-timing-function: steps(40);
 transform: translateY(-1248px);
 height: 1280px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('guilian',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_guilian.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/guilian.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('liubixue',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_liubixue.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/liubixue.png);
 animation-duration: 1400ms;
 animation-timing-function: steps(35);
 transform: translateY(-1088px);
 height: 1120px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('shengqi',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_shengqi.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/shengqi.png);
 animation-duration: 440ms;
 animation-timing-function: steps(11);
 transform: translateY(-320px);
 height: 352px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('xiaoku',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_xiaoku.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/xiaoku.png);
 animation-duration: 600ms;
 animation-timing-function: steps(15);
 transform: translateY(-448px);
 height: 480px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('daku',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_daku.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/daku.png);
 animation-duration: 320ms;
 animation-timing-function: steps(8);
 transform: translateY(-224px);
 height: 256px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('guzhang',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_guzhang.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/guzhang.png);
 animation-duration: 680ms;
 animation-timing-function: steps(17);
 transform: translateY(-512px);
 height: 544px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('liuhan',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_liuhan.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/liuhan.png);
 animation-duration: 1080ms;
 animation-timing-function: steps(27);
 transform: translateY(-832px);
 height: 864px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('shuizhao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_shuizhao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/shuizhao.png);
 animation-duration: 960ms;
 animation-timing-function: steps(24);
 transform: translateY(-736px);
 height: 768px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent"
                      onclick="grin('xieyanxiao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_xieyanxiao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/xieyanxiao.png);
 animation-duration: 320ms;
 animation-timing-function: steps(8);
 transform: translateY(-224px);
 height: 256px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('dalao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_dalao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/dalao.png);
 animation-duration: 1320ms;
 animation-timing-function: steps(33);
 transform: translateY(-1024px);
 height: 1056px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('haixiu',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_haixiu.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/haixiu.png);
 animation-duration: 1240ms;
 animation-timing-function: steps(31);
 transform: translateY(-960px);
 height: 992px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('liulei',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_liulei.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/liulei.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('sikao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_sikao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/sikao.png);
 animation-duration: 1440ms;
 animation-timing-function: steps(36);
 transform: translateY(-1120px);
 height: 1152px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('yiwen',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_yiwen.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/yiwen.png);
 animation-duration: 840ms;
 animation-timing-function: steps(21);
 transform: translateY(-640px);
 height: 672px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('dalian',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_dalian.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/dalian.png);
 animation-duration: 1480ms;
 animation-timing-function: steps(37);
 transform: translateY(-1152px);
 height: 1184px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent"
                      onclick="grin('heirenwenhao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_heirenwenhao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/heirenwenhao.png);
 animation-duration: 1040ms;
 animation-timing-function: steps(26);
 transform: translateY(-800px);
 height: 832px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('miantian',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_miantian.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/miantian.png);
 animation-duration: 1120ms;
 animation-timing-function: steps(28);
 transform: translateY(-864px);
 height: 896px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('tiaokan',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_tiaokan.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/tiaokan.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('yun',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_yun.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/yun.png);
 animation-duration: 480ms;
 animation-timing-function: steps(12);
 transform: translateY(-352px);
 height: 384px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('dianzan',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_dianzan.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/dianzan.png);
 animation-duration: 800ms;
 animation-timing-function: steps(20);
 transform: translateY(-608px);
 height: 640px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('huaixiao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_huaixiao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/huaixiao.png);
 animation-duration: 1240ms;
 animation-timing-function: steps(31);
 transform: translateY(-960px);
 height: 992px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent"
                      onclick="grin('mudengkoudai',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_mudengkoudai.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/mudengkoudai.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('tiaopi',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_tiaopi.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/tiaopi.png);
 animation-duration: 2000ms;
 animation-timing-function: steps(50);
 transform: translateY(-1568px);
 height: 1600px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('zaijian',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_zaijian.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/zaijian.png);
 animation-duration: 960ms;
 animation-timing-function: steps(24);
 transform: translateY(-736px);
 height: 768px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('doge',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_doge.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/doge.png);
 animation-duration: 800ms;
 animation-timing-function: steps(20);
 transform: translateY(-608px);
 height: 640px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('jingxia',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_jingxia.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/jingxia.png);
 animation-duration: 1280ms;
 animation-timing-function: steps(32);
 transform: translateY(-992px);
 height: 1024px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('nanguo',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_nanguo.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/nanguo.png);
 animation-duration: 1120ms;
 animation-timing-function: steps(28);
 transform: translateY(-864px);
 height: 896px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('touxiao',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_touxiao.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/touxiao.png);
 animation-duration: 240ms;
 animation-timing-function: steps(6);
 transform: translateY(-160px);
 height: 192px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('zhoumei',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_zhoumei.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/zhoumei.png);
 animation-duration: 40ms;
 animation-timing-function: steps(1);
 transform: translateY(-0px);
 height: 32px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('facai',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_facai.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/facai.png);
 animation-duration: 1200ms;
 animation-timing-function: steps(30);
 transform: translateY(-928px);
 height: 960px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('keai',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_keai.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/keai.png);
 animation-duration: 680ms;
 animation-timing-function: steps(17);
 transform: translateY(-512px);
 height: 544px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('outu',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_outu.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/outu.png);
 animation-duration: 1680ms;
 animation-timing-function: steps(42);
 transform: translateY(-1312px);
 height: 1344px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent" onclick="grin('tuxue',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_tuxue.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/tuxue.png);
 animation-duration: 320ms;
 animation-timing-function: steps(8);
 transform: translateY(-224px);
 height: 256px;
 "></div></span><span class="emotion-secter emotion-item emotion-select-parent"
                      onclick="grin('zhuakuang',type = 'Math')"
                      style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/hd/ic_emoji_zhuakuang.png);"><div
            class="img emotion-select-child" style="background-image: url(https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/zhuakuang.png);
 animation-duration: 760ms;
 animation-timing-function: steps(19);
 transform: translateY(-576px);
 height: 608px;
 "></div></span></div>
        <div class="menhera-container motion-container" style="display:none;">
          <a class="emoji-item">(⌒▽⌒)</a>
          <a class="emoji-item">（￣▽￣）</a> <a class="emoji-item">(=・ω・=)</a> <a class="emoji-item">(｀・ω・´)</a> <a
            class="emoji-item">(〜￣△￣)〜</a> <a class="emoji-item">(･∀･)</a> <a class="emoji-item">(°∀°)ﾉ</a> <a
            class="emoji-item">(￣3￣)</a> <a class="emoji-item">╮(￣▽￣)╭</a> <a class="emoji-item">(´_ゝ｀)</a> <a
            class="emoji-item">←_←</a> <a class="emoji-item">→_→</a> <a class="emoji-item">(&lt;_&lt;)</a> <a
            class="emoji-item">(&gt;_&gt;)</a> <a class="emoji-item">(;¬_¬)</a> <a class="emoji-item">("▔□▔)/</a> <a
            class="emoji-item">(ﾟДﾟ≡ﾟдﾟ)!?</a> <a class="emoji-item">Σ(ﾟдﾟ;)</a> <a class="emoji-item">Σ(￣□￣||)</a> <a
            class="emoji-item">(’；ω；‘)</a> <a class="emoji-item">（/TДT)/</a> <a class="emoji-item">(^・ω・^ )</a> <a
            class="emoji-item">(｡･ω･｡)</a> <a class="emoji-item">(●￣(ｴ)￣●)</a> <a class="emoji-item">ε=ε=(ノ≧∇≦)ノ</a>
          <a class="emoji-item">(’･_･‘)</a> <a class="emoji-item">(-_-#)</a> <a class="emoji-item">（￣へ￣）</a> <a
              class="emoji-item">(￣ε(#￣)Σ</a> <a class="emoji-item">ヽ(‘Д’)ﾉ</a> <a class="emoji-item">（#-_-)┯━┯</a> <a
              class="emoji-item">(╯°口°)╯(┴—┴</a> <a class="emoji-item">←◡←</a> <a class="emoji-item">( ♥д♥)</a> <a
              class="emoji-item">_(:3」∠)_</a> <a class="emoji-item">Σ&gt;―(〃°ω°〃)♡→</a> <a class="emoji-item">⁄(⁄
            ⁄•⁄ω⁄•⁄ ⁄)⁄</a> <a class="emoji-item">(╬ﾟдﾟ)▄︻┻┳═一</a> <a class="emoji-item">･*･:≡( ε:)</a> <a
              class="emoji-item">(笑)</a> <a class="emoji-item">(汗)</a> <a class="emoji-item">(泣)</a> <a
              class="emoji-item">(苦笑)</a></div>
        <div class="tieba-container motion-container" style="display:none;">
          <span title="tongue" onclick="grin('tongue')">
            <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_tongue.gif">
          </span>
          <span
            title="theblackline" onclick="grin('theblackline')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_theblackline.gif"></span><span
            title="tear" onclick="grin('tear')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_tear.gif"></span><span
            title="surprised" onclick="grin('surprised')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_surprised.gif"></span><span
            title="surprised2" onclick="grin('surprised2')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_surprised2.gif"></span><span
            title="spray" onclick="grin('spray')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_spray.gif"></span><span
            title="spit" onclick="grin('spit')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_spit.gif"></span><span
            title="smilingeyes" onclick="grin('smilingeyes')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_smilingeyes.gif"></span><span
            title="shui" onclick="grin('shui')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_shui.gif"></span><span
            title="shame" onclick="grin('shame')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_shame.gif"></span><span
            title="se" onclick="grin('se')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_se.gif"></span><span
            title="rmb" onclick="grin('rmb')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_rmb.gif"></span><span
            title="reluctantly" onclick="grin('reluctantly')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_reluctantly.gif"></span><span
            title="rbq" onclick="grin('rbq')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_rbq.gif"></span><span
            title="niconiconit" onclick="grin('niconiconit')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_niconiconit.gif"></span><span
            title="niconiconi" onclick="grin('niconiconi')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_niconiconi.gif"></span><span
            title="niconiconi-t" onclick="grin('niconiconi-t')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_niconiconi_t.gif"></span><span
            title="naive" onclick="grin('naive')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_naive.gif"></span><span
            title="ku" onclick="grin('ku')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_ku.gif"></span><span
            title="huaji" onclick="grin('huaji')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_huaji.gif"></span><span
            title="hu" onclick="grin('hu')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_hu.gif"></span><span
            title="han" onclick="grin('han')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_han.gif"></span><span
            title="haha" onclick="grin('haha')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_haha.gif"></span><span
            title="good" onclick="grin('good')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_good.gif"></span><span
            title="doubt" onclick="grin('doubt')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_doubt.gif"></span><span
            title="britan" onclick="grin('britan')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_britan.gif"></span><span
            title="bbd" onclick="grin('bbd')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_bbd.gif"></span><span
            title="awesome" onclick="grin('awesome')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_awesome.gif"></span><span
            title="anger" onclick="grin('anger')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_anger.gif"></span><span
            title="aa" onclick="grin('aa')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_aa.gif"></span><span
            title="Happy" onclick="grin('Happy')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_Happy.gif"></span><span
            title="Grievance" onclick="grin('Grievance')"><img
            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/smilies/icon_Grievance.gif"></span>
        </div>
      </div>
      <div class="cmt-info-container">
        <div class="comment-user-avatar">
          <img :src="hasUserInfo?currentUser.avatarUrl:'https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png'">
          <div class="socila-check qq-check"><i class="fa fa-qq" aria-hidden="true"></i></div>
          <div class="socila-check gravatar-check"><i class="fa fa-google" aria-hidden="true"></i></div>
        </div>
        <div class="popup cmt-popup cmt-author" onclick="cmt_showPopup(this)">
          <span class="popuptext" id="thePopup" style="margin-left: -115px;width: 230px;">输入QQ号将自动拉取昵称和头像</span>
          <input type="text" placeholder="昵称或QQ号 (必须 Name* )" name="author" id="author" value="" size="22" autocomplete="off" tabindex="1" aria-required="true">
        </div>
        <div class="popup cmt-popup" onclick="cmt_showPopup(this)">
          <span class="popuptext" id="thePopup" style="margin-left: -65px;width: 130px;">你将收到回复通知</span>
          <input type="text" placeholder="邮箱 (必须 Email* )" name="email" id="email" value="" size="22" tabindex="1" autocomplete="off" aria-required="true">
        </div>
        <div class="popup cmt-popup" onclick="cmt_showPopup(this)">
          <span class="popuptext" id="thePopup" style="margin-left: -55px;width: 110px;">禁止小广告😀</span>
          <input type="text" placeholder="网站 (选填 Site)" name="url" id="url" value="" size="22" autocomplete="off" tabindex="1">
        </div>
      </div>
      <label class="siren-checkbox-label active">
        <input class="siren-checkbox-radio" type="checkbox" name="no-robot">
        <span class="siren-no-robot-checkbox siren-checkbox-radioInput"></span>
        滴，学生卡 | I'm not a robot
      </label>
      <input type="text" placeholder="QQ" name="new_field_qq" id="qq" value="" style="display:none" autocomplete="off">
      <div class="form-submit">
        <input id="submit" class="submit" @click="handleSubmit" value="发送评论" type="submit"><!-- @click="submitComment"-->
        <div class="insert-image-tips popup"
             @mouseenter="$event.currentTarget.children[1].className='insert-img-popuptext show'"
             @mouseleave="$event.currentTarget.children[1].className='insert-img-popuptext'">
          <i class="fa fa-picture-o" aria-hidden="true"></i>
          <span class="insert-img-popuptext" id="uploadTipPopup">上传图片</span>
        </div>
        <!--
        <input id="upload-img-file" type="file" accept="image/*" multiple="multiple" class="insert-image-button">
        -->
        <input type="hidden" name="comment_post_ID" value="3137" id="comment_post_ID">
        <input type="hidden" name="comment_parent" id="comment_parent" value="0">
      </div>
      <p style="display: none;">
        <input type="hidden" id="akismet_comment_nonce" name="akismet_comment_nonce" value="7f8fe6e5d2">
      </p>
      <p style="display: none;"></p>
      <input type="hidden" id="ak_js" name="ak_js" value="1614604055274">
    </div>
  </div>
</template>

<script>
import { parseTime } from '@/utils/index'
import {mapMutations} from "vuex";
import {message} from "../Message";

export default {
  //inject:['reload'],
  name: "box",
  data: () => ({
    content: '',
    currentUser: null,
  }),
  methods:{
    ...mapMutations(['removeToInfo','setCommentList']),
    handleSubmit(){
      if (this.content === ''){
        message("不能为空");
        return;
      }
      let userInfo = this.$store.getters.userInfo;
      if (userInfo === null || userInfo === undefined){
        message("请先登陆");
        return;
      }
      let blogUid = this.blogUid//this.$store.getters.blog.uid;
      console.log('blogUid: '+blogUid);
      let toInfo = this.$store.getters.toInfo;

      let param = {};
      //param.blogUid = blogUid; // 在comment-index中
      //param.rootUid = blogId;
      param.userUid = userInfo.uid;
      param.content = this.content;

      if(toInfo !== null){ // is reply
        console.log('toInfoUid: '+toInfo.toUid);
        param.toUid = toInfo.toUid;
        param.toUserUid = toInfo.toUserUid;
        param.targetType = 1; // 回复评论
      }else {
        param.toUid = null;
        param.toUserUid = null;
        param.targetType = 0;
      }
      param.createTime = parseTime("YYYY-mm-dd HH:MM:SS",new Date());
      console.log('在box中: ' + param);

      this.content = '';
      this.cancelReply();
      this.$emit('submit-comment', param);
    },
    cancelReply(){
      let origin_pos = document.getElementById('comments');
      //let box = e.currentTarget.parentNode.parentNode.parentNode;
      let box = document.getElementById('respond');
      //let refbox = this.$refs.commentbox;
      //console.log('refbox: '+refbox);
      console.log('child: '+box.children);
      box.children[0].children[0].children[0].style = 'display: none;';
      this.removeToInfo();
      origin_pos.appendChild(box);
    },

  },
  created() {

  },
  mounted() {
    this.currentUser = this.$store.getters.userInfo;
  },
  computed: {
    hasUserInfo(){
      console.log('urltest：'+!!(this.currentUser&&this.currentUser.avatarUrl))
      return !!(this.currentUser&&this.currentUser.avatarUrl);
    }
  }
}
</script>

<style scoped>
.comment-respond button {
  width: calc(98% - 46px);
  margin: 0;
  padding: 15px 25px;
  text-transform: none;
  color:
      #535a63;
  background: 0 0;
  border-right: 0;
  -webkit-transition: all .1s ease-out;
  -moz-transition: all .1s ease-out;
  transition: all .1s ease-out;
  box-shadow: none;
  border: 1px solid
  #ccc;
  text-shadow: none;
}
</style>