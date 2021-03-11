<template>
  <section id="comments" class="comments">

    <!--
    <div class="commentwrap comments-hidden">
      <div class="notification"><i class="iconfont icon-mark"></i><?php _e('view comments', 'sakura'); /*查看评论*/?> -
        <span class="noticom"><?php comments_number('NOTHING', '1'.__(" comment","sakura"), '%'.__(" comments","sakura")); ?> </span>
      </div>
    </div>
    -->

    <div class="comments-main">
      <h3 id="comments-list-title">Comments | <span class="noticom">{{ commentCount }}</span></h3>
      <div id="loading-comments"><span></span></div>

      <div v-if="enableComment">
        <div v-if="hasComment">
          <comment-patch :comment-list="commentList" class="commentwrap"></comment-patch><!--this.rootId-->
          <nav id="comments-navi"><a class="prev page-numbers"
                                     href="https://2heng.xin/theme-sakura/comment-page-56/#comments">« Older</a> <a
              class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-1/#comments">1</a> <span
              class="page-numbers dots">…</span> <a class="page-numbers"
                                                    href="https://2heng.xin/theme-sakura/comment-page-55/#comments">55</a> <a
              class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-56/#comments">56</a> <span
              aria-current="page" class="page-numbers current">57</span>
          </nav>
        </div>
        <div v-else>
          <div class="commentwrap">
            <div><i class="iconfont icon-mark"></i> 暂时没有评论哦~留下你的足迹吧!</div><!-- class="notification-hidden"-->
          </div>
        </div>
      </div>
      <div v-else>
        评论暂时不开放哦
      </div>
    </div>

    <comment-box @submit-comment="submitComment"></comment-box>



  </section>
</template>

<script>
import { addComment, getCommentList } from "../../api/comment";

export default {
  name: "index",
  components: {
    CommentPatch: () => import('./patch'),
    CommentBox: () => import('./box'),
  },
  props: {
    blogId: {
      type: Number
    },
    enableComment: {
      type: Boolean
    }
  },
  data: () => ({
    commentCount: 1322,
    //enableComment: true,
    hasComment: false,

    pageSize: 10,
    currentPage: 1,
    commentList: [],
  }),
  methods: {
    getCommentList(){
      let params = {};
      params.articleId = this.blogId;
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentList(params).then(response=>{
        console.log(response.state);
        if (response.state === this.$STATE.SUCCESS){
          if (response.data.records.length !== 0){
            this.hasComment = true;
            this.commentList = response.data.records;
          }
          console.log(response.data.records);
        }else {
          alert(response.message);
          alert('获取评论失败');
        }
      })
    },
    submitComment(c){
      //alert('father');
      let param = {};
      param.articleId = c.articleId;
      param.fromUid = c.fromUid;
      param.toUid = c.toUid;
      param.parentId = c.parentId;
      param.content = c.content;
      param.targetType = c.targetType;

      //param.commentTime = c.commentTime;
      addComment(param).then(response=> {
        if (response.state === this.$STATE.SUCCESS) {
          alert(response.message);
          alert('评论成功');
          this.updateComment();
        } else {
          alert(response.message);
          alert('发送失败');
        }
      });

    },
    updateComment(){
      let params = {};
      params.articleId = this.$store.getters.blog.id;
      params.pageSize = 10//this.pageSize;
      params.currentPage = 1//this.currentPage;
      getCommentList(params).then(response => {
        if (response.state === this.$STATE.SUCCESS) {
          alert(response.message);
          alert('更新评论成功');
          this.commentList = response.data.records;
          //this.setCommentList(response.data.records);
        } else {
          alert(response.message);
          alert('更新评论失败');
        }
      })
    }
  },
  mounted() {
    this.getCommentList();
  }
}
</script>

<style scoped>

</style>