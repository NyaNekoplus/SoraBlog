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
              <a :href="about.link">
                <ruby>
                  <span class="sakuraso" style="padding-left: 7px"><span>悠</span></span>

                  <span class="no">の</span>

                  <span class="shironeko">空</span>

                  <rp></rp><rt class="chinese-font">{{ about.name }}</rt><rp></rp>
                </ruby>
              </a>
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
import {getAboutMe} from "../../api/user";

export default {
  name: 'SoraLayout',
  components: {
    SoraOtherView: () => import('./OtherView'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
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
    getAboutMe().then(response => {
      if (response.state === this.$STATE.SUCCESS){
        this.about = response.data;
        this.setAbout(this.about);
      }
    })
  }
}
</script>
<style>


</style>