<template>
  <div id="primary" class="content-area">
    <main id="main" class="site-main" role="main" v-for="blog in blogData" :key="blog.id">

      <blog-card :blog="blog"></blog-card>

<!--
      <?php
			while ( have_posts() ) : the_post();

				<article class="post post-list" itemscope="" itemtype="http://schema.org/BlogPosting">
      <div class="post-entry">
        <div class="feature">
          <div v-if="has_post_thumbnail()">
            <a href="<?php the_permalink();?>"><div class="overlay"><i class="iconfont icon-text"></i></div><?php the_post_thumbnail(); ?></a>
          </div>
          <div v-else>
            <a href="<?php the_permalink();?>"><div class="overlay"><i class="iconfont icon-text"></i></div><img src="<?php bloginfo('template_url'); ?>/images/random/d-<?php echo rand(1,10)?>.jpg" /></a>
          </div>
        </div>
        <h1 class="entry-title"><a href="<?php the_permalink();?>"><?php the_title();?></a></h1>
        <div class="p-time">
          <i v-if="is_sticky()" class="iconfont hotpost icon-hot"></i>

          <i class="iconfont icon-time"></i><?php echo poi_time_since(strtotime($post->post_date_gmt)); ?>
        </div>
        <?php the_excerpt(); ?>
        <footer class="entry-footer">
          <div class="post-more">
            <a href="<?php the_permalink(); ?>"><i class="iconfont icon-caidan"></i></a>
          </div>
          <div class="info-meta">
            <div class="comnum">
              <span><i class="iconfont icon-mark"></i><?php comments_popup_link('NOTHING', '1 '.__("Comment","sakura")/*条评论*/, '% '.__("Comments","sakura")/*条评论*/); ?></span>
            </div>
            <div class="views">
              <span><i class="iconfont icon-attention"></i><?php echo get_post_views(get_the_ID()).' '._n('Hit','Hits',get_post_views(get_the_ID()),'sakura')/*热度*/?></span>
            </div>
          </div>
        </footer> .entry-footer
      </div>
      <hr>
      </article> #post-##

			endwhile; // End of the loop.
			?>
-->
    </main><!-- #main -->
  </div><!-- #primary -->
</template>

<script>
import {getBlogList} from "../api/article";

export default {
  name: "list",
  components: {
    BlogCard: () => import('@/components/BlogCard'),
  },
  props: {
    category: {
      type: String
    }
  },
  data: () => ({
    pageSize: 10,
    currentPage: 1,

    blogData: [],
  }),
  methods: {
    getData() {
      let params = {};
      params.category = this.category;
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getBlogList(params).then(response => {
        console.log(response);
        if (response.state === this.$STATE.SUCCESS) {
          alert(response.message);
          this.blogData = response.data.records;
          alert('获取文章数据成功');
        }
        else {
          alert(response.message);
          alert('获取文章数据失败');
        }
      })
    },
  },
  created() {
    this.getData();
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>