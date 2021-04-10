<template>
  <v-card-text>
    <v-row align="center">
      <v-col v-for="image in imageList" :key="image.uid" cols="3">
        <v-card>
          <v-img :src="image.url" class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="200px">
            <v-card-title v-text="image.originalName"></v-card-title>
          </v-img>
          <v-card-actions>
            <v-spacer></v-spacer>

            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on">
                  <v-icon>mdi-heart</v-icon>
                </v-btn>
              </template>
              <span>复制URL</span>
            </v-tooltip>
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on">
                  <v-icon>mdi-bookmark</v-icon>
                </v-btn>
              </template>
              <span>复制Markdown URL</span>
            </v-tooltip>
            <v-tooltip bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on">
                  <v-icon>mdi-share-variant</v-icon>
                </v-btn>
              </template>
              <span>复制URL</span>
            </v-tooltip>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="12">

      </v-col>
    </v-row>
  </v-card-text>
</template>

<script>
export default {
  name: "WebsiteConfig",
  data: () => ({}),
}
</script>

<style scoped>

</style>
