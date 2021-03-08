<template>
 <div>
   <div>User {{ $route.params.title }}</div>
   <comment-block></comment-block>
 </div>
</template>

<script>
export default {
  name: "page",
  components:{
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({}),
}
</script>

<style scoped>

</style>