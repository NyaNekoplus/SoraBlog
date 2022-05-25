<template>
  <div id="respond" class="comment-respond">
    <h3 id="reply-title" class="comment-reply-title">
      <small><a rel="nofollow" id="cancel-comment-reply-link" style="display:none;" @click="cancelReply">Cancel
        Reply</a></small>
    </h3>
    <div id="commentform" class="comment-form" novalidate="">
      <p>
        <i class="iconfont icon-markdown"></i> Html tag Supported
        <!--
        <i class="iconfont icon-markdown"></i> Markdown Supported while
        <i class="fa fa-code" aria-hidden="true"></i>Forbidden
        -->
      </p>
      <div class="comment-textarea">
        <textarea placeholder="你是我一生只会遇见一次的惊喜 ..." name="comment" class="commentbody" id="comment" rows="5" tabindex="4"
                  v-model="content"></textarea>
        <label class="input-label active">你是我一生只会遇见一次的惊喜 ...</label>
      </div>
      <div id="upload-img-show"></div>
      <p id="emotion-toggle" class="no-select">
        <span v-show="!emojiPanelState" @click="emojiPanelState = !emojiPanelState">Emoji panel on</span>
        <span v-show="emojiPanelState" @click="emojiPanelState = !emojiPanelState">Emoji panel off</span>
      </p>
      <div class="emotion-box no-select" :style="`display: ${emojiPanelState?'inline':'none'};`">
        <table class="motion-switcher-table">
          <tbody>
          <tr>
            <!--
            <th onclick="motionSwitch('.bili')" class="bili-bar">bilibili~</th>
            <th onclick="motionSwitch('.menhera')" class="menhera-bar">(✪ω✪)</th>
            -->
            <th class="tieba-bar  on-hover">Tieba</th>
          </tr>
          </tbody>
        </table>

        <div class="tieba-container motion-container">
          <span v-for="(value,key) in tiebaEmojiMap" :key="key">
            <img @click="addEmoji($event)"
                 :src="value"
                 :alt="key">
          </span>
        </div>
      </div>
      <div class="cmt-info-container">
        <div class="comment-user-avatar">
          <!--src="http://q.qlogo.cn/headimg_dl?dst_uin=1359643068&spec=100&img_type=jpg"/>-->
          <img
              :src="qqAvatar?qqAvatar:userInfo.qqAvatar?userInfo.qqAvatar:userInfo.avatarUrl?userInfo.avatarUrl:'https://fastly.jsdelivr.net/gh/Nyanekoplus/js@master/data/nologin.png'">
          <div class="socila-check qq-check"><i class="fa fa-qq" aria-hidden="true"></i></div>
          <div class="socila-check gravatar-check"><i class="fa fa-google" aria-hidden="true"></i></div>
        </div>
        <div class="popup cmt-popup cmt-author">
          <span class="popuptext" style="margin-left: -115px;width: 230px;">输入QQ号将自动拉取昵称和头像</span>
          <input @focus="showHint($event,true)" @blur="showHint($event,false);getQQInfo();"
                 type="text" placeholder="QQ/Github" id="author" v-model="qq" size="15"
                 autocomplete="off" tabindex="1" aria-required="true">
        </div>
        <div class="popup cmt-popup">
          <span class="popuptext" style="margin-left: -65px;width: 130px;">你将收到回复通知</span>
          <input @focus="showHint($event,true)" @blur="showHint($event,false)"
                 type="text" placeholder="邮箱" id="email" v-model="email" size="22" tabindex="1"
                 autocomplete="off" aria-required="true">
        </div>
        <div class="popup cmt-popup"><!-- onclick="cmt_showPopup(this)"-->
          <span class="popuptext" style="margin-left: -55px;width: 110px;">禁止小广告😀</span>
          <input @focus="showHint($event,true)" @blur="showHint($event,false)"
                 type="text" placeholder="Site /deprecated" id="url" v-model="site" size="22" autocomplete="off"
                 tabindex="1">
        </div>
      </div>
      <label class="siren-checkbox-label active">
        <input v-model="robotCheck" class="siren-checkbox-radio" type="checkbox" name="no-robot">
        <span class="siren-no-robot-checkbox siren-checkbox-radioInput"></span>
        滴，学生卡 | I'm not a robot
      </label>
      <!--
      <label class="siren-checkbox-label active">
        <input class="siren-checkbox-radio" type="checkbox" name="is-private">
        <span class="siren-is-private-checkbox siren-checkbox-radioInput"></span>
        悄悄话 | Comment in private
      </label>-->
      <input type="text" placeholder="QQ" name="new_field_qq" id="qq" value="" style="display:none" autocomplete="off">
      <div class="form-submit">
        <div id="auth-box" class="auth-box"><!-- onclick="cmt_showPopup(this)"-->
          <div :class="`auth-boxtext ${showAuth?'show':''}`">
            请填写验证码
            <input type="text" placeholder="Auth Code" v-model="emailAuthCode" size="6" required autocomplete="off" tabindex="1" style="border-color:white;">
            <button @click="emailAuthentication" type="button" style="width:120px;margin-top:2px;margin-right:1px;border-color:white;">Submit</button>
            <button @click="closeAuth" type="button" style="width:120px;margin-top:2px;margin-left:1px;border-color:white;">Cancel</button>
          </div>
        </div>
        <input id="submit" class="submit" @click="handleSubmit" value="发送评论" type="submit">
        <div class="insert-image-tips popup" @click="closeAuth"
             @mouseenter="$event.currentTarget.children[1].className='insert-img-popuptext show'"
             @mouseleave="$event.currentTarget.children[1].className='insert-img-popuptext'">
          <i class="fa fa-picture-o" aria-hidden="true"></i>
          <span class="insert-img-popuptext" id="uploadTipPopup">上传图片</span>
        </div>
        <!--
        <input id="upload-img-file" type="file" accept="image/*" multiple="multiple" class="insert-image-button">
        -->
        <input type="hidden" name="comment_post_ID" value="3137" id="comment_post_ID">
        <input type="hidden" name="comment_parent" id="comment_parent" value="0">
      </div>
      <p style="display: none;">
        <input type="hidden" id="akismet_comment_nonce" name="akismet_comment_nonce" value="7f8fe6e5d2">
      </p>
      <p style="display: none;"></p>
      <input type="hidden" id="ak_js" name="ak_js" value="1614604055274">
    </div>
  </div>
</template>

<script>
import {parseTime} from '@/utils/index'
import {mapMutations} from "vuex";
import {message} from "../Message";
import {authEmail, authToken, getQQInfo, sendAuthEmail} from "../../api/user";
import {removeCookie} from "../../utils/cookie";

export default {
  name: "box",
  data: () => ({
    showAuth: false,
    emojiPanelState: false,

    username: '',
    qqAvatar: null,

    qq: '',
    site: '',
    email: '',
    emailAuthCode: '',
    content: '',
    robotCheck: false,
    currentUser: null,
  }),
  methods: {
    ...mapMutations(['setToken','setLoginState','setUserInfo','removeToInfo', 'setCommentList']),
    closeAuth(){this.showAuth = false;},
    showHint(e,b){
      let t = e.target.parentElement.children[0].classList;
      //console.log(t.parentElement.children[0].classList)
      if (b) t.add('show')
      else t.remove('show')
    },
    getQQInfo(){
      let q = this.qq;
      console.log('qq:'+q)
      if (!q || q.length<7 || q.length > 14){
        message('一般人q号多长心里没点b树吗')
        return
      }
      let param = new URLSearchParams();
      param.append('qq',q);
      getQQInfo(param).then(response => {
        message(response.message)
        if (response.state === this.$STATE.SUCCESS){
          // this.setUserInfo(response.data) // bug 会导致vuex中的userid被修改，应等到验证完成再设置
          this.qqAvatar = response.data.qqAvatar
          this.qq = response.data.username
          this.email = response.data.email
        }
      })
    },
    processEmoji(text) {
      let name = text.substring(1,text.length-1);
      let src = this.tiebaEmojiMap[name]
      return `<img src="${src}" alt="${name}" class="wp-smiley" style="height: 1em; max-height: 1em;">`
    },
    addEmoji(e) {
      let name = e.target.alt
      this.content += ':' + name + ':'
    },
    emailAuthentication(){
      let c = this.emailAuthCode.trim();
      if (c.length !== 6){
        message('验证码长度不符')
        return
      }
      let param = {}
      param.code = c
      param.username = this.qq
      param.qqAvatar = this.qqAvatar
      authEmail(param).then(response => {
        message(response.message);
        if (response.state === this.$STATE.SUCCESS){
          this.closeAuth();
          let token = response.data
          this.setToken(token);
          authToken(token).then(response=>{
            if(response.state === this.$STATE.SUCCESS){
              this.setLoginState(true);
              this.setUserInfo(response.data); // TODO
              this.handleSubmit()
              console.log(response.data);
            }/*else { // 已登录的验证q号失败也不会登出
              this.setLoginState(false);
              removeCookie("token");
              console.log(response.data);
              message(response.message)
            }*/
          });
        }
      })
    },
    handleSubmit() {
      if (!this.robotCheck) {
        message("Are you a ROBOT?")
        return
      }
      if (this.content === '') {
        message("内容不能为空");
        return;
      }
      let userInfo = this.$store.getters.userInfo;
      if (!this.$store.getters.isLogin) {// Object.keys(userInfo).length === 0 userInfo === null || userInfo === undefined
        // \w表示匹配字母或数字或下划线，这里两个括号表示匹配仅包含字母或数字 和 字母、数字、下划线、‘-’
        let emReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (!emReg.test(this.email)){
          message('请输入有效的邮箱地址')
          return
        }
        let param = new URLSearchParams();
        param.append('email',this.email);
        sendAuthEmail(param).then(response=> {
          message(response.message);
          this.showAuth = true;
        })
        //message("已向输入的邮箱发送验证码");
        return;
      }
      let blogUid = this.blogUid//this.$store.getters.blog.uid;
      console.log('blogUid: ' + blogUid);
      let toInfo = this.$store.getters.toInfo;

      let param = {};
      //param.blogUid = blogUid; // 在comment-index中
      //param.rootUid = blogId;
      param.userUid = userInfo.uid;
      console.log("替换后", this.content.replace(/:.*?:/g, this.processEmoji))
      param.content = this.content.replace(/:.*?:/g,this.processEmoji)
      param.type = 0; // 0 for comment
      if (toInfo !== null) { // is reply
        console.log('toInfoUid: ' + toInfo.toUid);
        param.toUid = toInfo.toUid;
        param.toUserUid = toInfo.toUserUid;
        //param.targetType = 1; // 回复评论
      } else {
        param.toUid = null;
        param.toUserUid = null;
        //param.targetType = 0;
      }
      param.createTime = parseTime("YYYY-mm-dd HH:MM:SS", new Date());
      this.robotCheck = false;
      this.content = '';
      this.cancelReply();
      this.$emit('submit-comment', param);
    },
    cancelReply() {
      let origin_pos = document.getElementById('comments');
      //let box = e.currentTarget.parentNode.parentNode.parentNode;
      let box = document.getElementById('respond');
      box.children[0].children[0].children[0].style = 'display: none;';
      this.removeToInfo();
      origin_pos.appendChild(box);
    },

  },
  beforeUpdate() {
    //TODO
    //this.qq = this.$store.getters.userInfo.username;
    //this.email = this.$store.getters.userInfo.email;
  },
  created() {
    this.qq = this.$store.getters.userInfo.username;
    this.email = this.$store.getters.userInfo.email;
  },
  mounted() {
  },
  computed: {
    userInfo(){
      return this.$store.getters.userInfo;
    },
    tiebaEmojiMap(){
      return this.$store.getters.tiebaEmoji
    }
  }
}
</script>

<style scoped>
.comment-respond button {
  width: calc(98% - 46px);
  margin: 0;
  padding: 15px 25px;
  text-transform: none;
  color: #535a63;
  background: 0 0;
  border-right: 0;
  -webkit-transition: all .1s ease-out;
  -moz-transition: all .1s ease-out;
  transition: all .1s ease-out;
  box-shadow: none;
  border: 1px solid #ccc;
  text-shadow: none;
}

.auth-box {
  position: absolute;
  display: inline-block;
  left: 41%;
  cursor: pointer
}
.auth-box .auth-boxtext {
  visibility: hidden;
  width: 250px;
  background-color: #ffcccc;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 3px;
  position: absolute;
  z-index: 1;
  bottom: 10px;
  left: 50%;
  /*margin-left: -80px*/
}

.auth-box .auth-boxtext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #ffcccc transparent transparent transparent
}
.auth-box .show {
  visibility: visible;
  -webkit-animation: fadeIn 1s;
  animation: fadeIn 1s
}
/*模态框*/
.modal {
  /*display: block; /* 默认隐藏 */
  position: fixed; /* 根据浏览器定位 */
  z-index: 1; /* 放在顶部 */
  left: 0;
  top: 0;
  width: 100%; /* 全宽 */
  height: 100%; /* 全高 */
  overflow: auto; /* 允许滚动 */
  background-color: rgba(0,0,0,0.4); /* 背景色 */
}
/*模态框内容*/
.modal-content {
  display: flex; /*采用flexbox布局*/
  flex-direction: column; /*垂直排列*/
  position: relative;
  background-color: #fefefe;
  margin: 15% auto; /*距顶部15% 水平居中*/
  padding: 20px;
  border: 1px solid #888;
  width: 20%;
  animation: topDown 0.4s; /*自定义动画，从模态框内容上到下出现*/
}
@keyframes topDown {
  from {top: -300px; opacity: 0}
  to {top: 0; opacity: 1}
}
/*模态框头部*/
.modal-header {
  display: flex; /*采用flexbox布局*/
  flex-direction: row; /*水平布局*/
  align-items: center; /*内容垂直居中*/
  justify-content: space-between;
}
/*关闭X 样式*/
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
