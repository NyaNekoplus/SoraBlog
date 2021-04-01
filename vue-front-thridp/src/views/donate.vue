<template>
  <div>
    <cover title="功德箱" cover-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/donate.jpg" :wide-screen="this.$store.getters.wideScreenCover"/>
    <div id="content" class="site-content">
      <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
          <article id="post-202" class="post-202 post type-post status-publish format-standard hentry category-uncategorized tag-bilibili tag-3 tag-5 tag-4">
            积累功德的有效方法：
            × 放生
            × 念经
            × 戒色
            × 戒赌
            √ 和Vincent交个朋友

            USDT | ETH | BTC
            支付宝 | 微信
            任意金额，交个朋友

          </article>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "donate",
  components: {
    Cover: () => import('@/layouts/sora/widgets/Cover'),
  },
  data: () => ({}),
}
</script>

<style scoped>

</style>