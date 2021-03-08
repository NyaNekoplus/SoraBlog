<template>
  <div>
    <!--
    <div>
      <b-card no-body class="text-center">
        <div class="bg-primary text-light">
          x条评论
        </div>
      </b-card>
    </div>
-->
    <ol
        v-for="comment in commentData"
        v-bind:key="comment.id"
        class="comment-block"
    >
        <b-card
            no-body
        >
          <b-card-header
              :header="comment.user" header-border-variant="">
            <b-avatar src="https://placekitten.com/300/300" class="comment-author" size="lg"></b-avatar>
          </b-card-header>
          <b-card-body class="comment-body">

            <div class="comment-content">
              <b-icon-card-text title="bbbbb">aaaaa</b-icon-card-text>

              <!--<b-icon-card-image sr></b-icon-card-image>-->
              <!--<b-card-img  left :src="sora"></b-card-img>-->
              <b-card-text style="margin: 0 0 10px">
                {{ comment.content }}
              </b-card-text>

              <div >
                <b-button
                    class="button-reply"
                    variant="outline-primary"
                    rel="nofollow"
                    size="sm"
                    pill
                    @click="reply(comment)"
                >回复</b-button>
              </div>

            </div>
            <div>
              <CommentBox
                  class="comment-box"
                  :id="comment.id"
                  :comment-id="comment.id"
                  :is-reply="true"
                  @submitComment="submitComment"
              ></CommentBox>
              <CommentContent :commentData="comment.replyList"></CommentContent>
            </div>
          </b-card-body>

        </b-card>
    </ol>


  </div>
</template>

<script>
//import request from "@/util/request";
import CommentBox from "@/components/CommentBlock/input";
import {addComment} from "@/api/comment";

export default {
  name: "CommentContent",
  components:{
    CommentBox
  },
  props: ['commentData'],
  data(){
    return{
      //commentData: [],
      //sora:require('@/assets/sora.png')
    }
  },
  methods: {
    reply(comment){
      let commentId = comment.id;
      alert(commentId);
      document.getElementById('666').style.display = 'none';
      document.getElementById(commentId).style.display = 'block';
    },
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


  }
}
</script>

<style scoped>
::selection {
  background: #d9534f;
  color: white
}

.comment-box{
  display: none;
}
.comment-block{
  margin-top: 1rem;
  margin-bottom: 1rem;

  list-style: none;
  list-style-position: outside;
  list-style-image: none;
  list-style-type: none;

  padding: 2px;
  border-radius: 4px;
  -webkit-box-shadow: 0px 0px 8px black;
  background-color: rgba(230,238,232,0.65);
  box-shadow: 0px 0px 8px black;
  transition: background-color 0.3s ease-in-out,box-shadow 0.3s ease-in-out;
  -moz-transition: background-color 0.3s ease-in-out,box-shadow 0.3s ease-in-out;
  -webkit-transition: background-color 0.3s ease-in-out,box-shadow 0.3s ease-in-out;
  -o-transition: background-color 0.3s ease-in-out,box-shadow 0.3s ease-in-out;

}
.comment-block::before::after{
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.comment-block .comment-body{
  position: relative;
  display: block;
}
.comment-block .comment-body .comment-content{
  padding: 20px 55px 30px 45px;
  position: relative;
}

img{
  max-width: 100%;
  border-radius: 4px;
  vertical-align: middle;
  height: auto;
}
.comment-author{
  background-color: white;
  border: 2px solid white;
  border-radius: 50%;
  position: absolute;
  display: block;
  left: -40px;
  top: 10px;
  margin: 0;
  -webkit-transition: all .3s ease;
  -moz-transition: all .3s ease;
  -o-transition: all .3s ease;
  transition: all .3s ease;
}

.rignt-bottom{
  position: absolute;
  right: 1rem;
  bottom: 0.5rem;
}
.button-reply{
  position: absolute;
  bottom: 3px;
  right: 12px;
  font-size: 12px;
  text-transform: uppercase;
  display: block;
}
</style>