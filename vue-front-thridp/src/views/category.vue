<template>
  <div>
    <cover :title="$route.params.title" :cover-src="$route.params.title==='Tech'?'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.3/img/cover/nv.gif':'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.4/img/cover/life.jiff'"/>
    <page-content>
      <article v-for="blog in blogData" :key="blog.uid" class="post post-list" itemscope="" itemtype="http://schema.org/BlogPosting">
        <div class="post-entry">
          <div class="feature">
            <a :href="'/blog/'+blog.link">
              <div class="overlay">
                <i class="iconfont icon-text"></i>
              </div>
              <img width="150" height="150" :src="blog.coverUrl" class="attachment-post-thumbnail size-post-thumbnail wp-post-image" alt="">
            </a>
          </div>
          <h1 class="entry-title"><a :href="'/blog/'+blog.link">{{ blog.title }}</a></h1>
          <div class="p-time">
            <i class="iconfont icon-time"></i>发布于 {{ formatDate(blog.createTime) }}
          </div>
          <p>{{ blog.summary }}		</p>
          <footer class="entry-footer">
          <div class="post-more">
            <a :href="'/blog/'+blog.link"><i class="iconfont icon-caidan"></i></a>
          </div>
          <div class="info-meta">
            <div class="comnum">
              <span><i class="iconfont icon-mark"></i><a :href="'/blog/'+blog.link+'/#comment'">{{ blog.commentCount }} 条评论</a></span>
            </div>
            <div class="views">
              <span><i class="iconfont icon-attention"></i>298 热度</span>
            </div>
          </div>
        </footer><!-- .entry-footer -->
        </div>
        <hr>
      </article>
      <div class="clearer"></div>
    </page-content>
  </div>
</template>

<script>

import {getBlogList} from "../api/article";
import {getCategoryByName} from "../api/category";
import {formatDate} from "../utils";

export default {
  name: "category",
  components: {
    PageContent: () => import('@/components/PageContent'),
    Cover: () => import('@/layouts/sora/widgets/Cover'),
  },
  data: () => ({
    pageSize: 10,
    currentPage: 1,
    totalPage: 0,

    categoryUid: 0,
    blogData: [],
  }),
  methods: {
    formatDate(date){
      return formatDate(date)
    },
    getData() {
      let params = {};
      params.categoryUid = this.categoryUid;
      params.isDraft = false;
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getBlogList(params).then(response => {
        console.log(response);
        if (response.state === this.$STATE.SUCCESS) {
          let data = response.data;
          this.blogData = data.records;
          this.pageSize = data.size;
          this.totalPage = data.pages;
          this.currentPage = data.current;
        }
      })
    },
  },
  created() {
    console.log('category create: ' + this.$route.params.title);
    getCategoryByName(this.$route.params.title).then(cres => {
      if (cres.state === this.$STATE.SUCCESS){
        this.categoryUid = cres.data.uid;
        this.getData()
      }
    })
  }
}
</script>

<style scoped>

</style>