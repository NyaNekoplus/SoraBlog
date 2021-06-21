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
      <h3 id="comments-list-title">Comments | <span class="noticom">{{ total===0?'暂无评论':total+' 条评论' }}</span></h3>
      <div id="loading-comments"><span></span></div>

      <div v-if="enableComment">
        <div v-if="hasComment">
          <comment-patch :key="total" :comment-list="commentList" class="commentwrap"></comment-patch><!--this.rootId-->
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

    <comment-box :key="commentSource" @submit-comment="submitComment"></comment-box>
  </section>
</template>

<script>
import {addComment, getCommentList, getCommentUpdatedList} from "../../api/comment";
import {message} from "../Message";
import CommentPatch from './patch';
import CommentBox from './box'
export default {
  name: "index",
  components: {
    CommentBox,
    CommentPatch
    //CommentPatch: () => import('./patch'),
    //CommentBox: () => import('./box'),
  },
  props: {
    blogId: {
      type: Number
    },
    commentSource: {
      type: String
    },
    enableComment: {
      type: Boolean
    },
  },
  data: () => ({
    //enableComment: true,
    hasComment: false,
    blog: null,

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
      //let b = this.$store.getters.blog;
      //alert('uid:'+b.uid + typeof(b.uid)+ ' en c:'+ b.enableComment)
      params.blogUid = this.blogId//blog.uid;
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentList(params).then(response=>{
        if (response.state === this.$STATE.SUCCESS){
          if (response.data.records.length !== 0){
            this.hasComment = true;
            this.total = response.data.total;
            this.totalPage = response.data.pages;
            this.commentList = response.data.records;
          }
        }else {
          message(response.message);
        }
      })
    },
    submitComment(c){
      let param = {};
      param.blogUid = this.blogId;
      param.userUid = c.userUid;
      param.toUid = c.toUid;
      param.toUserUid = c.toUserUid;
      //param.rootUid = c.rootUid; 后端处理
      param.source = this.commentSource;
      param.content = c.content;
      param.targetType = c.targetType;
      param.currentPage = this.currentPage
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
        params.blogUid = this.blogId//blog.uid;
      }else {params.blogUid = 0;}
      console.log("Blog uid: "+params.blogUid);
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentUpdatedList(params).then(response => {
        if (response.state === this.$STATE.SUCCESS) {
          this.commentList = response.data.records;
          this.total = response.data.total;
          this.totalPage = response.data.pages;
          this.$forceUpdate();
        }else {
          message(response.message)
        }
      })
    },
    changePage(page){
      this.currentPage = page;
      this.updateComment();
    },
    updateCommentPage(){
      let commentsPage = this.$route.params.page
      let anchor = window.location.hash
      if (commentsPage === undefined || !/^comment-page-\d+/.test(commentsPage) || !/^#comment-\d+/.test(anchor)){
        return  // comment-id
      }

      this.currentPage = Number.parseInt(commentsPage.split('-')[2])
      console.log('current comment page: '+this.currentPage)

      this.$nextTick(()=>{
        setTimeout(()=>{
          let anchor = window.location.hash
          console.log('anchor: '+anchor.substr(1))
          let elem = document.getElementById(anchor.substr(1))
          console.log(elem)
          elem.scrollIntoView(true)
        },10)
      })
    },
    getCommentInWhichPage(){
      let str = window.location.hash.split('-')[1];
      if (str === undefined || str === null) return
      let replyID = Number.parseInt(str)
      if (isNaN(replyID)) return
      /^#\d/.test(to.hash)
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
    console.log('comment blog id: '+this.blogId)
    console.log('comment hash: '+window.location.hash)
    console.log('blog link: '+this.$route.params.title)
    console.log('comment page: '+this.$route.params.page)
    //window.location.pathname

    this.updateCommentPage()


    this.blog = this.$store.getters.blog;
    this.getCommentList();

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