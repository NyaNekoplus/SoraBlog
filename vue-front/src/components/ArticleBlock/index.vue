<template>
  <div>

    <div class="article-container">

      <b-card-group columns>
        <div
            v-for="blog in articleList"
            v-bind:key="blog.id"
        >
          <b-card no-body>
            <!--
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              -->

            <b-card-header header-bg-variant="">
              <!--
              <b-card no-body>
                <b-card-body>
                  {{blog.title}}
                </b-card-body>
              </b-card>
              -->
              <b-row>
                <b-col cols="9">
                  <div class="article-button">
                    <b-button :to="blog.title" class="shadow">{{blog.title}}</b-button>
                  </div>

                </b-col>
                <b-col cols="3">
                  <small>
                    2021-2-4
                    {{blog.releaseDate}}
                  </small>
                </b-col>
              </b-row>

            </b-card-header>

            <b-card-body>
              <b-card-text>
                {{ blog.summary }}
              </b-card-text>
              <b-button href="#" variant="primary">Go somewhere</b-button>
            </b-card-body>

          </b-card>
        </div>

      </b-card-group>

    </div>
  </div>


</template>

<script>
import {getArticleList} from "@/api/article";

export default {
  name: "index",
  //props: ['blogData'],
  data(){
    return{
      articleList: [],

    };
  },
  mounted() {
    getArticleList().then(response=>{
      this.articleList = response;
    })
  }
}
</script>

<style scoped>
.article-button{
  width: 80%;
  height: 80%;
}
</style>