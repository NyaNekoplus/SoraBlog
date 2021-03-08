<template>
  <!--
  //function custom_short_excerpt($excerpt){
  //	return substr($excerpt, 0, 120);
  //}
  //add_filter('the_excerpt', 'custom_short_excerpt');
  $i=0; while ( have_posts() ) : the_post(); $i++;
  if( $i == 1 ){
  $class = ' post-list-show';
  }
  if(has_post_thumbnail()){
  $large_image_url = wp_get_attachment_image_src( get_post_thumbnail_id($post->ID), 'large');
  $post_img = $large_image_url[0];
  }else{
  $post_img = DEFAULT_FEATURE_IMAGE();
  }
  $the_cat = get_the_category();
  // 摘要字数限制

  //add_filter( 'excerpt_length', 'custom_excerpt_length', 120 );
  ?>
  -->
  <article class="post post-list-thumb post-list-show" itemscope="" itemtype="http://schema.org/BlogPosting">
    <div class="post-thumb">
      <a href="<?php the_permalink(); ?>"><img class="lazyload" src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.0.1/img/svg/loader/orange.progress-bar-stripe-loader.svg" data-src="<?php echo $post_img; ?>"></a>
    </div><!-- thumbnail-->
    <div class="post-content-wrap">
      <div class="post-content">
        <div class="post-date">
          <i class="iconfont icon-time"></i>发布于 {{ releaseTime }}
          <div v-if="isTop"><!-- is_sticky():是否置顶 -->
            &nbsp;<i class="iconfont hotpost icon-hot"></i>
          </div>

        </div>
        <a href="<?php the_permalink(); ?>" class="post-title"><h3>{{ title }}</h3></a>
        <div class="post-meta">
          <span><i class="iconfont icon-attention"></i>{{ viewCount }}</span>
          <span class="comments-number"><i class="iconfont icon-mark"></i>{{ commentCount }}条评论</span>
          <span><i class="iconfont icon-file"></i><a href="<?php echo esc_url(get_category_link($the_cat[0]->cat_ID)); ?>">{{ category }}</a>
					</span>
        </div>
        <div class="float-content">
          <!-- 提取摘要
              the_excerpt()
              输出当前文章的摘要，并会附上”[…]”，这不是”更多”的链接。如果你没提供一篇文章明确的摘要（后台文章编辑器的可选摘要区域），它将自动摘录文章内容中前55个字作为摘要。而HTML标签和图片都会从摘要内容中去除
          -->
          <p>{{ summary }}</p>
          <div class="post-bottom">
            <a :href="link" class="button-normal"><i class="iconfont icon-caidan"></i>...</a>
          </div>
        </div>
      </div>
    </div>
  </article>

</template>

<script>
//import AppCard from "@/components/app/Card";
export default {
  name: "BlogCard",
  props: {

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
  }),
}
</script>

<style>
.post-list-show {
  animation: post-list-show .5s;
  -webkit-animation: post-list-show .5s;
  opacity: 1;
}
</style>