<template>
  <div id="primary" class="content-area">
    <main id="main" class="site-main" role="main">
      <h1 class="main-title" style="font-family: 'Ubuntu', sans-serif;"><i class="fa fa-envira" aria-hidden="true"></i> Discovery</h1>
      <blog-card v-for="blog in blogData" :key="blog.uid" :blog="blog"/>
    </main>

    <div class="page-wrapper">
      <nav id="comments-navi">
        <a v-if="currentPage!==1" class="prev page-numbers">« Older</a>
        <span v-if="totalPage>10&&currentPage>4" @click="changePage(1)" class="page-numbers">1</span>
        <span v-if="totalPage>10&&currentPage>4" class="page-numbers dots">…</span>
        <span v-if="totalPage!==1"
            v-for="i in totalPage>10?pageRange:totalPage" :key="i"
            @click="changePage(i)"
            style="padding: 0 5px;"
            :class="`page-numbers ${i===currentPage?'current':''}`"
        >
          {{ i }}
        </span>
        <span v-if="totalPage>10&&currentPage<(totalPage-3)" class="page-numbers dots">…</span>
        <span v-if="totalPage>10&&currentPage<(totalPage-3)" @click="changePage(totalPage)" class="page-numbers">{{ totalPage }}</span>
        <a v-if="currentPage!==totalPage" @click="changePage(this.currentPage+1)" class="next page-numbers">Newer »</a>
      </nav>
      <!--<a href="https://2heng.xin/page/2/?_pjax=%23page">Previous</a>-->
    </div>
  </div>
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
    changePage(page){
      this.currentPage = page;
      this.getData();
    },
    getData() {
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
        }
      })

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
  computed: {
    pageRange(){
      let range = [];
      let cur = this.currentPage;
      let v = 3;
      let s=cur-v+1;
      if (s<=0)s=1
      let e = cur+v;
      if (e>=this.totalPage)e=this.totalPage+1
      for (;s<e;s++){//todo
        range.push(s);
      }
      return range;
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
.next,.prev{
  color: #00bbff;
}
</style>