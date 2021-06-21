<template>
  <div>
    <div class="pattern-center-blank"></div>
    <div :class="isWideScreen?'single-center pattern-center':'single-center pattern-center-sakura'">
      <div class="pattern-attachment-img">
        <img :src="coverSrc"
             style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;">
      </div>
      <header v-if="hasSubtitle" class="pattern-header single-header"><!--pattern-header single-header-->
        <h1 class="entry-title">{{ title }}</h1>
        <p class="entry-census">
          <span>
            <a href="https://sora.vin/About">
              <img :src="avatarSrc">
            </a>
          </span>
          <span>
            <a href="https://sora.vin/About">{{ author }}</a>
          </span>
          <span class="bull">·</span>{{ formatDate(releaseTime) }}
          <!--<span class="bull">·</span>{{ viewCount }} 次阅读-->
          <span class="bull">·</span>{{ commentCount }} 条评论
          <span class="bull">·</span>
          <span class="cover-tags">
            <i class="iconfont icon-tags"></i>
            <a v-for="tag in tagList" :key="tag.uid" rel="tag">{{tag.name}}</a>
          </span>
          <!--
          <a href="//farseerfc.me/zhs/tag/swap.html" class="btn btn-primary btn-xs withripple">
            <i class="fa fa-tag"></i> swap
            <div class="ripple-wrapper"></div>
          </a>
          -->
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
import {formatDate} from "../../../utils";

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
  methods: {
    formatDate(date){
      return formatDate(date)
    },
  },
  computed: {

    hasSubtitle(){
      return !!(this.author||this.releaseTime||this.viewCount); // ！会将对象转换为bool表达式，从而让返回值用于判断
    },
    isWideScreen(){
      return this.$store.getters.wideScreenCover
    }
  },
  created() {

  },
  mounted() {
    //this.wideScreen = this.$store.getters.wideScreenCover;
  }
}
</script>

<style scoped>
.cover-tags {
  font-size: 13px;
  color: #FFF;
  text-transform: uppercase
}

.cover-tags a {
  color: #FFF;
  margin-right: 5px
}

.cover-tags a:hover {
  color: #76B9E4
}

.cover-tags i {
  margin-right: 5px
}
</style>