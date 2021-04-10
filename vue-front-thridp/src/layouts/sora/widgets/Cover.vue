<template>
  <div>
    <div class="pattern-center-blank"></div>
    <div :class="isWideScreen?'single-center pattern-center':'single-center pattern-center-sakura'">
      <div class="pattern-attachment-img">
        <img :src="coverSrc"
             onerror="imgError(this,3)"
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
          <span class="bull">·</span><span v-for="tag in tagList" :key="tag.uid">{{tag.name}} | </span>
          <a href="//farseerfc.me/zhs/tag/swap.html" class="btn btn-primary btn-xs withripple">
            <i class="fa fa-tag"></i> swap
            <div class="ripple-wrapper"></div>
          </a>
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
    //wideScreen: Boolean,
    coverSrc: String,
    title: String,
    //subtitle: String,
    author: String,
    avatarSrc: String,
    releaseTime: String,
    commentCount: Number,
    viewCount: Number,
    tagList: Array,
  },
  data: () => ({
    //wideScreen: true,
    //wideScreenClass: 'single-center pattern-center',
  }),
  computed: {
    hasSubtitle(){
      return !!(this.author||this.releaseTime||this.viewCount); // ！会将对象转换为bool表达式，从而让返回值用于判断
    },
    isWideScreen(){
      return this.$store.getters.wideScreenCover
    }
  },
  mounted() {
    //this.wideScreen = this.$store.getters.wideScreenCover;
  }
}
</script>

<style scoped>
a:focus {
  outline: thin dotted;
  outline: 5px auto -webkit-focus-ring-color;
  outline-offset: -2px;
}
.btn:focus {
  z-index: 2;
}
.btn-primary {
  color: #fff;
  background-color: #5c497f;
  border-color: #50406f;
}
.btn {
  position: relative;
  padding: 8px 30px;
  border: 0;
  border-top-color: currentcolor;
  border-right-color: currentcolor;
  border-bottom-color: currentcolor;
  border-left-color: currentcolor;
  margin: 10px 1px;
  cursor: pointer;
  border-radius: 2px;
  color: rgba(255,255,255,0.84);
  transition: box-shadow .28s cubic-bezier(.4, 0, .2, 1);
  outline: none !important;
}
.btn {
  display: inline-block;
  margin-bottom: 0;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;
  touch-action: manipulation;
  cursor: pointer;
  background-image: none;
  border: 1px solid transparent;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  border-radius: 4px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.btn-xs > .btn {
  padding: 1px 5px;
  font-size: 12px;
  line-height: 1.5;
  border-radius: 3px;
}
</style>