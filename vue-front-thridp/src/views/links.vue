<template>
  <div>
    <cover
        title="友情链接"
        cover-src="https://fastly.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.3/img/cover/link.jpg"
    />
    <page-content>
      <article class="page type-page status-publish has-post-thumbnail hentry">
        <div class="entry-content">
          <div v-html=""></div>

          <div class="links">
            <ul class="link-items fontSmooth">
              <!------------------------------资源 START---------------------------------->
              <li class="link-item">
                <a class="link-item-inner effect-apollo" href="https://sora.vin/" title="Sample" target="_blank" rel="friend noopener noreferrer">
                  <!--<img class="lazyload" src="https://fastly.jsdelivr.net/gh/honjun/cdn@1.6/img/loader/orange.progress-bar-stripe-loader.svg" data-src="/wp-content/uploads/2020/05/资源.png">-->
                  <img src="https://fastly.jsdelivr.net/gh/Nyanekoplus/js@master/data/1618926500372.jpg">
                  <span class="sitename">Sample</span>
                  <div class="linkdes">In solitude, where we are least alone.</div>
                </a>
              </li>
              <!------------------------------END------------------------------------->
            </ul>
          </div>
        </div>
      </article>
    </page-content>
    <comment-block :blog-id="0" :comment-source="'LINK'" :enable-comment="true"></comment-block>
  </div>
</template>

<script>
export default {
  name: "links",
  components: {
    PageContent: () => import('@/components/PageContent'),
    Cover: () => import('@/layouts/sora/widgets/Cover'),
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({}),
}
</script>

<style scoped>
.links ul li {
  width: 100%;
  float: left;
  border: 1px solid #ececec;
  padding: 10px 30px;
  margin: 4px 4px;
  position: relative;
  overflow: hidden;
  -webkit-transition: all .3s;
  transition: all .3s;
  border-radius: 10px;
}
</style>
