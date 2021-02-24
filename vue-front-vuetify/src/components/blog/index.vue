<template>
  <v-container>
    <v-row>
      <v-col
          v-for="blog in blogList"
          :key="blog.id"
          cols="12"
      >
        <!--
        d-flex justify-space-between
                      :aspect-ratio="1.43"
                      post-list-thumb post-list-show
                      post-content-wrap
        -->
        <v-card
            color="transparent"
            elevation="2"
            class=""
        >
          <div>
            <v-img

                height="300px"
                :src="blog.src"></v-img>
          </div>

          <div class="">
              <v-card-title
                  class="headline"
                  v-text="blog.title"
              ></v-card-title>

              <v-card-subtitle v-text="blog.artist"></v-card-subtitle>

              <v-card-actions>
                <v-btn
                    v-if="blog.artist === 'Ellie Goulding'"
                    class="ml-2 mt-3"
                    fab
                    icon
                    height="40px"
                    right
                    width="40px"
                >
                  <v-icon>mdi-play</v-icon>
                </v-btn>

                <v-btn
                    v-else
                    class="ml-2 mt-5"
                    outlined
                    rounded
                    small
                >
                  START RADIO
                </v-btn>
              </v-card-actions>
            </div>
        </v-card>
      </v-col>
    </v-row>
    <v-card>

    </v-card>
  </v-container>
</template>

<script>
import {getBlogList} from "@/api/article";

export default {
  name: "index",
  data: () => ({
    blogList: [],
  }),
  mounted() {
    getBlogList().then(response=>{
      console.log(response);
      this.blogList = response;
    })
  }
}
</script>

<style scoped>
.v-image--image{
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: all .6s
}
.v-image--image:hover {
  transform: scale(1.1)
}
.v-card{
  will-change: transform;
  float: left;
  width: 100%;
  height: 300px;
  position: relative;
  margin: 20px 0 20px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0);
  box-shadow: 0 1px 20px -8px rgba(0, 0, 0, .5);
  opacity: 0;

  animation: post-list-show .5s;
  -webkit-animation: post-list-show .5s;
  opacity: 1;

}
@keyframes v-card {
  0% {
    opacity: 0;
    -webkit-transform: translateY(80px);
    transform: translateY(80px)
  }
  100% {
    opacity: 1;
    -webkit-transform: translateY(0);
    transform: translateY(0)
  }
}
.pad-zero{
  padding: 0;
}
</style>