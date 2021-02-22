<template>
  <section>

    <div class="reply-title">
      <b-card no-body class="text-center">
        <div class="bg-primary text-light">
          <div>
            <i class="fa fa-pencil"></i>
            欢迎留言
            <!--<small><a rel="nofollow" class="cancel-comment-reply">取消回复</a></small>-->
            <b-button v-if="isReply" class="cancel-comment-reply" @click="cancelReply(commentId)">取消评论</b-button>
          </div>
        </div>
      </b-card>
    </div>

    <div>
      <div class="emoji-panel"></div>
      <b-form-textarea
          id="textarea"
          rows="8"
          no-resize
          placeholder="来帮博主扫扫雪吧~"
          class="comment-textarea"
          v-model="value"
      ></b-form-textarea>

      <b-button style="margin: 0 0 10px;" block variant="outline-primary" @click="submitComment">发表评论</b-button>
    </div>

  </section>
</template>

<script>
import { parseTime} from "@/util";

export default {
  name: "index",
  props: ['isReply','commentId'],
  data(){
    return {
      value:'',
    }
  },
  methods: {
    submitComment(){
      if (this.value === ''){
        console.log('不能为空');
        return;
      }
      let param = {};
      param.articleId = 1;
      param.fromUid = 1;
      param.content = this.value;
      if(this.isReply === true){
        param.fromUid = 3;
        param.toUid = 2;
        param.parentId = 1;
        param.targetType = 1; // 回复评论
      }else {
        param.toUid = null;
        param.parentId = null;
        param.targetType = 0;
      }
      param.commentTime = parseTime("YYYY-mm-dd HH:MM:SS",new Date());
      alert(param.commentTime);
      this.$emit('submitComment',param);

    },
    cancelReply(commentId){
      alert(commentId);
      document.getElementById('666').style.display = 'block';
      document.getElementById(commentId).style.display = 'none';
    },
  }
}
</script>

<style scoped>
.reply-title{
  font-weight: 100;

  padding: 15px;
  margin-bottom: 20px;
  border: 1px solid
  transparent;
  border-radius: 4px;
  overflow: auto;
  color: #3a87ad;
  background-color: rgba(217,237,247,0.8);
  margin: 10px auto;
  text-align: center;
  font-size: 14px;
}
.reply-title .cancel-comment-reply {
  padding: 2px 5px;
  background-color: #d9534f;
  color: white;
  border-radius: 4px;
  font-size: 14px;
}

.comment-textarea{
  margin: 0;
  margin-bottom: 10px;
  padding: 10px 15px;
  width: 100%;
  border-radius: 4px;
  background-color: transparent;
  border: 2px solid
  #c1c1c1;

  overflow: auto;
  vertical-align: top;
}
</style>