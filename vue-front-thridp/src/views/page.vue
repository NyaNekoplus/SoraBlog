<template>
 <div>
   <div>User {{ $route.params.title }}</div>
   <div>Article ID {{ this.$store.getters.blogId }}</div>
   <div>To ID {{ $store.getters.toId }}</div>
   <comment-block></comment-block>
 </div>
</template>

<script>
export default {
  name: "page",
  components:{
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({

  }),

}
</script>

<style scoped>

</style>