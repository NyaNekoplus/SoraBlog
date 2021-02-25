<template>
  <!-- 是否显示封面轮播图
        if(!akina_option('head_focus')){
  $filter = akina_option('focus_img_filter');
  -->
  <div v-if="!akina_option('head_focus')">
    <div class="headertop <?php echo $filter; ?>">
      <!--
      <?php get_template_part('layouts/imgbox'); ?>
      -->
    </div>
  </div>
</template>

<script>
export default {
  name: "HeadCarousel",
  data: () => ({}),
}
</script>

<style scoped>

</style>