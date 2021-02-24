<template>
  <v-container style="width: 50rem"><!--32vw 800/2560 class="grey lighten-3" -->
    <v-row align-content="center" justify="center">

      <v-col
          cols="12"
          lg="12"
      >
        <v-sheet
            min-height="70vh"
            rounded="lg"
        >
          <!--  -->
          思绪来得快去得也快，偶尔在这里停留
          <blog-chunk></blog-chunk>
        </v-sheet>

        <v-card>
          加载更多
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
export default {
  name: "index",
  components: {
    Sidebar: ()=>import('@/components/sidebar'),
    BlogChunk: ()=>import('@/components/blog'),
  },
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>