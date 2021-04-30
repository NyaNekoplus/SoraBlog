<template>
  <div>
    <cover
        title="友情链接"
        cover-src="https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.3/img/cover/link.jpg"
    />
    <page-content>
      <article class="page type-page status-publish has-post-thumbnail hentry">
        <div class="entry-content">
          <div v-html=""></div>
        </div>
      </article>
    </page-content>
    <comment-block :comment-source="'LINK'" :enable-comment="true"></comment-block>
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

</style>