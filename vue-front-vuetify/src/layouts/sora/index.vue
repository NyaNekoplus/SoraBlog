<template>
  <v-app id="inspire">

    <sora-bar/>

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

  }),
}
</script>