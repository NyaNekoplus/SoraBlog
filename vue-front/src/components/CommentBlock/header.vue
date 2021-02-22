<template>
  <h3 class="comment-header alert alert-info">
    <i class="fa fa-comment"></i>
      {{count}}条评论
  </h3>
</template>

<script>
export default {
  name: "header",
  props: ['count']
}
</script>

<style scoped>
.comment-header{
  text-align: center;
  font-size: 14px;
}
</style>