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
      <h3 id="comments-list-title">Comments | <span class="noticom">{{ total===0?'暂无评论':total }}</span></h3>
      <div id="loading-comments"><span></span></div>

      <div v-if="enableComment">
        <div v-if="hasComment">
          <comment-patch :comment-list="commentList" class="commentwrap"></comment-patch><!--this.rootId-->
          <nav id="comments-navi">
            <a v-if="currentPage!==1" class="prev page-numbers">« Older</a>
            <span v-if="totalPage>10&&currentPage>3" class="page-numbers">1</span>
            <span v-if="totalPage>10&&currentPage>3" class="page-numbers dots">…</span>
            <span
                v-for="i in totalPage>10?pageRange:totalPage" :key="i"
                @click="changePage(i)"
                style="padding: 0 5px;"
                :class="`page-numbers ${i===currentPage?'current':''}`"
            >
              {{ i }}
            </span>
            <span v-if="totalPage>10&&currentPage<(totalPage-2)" class="page-numbers dots">…</span>
            <span v-if="totalPage>10&&currentPage<totalPage-2" @click="changePage(totalPage)" class="page-numbers">{{ totalPage }}</span>
            <a v-if="currentPage!==totalPage" @click="changePage(this.currentPage+1)" class="next page-numbers">Newer »</a>
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
import {addComment, getCommentList, getCommentUpdatedList} from "../../api/comment";
import {message} from "../Message";

export default {
  name: "index",
  components: {
    CommentPatch: () => import('./patch'),
    CommentBox: () => import('./box'),
  },
  props: {
    blogUid: {
      type: Number,
      default: 0 // 默认不在文章页下评论
    },
    commentCount: {
      type: Number
    },
    enableComment: {
      type: Boolean
    },
    commentSource: {
      type: String
    },
  },
  data: () => ({
    //enableComment: true,
    hasComment: false,

    total: 0,
    totalPage: 0,
    pageSize: 10,
    currentPage: 1,
    commentList: [],
  }),
  methods: {
    getCommentList(){
      let params = {};
      params.source = this.commentSource;
      console.log("Comment source: "+params.source);
      if (params.source === 'BLOG'){
        params.blogUid = this.blogUid//blog.uid;
      }else {params.blogUid = 0;}
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentList(params).then(response=>{
        if (response.state === this.$STATE.SUCCESS){
          message(response.message);
          if (response.data.records.length !== 0){
            this.hasComment = true;
            this.total = response.data.total;
            this.totalPage = response.data.pages;
            this.commentList = response.data.records;
          }
        }
      })
    },
    submitComment(c){
      let param = {};
      param.blogUid = this.blogUid;
      param.userUid = c.userUid;
      param.toUid = c.toUid;
      param.toUserUid = c.toUserUid;
      //param.rootUid = c.rootUid; 后端处理
      param.source = this.commentSource;
      param.content = c.content;
      param.targetType = c.targetType;
      //param.commentTime = c.commentTime;
      addComment(param).then(response=> {
        message(response.message)
        if (response.state === this.$STATE.SUCCESS) {
          this.updateComment();
        }
      });
    },
    updateComment(){
      let params = {};
      params.source = this.commentSource;
      console.log("Comment source: "+params.source);
      if (params.source === 'BLOG'){
        params.blogUid = this.blogUid//blog.uid;
      }else {params.blogUid = 0;}
      console.log("Blog uid: "+params.blogUid);
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentUpdatedList(params).then(response => {
        message(response.message)
        if (response.state === this.$STATE.SUCCESS) {
          this.commentList = response.data.records;
          this.total = response.data.total;
          this.totalPage = response.data.pages;
        }
      })
    },
    changePage(page){
      this.currentPage = page;
      this.updateComment();
    }
  },
  computed: {
    pageRange(){
      let range = [];
      let cur = this.currentPage;
      let v = 2;
      let s=cur-v;
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
    this.getCommentList();
  },
  mounted() {
    //this.getCommentList();
  }
}
</script>

<style scoped>
.next,.prev{
  color: #00bbff;
}
</style>