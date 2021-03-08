<template>
  <div>
    <ul :class="isChild?'children':'commentwrap'" v-for="comment in commentList" :key="comment.id">
      <li :class="liClass" id="comment-8435">
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
                        <span class="bb-comment isauthor" title="博主">博主</span> {{ comment.userName }}
                        <span class="showGrade0" title="萌萌哒新人~">
                    <img
                        src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/level/level_0.svg"
                        style="height: 1.5em; max-height: 1.5em; display: inline-block;">
                  </span>
                      </a>
                    </h4>
                  </div>
                  <a rel="nofollow" class="comment-reply-link" data-commentid="8435" data-postid="3137"
                     data-belowelement="comment-8435" data-respondelement="respond" aria-label="Reply to センカメイ" @click="reply($event)">Reply</a>
                  <div class="right">
                    <div class="info">
                      <time datetime="2021-02-27">发布于 1 天前</time>&nbsp;&nbsp;
                      <span class="useragent-info">(
                  <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">
                  &nbsp;Google Chrome 87.0.4280.88&nbsp;&nbsp;
                  <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg">
                  &nbsp;Windows 10 )
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
              <div class="body"><p>···<br> {{ comment.content }}<br> ···</p></div>
            </div>
            <div class="arrow-left"></div>
          </div>

        </div>
        <hr>
        <patch :comment-list="comment.replyList" :is-child="true"></patch>
        <!--
        <ul class="children">
          <li class="comment byuser comment-author-mashiro bypostauthor even depth-2" id="comment-1638">
            <div class="contents">
              <div class="comment-arrow">
                <div class="main shadow">
                  <div class="profile">
                    <a href="javascript: return false;" rel="nofollow">
                      <img alt="Mashiro"
                           src="https://gravatar.loli.net/avatar/cd2b3a164c977539712929f66cad335c?s=80&amp;d=mm&amp;r=g"
                           onerror="imgError(this,1)"
                           data-src="https://gravatar.loli.net/avatar/cd2b3a164c977539712929f66cad335c?s=80&amp;d=mm&amp;r=g"
                           srcset="https://gravatar.loli.net/avatar/cd2b3a164c977539712929f66cad335c?s=160&amp;d=mm&amp;r=g 2x"
                           class="avatar avatar-80 photo lazyload"
                           width="80" height="80">
                    </a>
                  </div>
                  <div class="commentinfo">
                    <section class="commeta">
                      <div class="left">
                        <h4 class="author">
                          <a href="javascript: return false;" rel="nofollow">
                            <img
                                alt="Mashiro"
                                src="https://gravatar.loli.net/avatar/cd2b3a164c977539712929f66cad335c?s=24&amp;d=mm&amp;r=g"
                                srcset="https://gravatar.loli.net/avatar/cd2b3a164c977539712929f66cad335c?s=48&amp;d=mm&amp;r=g 2x"
                                class="avatar avatar-24 photo" width="24" height="24">
                            <span class="bb-comment isauthor" title="博主">博主</span> Mashiro
                            <span class="showGrade6" title="德国骨科">
                          <img
                              src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/level/level_6.svg"
                              style="height: 1.5em; max-height: 1.5em; display: inline-block;">
                        </span>
                          </a>
                        </h4>
                      </div>
                      <a rel="nofollow" class="comment-reply-link" href="#comment-1638" data-commentid="1638"
                         data-postid="3137" data-belowelement="comment-1638" data-respondelement="respond"
                         aria-label="Reply to Mashiro">Reply</a>
                      <div class="right">
                        <div class="info">
                          <time datetime="2018-06-10">发布于 2018-06-10 02:06</time>&nbsp;&nbsp;<span class="useragent-info">( <img
                            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">&nbsp;Google Chrome 66.0.3359.181&nbsp;&nbsp;<img
                            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg">&nbsp;Windows 10 )</span><span
                            class="useragent-info-m">( <img
                            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/chrome.svg">&nbsp;&nbsp;<img
                            src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.4.5/img/Sakura/images/ua/svg/windows_win10.svg"> )</span>&nbsp;来自:
                          上海市 移动
                        </div>
                      </div>
                    </section>
                  </div>
                  <div class="body">
                    <p>
                      <a href="https://api.2heng.xin/redirect/?url=#comment-1624" class="comment-at">@mikusa</a>&nbsp;
                      <span class="emotion-inline emotion-item" style="margin-left: 0px;margin-right: 0px;">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@2.9.4/img/bili/xieyanxiao.png" class="img" style="/*background-image: url();*/
                     animation-duration: 320ms;
                     animation-timing-function: steps(8);
                     transform: translateY(-224px);
                     height: 256px;
                     ">
                  </span>
                    </p>
                  </div>
                </div>
                <div class="arrow-left"></div>
              </div>
            </div>
            <hr>
          </li>
        </ul>
        -->
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "patch",
  props: {
    commentList: {
      type: Array
    },
    isChild: {
      type: Boolean
    },
  },
  data: () => ({
    liClass: 'comment even thread-even depth-1'
  }),
  methods: {
    reply(e){
      let box = document.getElementById('respond');
      let current = e.currentTarget.parentNode.parentNode.parentNode;
      console.log('current: '+current);

      /*
      let divTemp = document.createElement("div"), nodes = null
          // 文档片段，一次性append，提高性能
          , fragment = document.createDocumentFragment(); //href="/theme-sakura/#respond"
      divTemp.innerHTML = '<h3 id="reply-title" class="comment-reply-title"> <small><a rel="nofollow" id="cancel-comment-reply-link" @click="cancelReply($event)" style="">Cancel Reply</a></small></h3>';
      nodes = divTemp.childNodes;
      for (let i=0, length=nodes.length; i<length; i+=1) {
        fragment.appendChild(nodes[i].cloneNode(true));
      }
      current.appendChild(fragment);
      // 据说下面这样子世界会更清净
      nodes = null;
      fragment = null;
      */
      box.children[0].children[0].children[0].style = '';
      current.appendChild(box);

    },
  },
  watch: {
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