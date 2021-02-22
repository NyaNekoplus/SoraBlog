<template>
<article>
  <b-container>
    <b-row>
      <!-- Content block Start -->
      <b-col cols="10">
        <div>
          <Breadcrumb></Breadcrumb>

          <section class="page-header">
            <header>
                <b-jumbotron bg-variant="info" text-variant="white" border-variant="dark">
                  <template #header>BootstrapVue</template>

                  <template #lead>
                    This is a simple hero unit, a simple jumbotron-style component for calling extra attention to
                    featured content or information.
                  </template>

                  <hr class="my-4">

                  <p>
                    It uses utility classes for typography and spacing to space content out within the larger
                    container.
                  </p>
                </b-jumbotron>
            </header>

            <div></div>

            <div id="comments-template">
              <div id="comments">
                <CommentHeader :count="commentNum"></CommentHeader>
                <CommentContent :commentData="commentData"></CommentContent>
                <CommentBox
                    :id="666"
                    :is-reply="false"
                    @submitComment="submitComment"
                ></CommentBox>
              </div>
            </div>



          </section>

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
//import request from "@/util/request";

import Breadcrumb from "@/components/Breadcrumb"
import AboutMe from "@/components/Sidebar/AboutMe";
import TagCloud from "@/components/Sidebar/TagCloud";
import CommentHeader from "@/components/CommentBlock/header"
import CommentBox from "@/components/CommentBlock/input";
import CommentContent from "@/components/CommentBlock/content"

import {addComment} from "@/api/comment";
import {getCommentList} from "@/api/comment";

export default {
  name: "about",
  components: {
    Breadcrumb,
    AboutMe,
    TagCloud,
    CommentHeader,
    CommentBox,
    CommentContent,
  },
  data(){
    return{
      currentPage: 1,
      pageSize: 10,
      commentNum: 10000,
      commentData: [],
    }
  },
  methods:{
    submitComment(c){
      alert('father');
      let param = {};
      param.articleId = c.articleId;
      param.fromUid = c.fromUid;
      param.toUid = c.toUid;
      param.parentId = c.parentId;
      param.content = c.content;
      param.targetType = c.targetType;

      //param.commentTime = c.commentTime;
      alert(param)
      addComment(param).then(response=> {
        if (response.state === this.$STATE.SUCCESS) {
          alert(response.message);
          alert('评论成功');
        }
        else {
          alert(response.message);
          alert('发送失败');
        }
      });
    },

  },
  mounted() {
    let params = {};
    params.articleId = 1;
    params.pageSize = this.pageSize;
    params.currentPage = this.currentPage;
    getCommentList(params).then(response=>{
      if (response.state === this.$STATE.SUCCESS){
        this.commentData = response.data.records;
      }
    })
    /*request.get('../mock/comment.json').then(response=>{
      this.commentData=response
      console.log(response);
    });*/
  }
}
</script>

<style scoped>

</style>