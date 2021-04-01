<template>
  <div>
    <div class="pattern-center-blank"></div>
    <div :class="wideScreen?'single-center pattern-center':'single-center pattern-center-sakura'">
      <div class="pattern-attachment-img">
        <img :src="coverSrc"
             data-src="https://2heng.xin/wp-content/uploads//2017/08/pixiv54839592.png"
             class="lazyload" onerror="imgError(this,3)"
             style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;">
      </div>
      <header v-if="hasSubtitle" class="pattern-header single-header"><!--pattern-header single-header-->
        <h1 class="entry-title">{{ title }}</h1>
        <p class="entry-census">
          <span>
            <a href="https://2heng.xin/author/Mashiro/">
              <img :src="avatarSrc">
            </a>
          </span>
          <span>
            <a href="https://2heng.xin/author/Mashiro/">{{ author }}</a>
          </span>
          <span class="bull">·</span>{{ releaseTime }}
          <span class="bull">·</span>{{ viewCount }} 次阅读
          <span class="bull">·</span>{{ commentCount }} 条评论
        </p>
      </header>
      <header v-else class="pattern-header">
        <h1 class="entry-title">{{ title }}</h1>
      </header>
    </div>

    <!--
    需要在article标签下
            <header class="entry-header">
              <h1 class="entry-title">使用腾讯云CDN解除哔哩哔哩番剧区域限制</h1>
              <p class="entry-census">发布于 2020-12-20&nbsp;&nbsp;12589 次阅读 </p>
              <hr>
            </header>
    -->
  </div>
</template>

<script>
export default {
  name: "Cover",
  props: {
    wideScreen: Boolean,
    coverSrc: String,
    title: String,
    //subtitle: String,
    author: String,
    avatarSrc: String,
    releaseTime: String,
    commentCount: Number,
    viewCount: Number,
  },
  data: () => ({}),
  computed: {
    hasSubtitle(){
      return !!(this.author||this.releaseTime||this.viewCount); // ！会将对象转换为bool表达式，从而让返回值用于判断
    }
  }
}
</script>

<style scoped>

</style>