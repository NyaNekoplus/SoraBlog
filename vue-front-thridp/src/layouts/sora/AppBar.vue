<template>
  <!--
  <v-app-bar
      app
      color="white"
      flat
  >
    <v-container class="py-0 fill-height">
      <v-avatar
          class="mr-10"
          color="grey darken-1"
          size="32"
      ></v-avatar>

      <v-btn
          v-for="link in links"
          :key="link"
          text
      >
        {{ link }}
      </v-btn>

      <v-spacer></v-spacer>

      <v-responsive max-width="260">
        <v-text-field
            dense
            flat
            hide-details
            rounded
            solo-inverted
        ></v-text-field>
      </v-responsive>
    </v-container>
  </v-app-bar>
  -->
  <v-app-bar
      app
      color="white"
      flat
  >
    <v-avatar
        :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
        size="32"
    ></v-avatar>

    <v-tabs
        centered
        class="ml-n9"
        color="grey darken-1"
    >
      <v-tab
          v-for="link in links"
          :key="link"
      >
        {{ link }}
      </v-tab>
    </v-tabs>

    <v-avatar
        class="hidden-sm-and-down"
        color="grey darken-1 shrink"
        size="32"
    ></v-avatar>
  </v-app-bar>

</template>

<script>
export default {
  name: "SoraBar",
  data:() => ({
      links: [
        'Dashboard',
        'Messages',
        'Profile',
        'Updates',
      ],
  }),
}
</script>

<style scoped>

</style>