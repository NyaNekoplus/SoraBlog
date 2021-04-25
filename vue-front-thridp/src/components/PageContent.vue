<template><!--内容容器-->
  <div id="content" class="site-content">
    <div id="primary" class="content-area">
      <main id="main" class="site-main" role="main">
        <slot name="default"/>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: "Content",
  data: () => ({}),
}
</script>

<style scoped>

</style>