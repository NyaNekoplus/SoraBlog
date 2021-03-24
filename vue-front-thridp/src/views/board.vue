<template>
  <!-- 留言板 -->
  <div>
    <!--<div class="pattern-center-blank"></div>-->
    <div class="pattern-center-sakura">
      <div class="pattern-attachment-img"><img src="https://2heng.xin/wp-content/uploads//2017/08/pixiv54839592.png"
                                               data-src="https://2heng.xin/wp-content/uploads//2017/08/pixiv54839592.png"
                                               class="lazyload" onerror="imgError(this,3)"
                                               style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;">
      </div>
      <header class="pattern-header "><h1 class="entry-title">留言板</h1></header>
    </div>
    <comment-block :comment-source="'BOARD'"></comment-block><!--:enable-comment="blog.enableComment" -->
  </div>
</template>

<script>
import {mapMutations} from "vuex";
//import {getBlogByTitle} from "../api/article";

export default {
  name: "board",
  components: {
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({}),
  methods: {
    ...mapMutations(['setBlog', 'removeToInfo']),

  },
  created() {
    /*
    console.log('page create: ' + this.$route.params.title);
    if (this.$store.getters.blog) {
      let t_blog = this.$store.getters.blog;
      if (t_blog.title === this.$route.params.title){
        this.blog = this.$store.getters.blog;
      }
    } else {
      this.blog = getBlogByTitle(this.$route.params.title).then(response => {
        console.log("page: "+response.state);
        if (response.state === this.$STATE.SUCCESS) {
          this.blog = response.data.records;
          this.setBlog(response.data.records)
          console.log("page: "+this.blog);
        } else {
          alert(response.message);
          alert('获取文章失败');
          this.$router.push('/404');
        }
      });
    }*/

  },
  mounted() {
    this.removeToInfo();
    //this.blog = this.$store.getters.blog;
    //this.toId = this.$store.getters.toId;
  }
}
</script>

<style scoped>

</style>