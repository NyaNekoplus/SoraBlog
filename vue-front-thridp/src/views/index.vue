<template>
  <div>
    <div class="notice" style="margin-top:60px">
      <i class="iconfont icon-notification"></i>
      <div class="notice-content">思绪如风，来得快去得也快，偶尔在这里停留  ~&gt;ω&lt;</div>
    </div>
    <top-list/>
    <main-list :categoryUid="'1'"></main-list>

  </div>

</template>

<script>
export default {
  name: "index",
  components: {
    TopList: () => import('@/components/TopList'),
    MainList: () => import('@/components/List'),
  },
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>