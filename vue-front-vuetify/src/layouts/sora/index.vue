<template>
  <v-app id="inspire">

    <sora-bar/>

    <v-progress-linear value="60"
                       height="10"
                       striped
                       color="deep-orange"></v-progress-linear>
    <!-- v-main 放在view中，其内容拆分到Views文件夹-->
    <sora-view/>



    <sora-footer/>

    <!--
    <sora-setting/>
    -->

  </v-app>
</template>

<script>
export default {
  name: 'SoraLayout',
  components: {
    SoraView: () => import('./View'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
  },
  data: () => ({
    pageYOffset: 0

  }),
  computed: {
    getYoffset(){
      return window.pageYOffset;
    }
  },
}
</script>