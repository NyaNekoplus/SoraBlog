<template>
  <div id="primary" class="content-area">

    <main id="main" class="site-main" role="main">

      <h1 class="main-title" style="font-family: 'Ubuntu', sans-serif;"><i class="fa fa-envira" aria-hidden="true"></i> Discovery</h1>
      <blog-card v-for="blog in blogData" :key="blog.uid" :blog="blog"></blog-card>

    </main><!-- #main -->

    <div class="page-wrapper">
      <nav id="comments-navi">
        <a v-if="currentPage!==1" class="prev page-numbers" href="https://2heng.xin/theme-sakura/comment-page-54/#comments">« Older</a>
        <a v-for="i in totalPage" :key="i" :class="`page-numbers ${(i)===currentPage?'current':''}`" href="https://2heng.xin/theme-sakura/comment-page-1/#comments">{{ i }}</a>
        <!--
        <a class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-1/#comments">1</a>
        <span class="page-numbers dots">…</span>
        <a class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-53/#comments">53</a>
        <a class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-54/#comments">54</a>
        <span aria-current="page" class="page-numbers current">{{ currentPage }}</span>
        <a class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-56/#comments">56</a>
        <a class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-57/#comments">57</a>
        -->
        <a v-if="currentPage!==totalPage" class="next page-numbers" href="https://2heng.xin/theme-sakura/comment-page-56/#comments">Newer »</a>
      </nav>
      <!--<a href="https://2heng.xin/page/2/?_pjax=%23page">Previous</a>-->
    </div>
  </div><!-- #primary -->
</template>

<script>
import {mapMutations} from "vuex"
import {getBlogList} from "../api/article";

export default {
  name: "list",
  components: {
    BlogCard: () => import('@/components/BlogCard'),
  },
  props: {
    categoryUid: {
      type: String
    }
  },
  data: () => ({
    pageSize: 10,
    currentPage: 1,
    totalPage: 0,

    blogData: [],
  }),
  methods: {
    ...mapMutations(['setBlogList']),
    getPageInfo(){

    },
    getData() {
      if (this.$store.getters.blogList){
        console.log(this.$store.getters.blogList)
        this.blogData = this.$store.getters.blogList;
      }else{
        let params = {};
        params.categoryUid = this.categoryUid;
        params.isDraft = false;
        params.pageSize = this.pageSize;
        params.currentPage = this.currentPage;
        getBlogList(params).then(response => {
          console.log(response);
          if (response.state === this.$STATE.SUCCESS) {
            let data = response.data;
            this.blogData = data.records;
            this.pageSize = data.size;
            this.totalPage = data.pages;
            this.currentPage = data.current;
            console.log(this.totalPage)
            this.setBlogList(this.blogData);
          } else {
            alert('Message from Back-end: '+response.message);
            alert('获取文章数据失败');
          }
        })
      }
    },
    loadCover() {
      let imgList = document.getElementsByClassName('lazyload');
      //console.log(imgList.length);
      for (let i=0;i<imgList.length;i++){
        //console.log(imgList[i].hasAttribute('data-isLoaded'));
        if (!imgList[i].hasAttribute('data-isLoaded')){
          let imgToTop = imgList[i].offsetTop;
          let viewportHeight = document.documentElement.clientHeight;
          let scrollHeight = document.documentElement.scrollTop;
          //console.log(imgToTop-scrollHeight);
          if (imgToTop-scrollHeight < viewportHeight + 50){
            imgList[i].setAttribute('src',imgList[i].getAttribute('data-src'))
            imgList[i].setAttribute('data-isLoaded', 1);
          }
        }
      }
    }
  },
  created() {
    this.getData();
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>