<template>
  <article class="post post-list-thumb post-list-show" itemscope="" itemtype="http://schema.org/BlogPosting">
    <div class="post-thumb">
      <a href=""><img class="lazyload" src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.0.1/img/svg/loader/orange.progress-bar-stripe-loader.svg" :data-src="blog.coverUrl"></a>
    </div><!-- thumbnail-->
    <div class="post-content-wrap">
      <div class="post-content">
        <div class="post-date">
          <i class="iconfont icon-time"></i>发布于 {{ blog.createTime }}
          <div v-if="blog.isTop"><!-- is_sticky():是否置顶 -->
            &nbsp;<i class="iconfont hotpost icon-hot"></i>
          </div>

        </div>
        <a @click="intoDetail(blog)" class="post-title"><h3>{{ blog.title }}</h3></a>
        <div class="post-meta">
          <span><i class="iconfont icon-attention"></i>{{ blog.viewCount }}</span>
          <span class="comments-number"><i class="iconfont icon-mark"></i>{{ blog.commentCount }}条评论</span>
          <span><i class="iconfont icon-file"></i><a href="<?php echo esc_url(get_category_link($the_cat[0]->cat_ID)); ?>">{{ blog.category }}</a>
					</span>
        </div>
        <div class="float-content">
          <!-- 提取摘要
              the_excerpt()
              输出当前文章的摘要，并会附上”[…]”，这不是”更多”的链接。如果你没提供一篇文章明确的摘要（后台文章编辑器的可选摘要区域），它将自动摘录文章内容中前55个字作为摘要。而HTML标签和图片都会从摘要内容中去除
          -->
          <p>{{ blog.summary }}</p>
          <div class="post-bottom">
            <a @click="intoDetail(blog)" class="button-normal"><i class="iconfont icon-caidan"></i>...</a>
          </div>
        </div>
      </div>
    </div>
  </article>

</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "BlogCard",
  props: {
    blog: {
      type: Object
    }
  },
  //components: {AppCard},
  data: () => ({
    isTop: true,
    title: '',
    link: 'test',
    viewCount: 99,
    commentCount: 23,
    category: 'Tech',
    summary: '当我们需要用 GraphQL 查询多层套嵌的数据，比如像 WordPress 这样套嵌的评论信息时，通常的写法是： {posts(first: 100)',
    releaseTime: '2020-10-18',

    isLoad: false
  }),
  methods: {
    ...mapMutations(['setBlog']),
    intoDetail(blog){
      if (blog.link[0] !== '/'){
        blog.link = '/' + blog.link;
      }
      this.setBlog(blog);
      let routeData = this.$router.resolve({
        path: '/blog' + blog.link,
      });
      this.$router.push(routeData.href);
      //window.open(routeData.href, '_blank');
    },
  },
  mounted() {
    //addEventListener('scroll',this.loadCover);
  }
}
</script>

<style>

</style>