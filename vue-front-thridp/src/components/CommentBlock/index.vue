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

      <div v-if="hasComment">
        <div v-if="enableComment">

          <comment-patch :comment-list="commentList" :is-child="false"></comment-patch>

          <nav id="comments-navi"><a class="prev page-numbers"
                                     href="https://2heng.xin/theme-sakura/comment-page-56/#comments">« Older</a> <a
              class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-1/#comments">1</a> <span
              class="page-numbers dots">…</span> <a class="page-numbers"
                                                    href="https://2heng.xin/theme-sakura/comment-page-55/#comments">55</a> <a
              class="page-numbers" href="https://2heng.xin/theme-sakura/comment-page-56/#comments">56</a> <span
              aria-current="page" class="page-numbers current">57</span>
          </nav>
        </div>
        <div v-else>评论暂时不开放哦</div>
      </div>
      <div v-else>
        <div class="commentwrap">
          <div class="notification-hidden"><i class="iconfont icon-mark"></i> 暂时没有评论哦~留下你的足迹吧!</div>
        </div>
      </div>
    </div>

    <comment-box></comment-box>



  </section>
</template>

<script>
import {getCommentList} from "../../api/comment";

export default {
  name: "index",
  components: {
    CommentPatch: () => import('./patch'),
    CommentBox: () => import('./box'),
  },
  data: () => ({
    commentCount: 1322,
    enableComment: true,
    hasComment: true,

    pageSize: 10,
    currentPage: 1,
    commentList: [
      {
        id: 1,
        userName: 'vincent',
        content: 'sorasora',
        replyList: [{
            id: 2,
            userName: 'sora',
            content: '~~~',
            replyList: [
              {
                id: 2,
                userName: 'sora',
                content: '~~~',
                replyList: []
              },
              {
                id: 2,
                userName: '言峰',
                content: 'mmm',
                replyList: []
              },
            ]
          }]
      },
      {
        id: 2,
        userName: 'sora',
        content: 'tsai',
        replyList: [
          {
            id: 1,
            userName: 'vincent',
            content: '~~~',
            replyList: []
          }
        ]
          
      },
    ],
  }),
  methods: {
    getCommentList(){
      let params = {};
      params.articleId = 1;
      params.pageSize = this.pageSize;
      params.currentPage = this.currentPage;
      getCommentList(params).then(response=>{
        if (response.state === this.$STATE.SUCCESS){
          this.commentList = response.data.records;
        }
      })
    },
  },
  mounted() {
    //this.getCommentList();
  }
}
</script>

<style scoped>

</style>