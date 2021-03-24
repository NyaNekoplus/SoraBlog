<template>
  <ul><!-- class="comment byuser comment-author-mashiro bypostauthor even depth-2"-->
    <li class="comment" :id="comment.uid" v-for="comment in commentList" :key="comment.uid"><!--id="comment-8435"-->
      <div class="contents">
        <div class="comment-arrow">
          <div class="main shadow">
            <div class="profile">
              <a href="javascript: return false;" rel="nofollow">
                <img
                    src="https://q2.qlogo.cn/headimg_dl?dst_uin=1723687845&amp;spec=100" onerror="imgError(this,1)"
                    data-src="https://q2.qlogo.cn/headimg_dl?dst_uin=1723687845&amp;spec=100"
                    class="lazyload avatar avatar-24 photo" alt="😀" width="24" height="24">
              </a>
            </div>
            <div class="commentinfo">
              <section class="commeta">
                <div class="left">
                  <h4 class="author">
                    <a href="javascript: return false;" rel="nofollow">
                      <img
                          src="https://q2.qlogo.cn/headimg_dl?dst_uin=1723687845&amp;spec=100" data-src=""
                          class="lazyload avatar avatar-24 photo" alt="😀" onerror="imgError(this,1)" width="24"
                          height="24">
                      <span v-if="comment.user.userProxy === 2" class="bb-comment isauthor" title="博主">博主</span> {{ comment.user.username }}
                      <span class="showGrade0" title="萌萌哒新人~">
                  <img
                      src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/level/level_0.svg"
                      style="height: 1.5em; max-height: 1.5em; display: inline-block;">
                </span>
                    </a>
                  </h4>
                </div>
                <a rel="nofollow" class="comment-reply-link" :id="comment.uid" data-commentid="8435" data-postid="3137"
                   data-belowelement="comment-8435" data-respondelement="respond" aria-label="Reply to センカメイ" @click="reply($event,comment)">Reply</a>
                <div class="right">
                  <div class="info">
                    <time datetime="2021-02-27">发布于 1 天前 {{comment.createTime}}</time>&nbsp;&nbsp;
                    <span class="useragent-info">(
                <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">
                &nbsp;{{ comment.user.browser }}&nbsp;&nbsp;
                <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg">
                &nbsp;{{ comment.user.os }} )
              </span>
                    <span class="useragent-info-m">(
                <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">&nbsp;&nbsp;
                <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg"> )
              </span>
                    &nbsp;来自: 江苏省南京市 移动
                  </div>
                </div>
              </section>
            </div>
            <div class="body"><p>{{ comment.content }}</p></div>
          </div>
          <div class="arrow-left"></div>
        </div>

      </div>
      <hr>
      <patch :comment-list="comment.replyList" class="children"></patch>

    </li>
  </ul>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "patch",
  props: {
    commentList: {
      type: Array
    }
  },
  data: () => ({
  }),
  methods: {
    ...mapMutations(['setToInfo']),
    reply(e, comment){
      let box = document.getElementById('respond');
      //let refbox = this.$refs.commentbox;
      //console.log('refbox:'+refbox)
      let current = e.currentTarget.parentNode.parentNode.parentNode;
      //let toId = e.currentTarget.id;
      let toInfo = {
        toUid: comment.uid,
        toUserUid: comment.user.uid
      }
      this.setToInfo(toInfo);
      console.log('toInfoUid: '+toInfo.toUid);

      box.children[0].children[0].children[0].style = '';
      current.appendChild(box);

    },
  },
  watch: {
    /*
    isChild: function (newVal){ //, oldVal
      console.log(newVal);
      if (newVal){
        console.log('子评论');
        this.liClass = 'comment byuser comment-author-mashiro bypostauthor even depth-2';
      }else {
        console.log('非子评论');
        this.liClass = 'comment even thread-even depth-1';
      }
    }
  },
  */
  }
}
</script>

<style scoped>
.comment-respond #cancel-comment-reply-link {
  background:
      #f4f6f8;
  border-radius: 3px;
  padding: 12px 25px;
  font-size: 12px;
  color:
      #454545;
}
</style>