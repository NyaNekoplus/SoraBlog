<template>
  <router-view :key="$router.path"/>
  <!--
  <div>
    <div class="pattern-center-blank"></div>
    <div class="pattern-center-sakura">
      <div class="pattern-attachment-img"><img src="https://2heng.xin/wp-content/uploads//2017/08/pixiv54839592.png"
                                               data-src="https://2heng.xin/wp-content/uploads//2017/08/pixiv54839592.png"
                                               class="lazyload" onerror="imgError(this,3)"
                                               style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;">
      </div>
      <header class="pattern-header "><h1 class="entry-title">留言板</h1></header>
    </div>
    <div id="content" class="site-content">

    </div>
  </div>
  -->
</template>

<script>
export default {
  name: "OtherView",
  data: () => ({}),
}
</script>

<style scoped>

</style>