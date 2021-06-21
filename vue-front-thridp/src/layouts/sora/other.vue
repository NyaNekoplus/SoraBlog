<template>
  <div>
    <section id="main-container">
      <div class="scrollbar" id="bar" style="width: 19%; background: #00bbff none repeat scroll 0% 0%;"></div>
      <div id="page" class="site-wrapper">

        <sora-bar :is-index="false"/>

        <sora-other-view/>

        <sora-footer/>

        <div :class="`openNav no-select ${sidebarOn?'open sidebar-on':'sidebar-off'}`">
          <div class="iconflat no-select control-icon" @click="showSidebar">
            <div class="icon"></div>
          </div>
          <div class="site-branding">
            <h1 class="site-title">
              <brand/>
            </h1>
          </div>
        </div>
      </div>
    </section>
    <sora-setting/>
  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: 'SoraLayout',
  components: {
    SoraOtherView: () => import('./OtherView'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
    Brand: () => import('@/layouts/sora/widgets/brand'),
  },
  data: () => ({
    about: {},
  }),
  methods: {
    ...mapMutations(['setAbout','setSidebarOn']),
    showSidebar(){
      this.setSidebarOn(!this.$store.getters.sidebarOn);
    },
  },
  computed: {
    sidebarOn(){
      return this.$store.getters.sidebarOn
    },
  },
  created() {
  }
}
</script>
<style>


</style>