<template>
  <ul><!-- class="comment byuser comment-author-mashiro bypostauthor even depth-2"-->
    <li class="comment" :id="comment.uid" v-for="comment in commentList" :key="comment.uid"><!--id="comment-8435"-->
      <div class="contents">
        <div class="comment-arrow">
          <div class="main shadow">
            <div class="profile">
              <a href="javascript: return false;" rel="nofollow">
                <!--https://q2.qlogo.cn/headimg_dl?dst_uin=1723687845&amp;spec=100-->
                <img
                    src=""
                    :data-src="comment.user.qqAvatar?comment.user.qqAvatar:comment.user.avatarUrl"
                    class="lazyload avatar avatar-24 photo" alt="😀" width="24" height="24">
              </a>
            </div>
            <div class="commentinfo">
              <section class="commeta">
                <div class="left">
                  <h4 class="author">
                    <a href="javascript: return false;" rel="nofollow">
                      <img
                          src="" :data-src="comment.user.qqAvatar?comment.user.qqAvatar:comment.user.avatarUrl"
                          class="lazyload avatar avatar-24 photo" alt="😀" width="24"
                          height="24">
                      <span v-if="comment.user.userProxy === 0" class="bb-comment" title="博主">博主</span> {{ comment.user.username }}
                      <span class="showGrade0" title="萌萌哒新人~">
                        <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/level/level_0.svg" style="height: 1.5em; max-height: 1.5em; display: inline-block;">
                      </span>
                    </a>
                  </h4>
                </div>
                <a rel="nofollow" class="comment-reply-link" :id="'comment-'+comment.uid" :aria-label="`Reply to ${comment.user.username}`" @click="reply($event,comment)">Reply</a>
                <div class="right">
                  <div class="info">
                    <time>{{calculatePeriod(comment.createTime)}}</time>&nbsp;&nbsp;
                    <span class="useragent-info">(
                      <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">
                      &nbsp;{{ comment.user.browser }}&nbsp;&nbsp;
                      <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg">
                      &nbsp;{{ comment.user.os }} )
                    </span>
                    <span class="useragent-info-m">(
                      <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">&nbsp;&nbsp;
                      <img src="https://fastly.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg"> )
                    </span>
                    <!--&nbsp;来自: 江苏省南京市 移动-->
                  </div>
                </div>
              </section>
            </div>
            <div class="body"><p v-html="comment.content"></p></div>
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
    calculatePeriod(time){
      let d = new Date(time),
          month = '' + (d.getMonth() + 1),
          day = '' + d.getDate(),
          year = d.getFullYear(),
          hour = d.getUTCHours(),
          minute = d.getUTCMinutes(),
          second = d.getUTCSeconds();
      let p = new Date(),
          pmonth = '' + (p.getMonth() + 1),
          pday = '' + p.getDate(),
          pyear = p.getFullYear(),
          phour = p.getUTCHours(),
          pminute = p.getUTCMinutes(),
          psecond = p.getUTCSeconds();
      if (pyear === year&&pmonth === month){
        let t = '发布于'
        if (pday === day){
          if (phour === hour){
            if (pminute === minute){
              return t+(psecond-second)+'秒前'
            }else {
              return t+(pminute-minute)+'分钟前'
            }
          }else {
            return t+(phour-hour)+'小时前'
          }
        }else {
          return t+(pday-day)+'天前'
        }
      }else {
        return time;
      }
    },
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
.comments .main.shadow:hover img.avatar {
  -webkit-transform: rotate(360deg);
  -moz-transform: rotate(360deg);
  -ms-transform: rotate(360deg);
  -o-transform: rotate(360deg);
  transform: rotate(360deg);
}
.comment .profile img:hover {
  opacity: .8;
}
.comments .main.shadow img.avatar {
  transform: rotate(0deg);
  -webkit-transform: rotate(0deg);
  -moz-transform: rotate(0deg);
  -o-transform: rotate(0deg);
  -ms-transform: rotate(0deg);
  transition: all ease 1s;
  -webkit-transition: all ease 1s;
  -moz-transition: all ease 1s;
  -o-transition: all ease 1s;
  box-shadow: 0 1px 10px -6px rgba(0,0,0,.5);
  margin-left: 3px;
}
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
