<template>
  <article>
    <b-container>
      <b-row>
        <!-- Content block Start -->
        <b-col cols="10">
          <div>
            <Breadcrumb></Breadcrumb>

            <ArticleBlock></ArticleBlock>


            <div class="mt-3">
              <b-pagination v-model="currentPage" :total-rows="rows" align="center"></b-pagination>
            </div>

          </div>
        </b-col>
        <!-- Content block End -->

        <!-- Sidebar Start -->
        <b-col cols="2">
          <div class="sidebar">
            <AboutMe></AboutMe>
            <tag-cloud></tag-cloud>
          </div>

        </b-col>
        <!-- Sidebar End -->
      </b-row>
    </b-container>
  </article>
</template>

<script>
  import Breadcrumb from "@/components/Breadcrumb";
  import ArticleBlock from "@/components/ArticleBlock";
  import AboutMe from "@/components/Sidebar/AboutMe";
  import TagCloud from "@/components/Sidebar/TagCloud";

  export default {
    name: "index",
    data(){
      return{
        //blogData: []
      };
    },
    components: {
      Breadcrumb,
      ArticleBlock,
      AboutMe,
      TagCloud,
    },
    /*
    mounted() {
      request.get("../mock/article.json").then(response=>{
        this.blogData=response.data;
        console.log(response.data);
      });
    }
    */
  }
</script>

<style scoped>

</style>