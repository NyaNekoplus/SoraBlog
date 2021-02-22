<template>
  <v-footer
      padless
  >
    <v-card
        flat tile
        width="100%" class="text-center"
    >
      <v-card-text class="text--white">
        <strong>Crafted by Vincent Tsai</strong>
      </v-card-text>
      <v-card-text class="text--white">
        <strong>©Vincent Tsai - {{ new Date().getFullYear() }}</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: "SoraFooter",
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>