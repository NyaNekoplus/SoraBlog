<template>
  <section id="main-container">
    <div class="scrollbar" id="bar" style="width: 19%; background: #00bbff none repeat scroll 0% 0%;"></div>
    <div id="page" class="site-wrapper">

      <sora-bar :is-index="false"/>

      <sora-other-view/>

      <sora-footer/>

      <sora-setting/>

    </div>
  </section>
</template>

<script>
export default {
  name: 'SoraLayout',
  components: {
    SoraOtherView: () => import('./OtherView'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
  },
  data: () => ({

  }),
  methods: {

  },
  mounted() {

  }

}
</script>
<style>


</style>