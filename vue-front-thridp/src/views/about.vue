<template>
  <div>
    <cover
        title="关于"
        cover-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/about.png"
    />
    <page-content>
      aaa
    </page-content>
    <comment-block :comment-source="'ABOUT'"></comment-block>
  </div>
</template>

<script>
export default {
  name: "about",
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