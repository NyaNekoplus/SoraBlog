<template>
  <div>
    <div class="notice" style="margin-top:60px">
      <i class="iconfont icon-notification"></i>
      <div class="notice-content">思绪如风，来得快去得也快，偶尔在这里停留  ~&gt;ω&lt;</div>
    </div>
    <div class="top-feature-row"><h1 class="fes-title" style="font-family: 'Ubuntu', sans-serif;"><i
        class="fa fa-anchor" aria-hidden="true"></i> START:DASH!!</h1>
      <div class="top-feature-v2">
        <div class="the-feature square from_left_and_right"><a href="/theme-sakura/" target="_blank">
          <div class="img"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.3.2/img/other/sakura.md.png"></div>
          <div class="info"><h3>Sakura</h3>
            <p>本站 WordPress 主题</p></div>
        </a></div>
      </div>
      <div class="top-feature-v2">
        <div class="the-feature square from_left_and_right" style="margin: 0 4.5px"><a href="/2017/09/19/pixiv/"
                                                                                       target="_blank">
          <div class="img"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.6/img/other/th%20(3).jpg"></div>
          <div class="info"><h3>Pixiv</h3>
            <p>P 站的正确打开方式</p></div>
        </a></div>
      </div>
      <div class="top-feature-v2">
        <div class="the-feature square from_left_and_right"><a href="https://ak.2heng.xin/" target="_blank">
          <div class="img"><img src="https://view.moezx.cc/images/2019/06/11/74751807_angel.jpg"></div>
          <div class="info"><h3>明日方舟</h3>
            <p>游戏主界面 H5 复刻</p></div>
        </a></div>
      </div>
    </div>

    <div id="primary" class="content-area">

      <main id="main" class="site-main" role="main">

        <main-list></main-list>

      </main>
    </div>

  </div>

</template>

<script>
export default {
  name: "index",
  components: {
    MainList: () => import('@/components/list'),
  },
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>