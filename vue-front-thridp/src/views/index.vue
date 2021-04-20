<template>
  <div>

    <!--
    <div class="tag-container">
      <div class="sticky_layout">
        <div class="card-widget card-categories">
          <div class="card-content">
            <div class="item-headline"><i class="fas fa-folder-open"></i><span>分类</span></div>
            <ul class="card-category-list" id="aside-cat-list">
              <li class="card-category-list-item parent"><a class="card-category-list-link"
                                                            href="/categories/%E4%B8%AA%E4%BA%BA%E9%A1%B9%E7%9B%AE/"
                                                            data-pjax-state=""><span
                  class="card-category-list-name">个人项目</span><span class="card-category-list-count"> 2</span><i
                  class="fas fa-caret-left "></i></a></li>
              <ul class="card-category-list child">
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E4%B8%AA%E4%BA%BA%E9%A1%B9%E7%9B%AE/%E4%BD%9C%E4%B8%9A%E4%BA%92%E8%AF%84-python/"
                                                        data-pjax-state=""><span class="card-category-list-name">作业互评 (python)</span><span
                    class="card-category-list-count">1</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E4%B8%AA%E4%BA%BA%E9%A1%B9%E7%9B%AE/%E5%A4%A9%E6%B0%94%E6%8E%A8%E9%80%81-python/"
                                                        data-pjax-state=""><span class="card-category-list-name">天气推送 (python)</span><span
                    class="card-category-list-count">1</span></a></li>
              </ul>
              <li class="card-category-list-item parent"><a class="card-category-list-link"
                                                            href="/categories/%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B/"
                                                            data-pjax-state=""><span
                  class="card-category-list-name">使用教程</span><span class="card-category-list-count"> 1</span><i
                  class="fas fa-caret-left "></i></a></li>
              <ul class="card-category-list child">
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B/WebSSH/"
                                                        data-pjax-state=""><span
                    class="card-category-list-name">WebSSH</span><span class="card-category-list-count">1</span></a></li>
              </ul>
              <li class="card-category-list-item parent"><a class="card-category-list-link"
                                                            href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/"
                                                            data-pjax-state=""><span
                  class="card-category-list-name">博客搭建</span><span class="card-category-list-count"> 7</span><i
                  class="fas fa-caret-left "></i></a></li>
              <ul class="card-category-list child">
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/CDN/"
                                                        data-pjax-state=""><span
                    class="card-category-list-name">CDN</span><span class="card-category-list-count">1</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/Zeit-co/"
                                                        data-pjax-state=""><span
                    class="card-category-list-name">Zeit.co</span><span class="card-category-list-count">1</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/%E5%9B%BE%E5%BA%8A/"
                                                        data-pjax-state=""><span
                    class="card-category-list-name">图床</span><span class="card-category-list-count"> 2</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/%E6%89%93%E8%B5%8F%E7%B3%BB%E7%BB%9F/"
                                                        data-pjax-state=""><span class="card-category-list-name">打赏系统</span><span
                    class="card-category-list-count"> 1</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/%E6%8F%92%E4%BB%B6/"
                                                        data-pjax-state=""><span
                    class="card-category-list-name">插件</span><span class="card-category-list-count"> 1</span></a></li>
                <li class="card-category-list-item "><a class="card-category-list-link"
                                                        href="/categories/%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA/%E8%83%8C%E6%99%AF%E9%9F%B3%E4%B9%90/"
                                                        data-pjax-state=""><span class="card-category-list-name">背景音乐</span><span
                    class="card-category-list-count"> 1</span></a></li>
              </ul>
              <li class="card-category-list-item parent"><a class="card-category-list-link"
                                                            href="/categories/%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/"
                                                            data-pjax-state=""><span
                  class="card-category-list-name">学习笔记</span><span class="card-category-list-count"> 22</span><i
                  class="fas fa-caret-left "></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    -->
    <div class="tag-cloud-container">
      <div class="tag-cloud">
        <ol class="tag-cloud-list ">
          <li class="tag-cloud-list-item"><a href="#tag-cloud-head-1" class="tag-cloud-link node-name--H2  is-active-link">I. 特性</a>
            <ol class="tag-cloud-list  is-collapsible">
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-2" class="tag-cloud-link node-name--H3 ">随机封面图</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-3" class="tag-cloud-link node-name--H3 ">Lazyload</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-4" class="tag-cloud-link node-name--H3 ">文章封面视频</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-5" class="tag-cloud-link node-name--H3 ">定制登陆及后台界面</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-6" class="tag-cloud-link node-name--H3 ">支持 Bilibili 表情</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-7" class="tag-cloud-link node-name--H3 ">QQ/Gravatar 头像</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-8" class="tag-cloud-link node-name--H3 ">评论插图</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-9" class="tag-cloud-link node-name--H3 ">用户 UA 及 IP 定位</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-10" class="tag-cloud-link node-name--H3 ">文章目录</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-11" class="tag-cloud-link node-name--H3 ">Mac 风格代码块</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-12" class="tag-cloud-link node-name--H3 ">前端主题切换</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-13" class="tag-cloud-link node-name--H3 ">时光轴</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-14" class="tag-cloud-link node-name--H3 ">相册</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-15" class="tag-cloud-link node-name--H3 ">邮件模板</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-16" class="tag-cloud-link node-name--H3 ">Aplayer/HermitX 支持</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-17" class="tag-cloud-link node-name--H3 ">CDN 优化</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-18" class="tag-cloud-link node-name--H3 ">Github Cards</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-19" class="tag-cloud-link node-name--H3 ">移动客户端*</a></li>
            </ol>
          </li>
          <li class="tag-cloud-list-item"><a href="#tag-cloud-head-20" class="tag-cloud-link node-name--H2 ">II. 注意事项</a>
            <ol class="tag-cloud-list  is-collapsible is-collapsed">
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-21" class="tag-cloud-link node-name--H3 ">重要★★：</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-22" class="tag-cloud-link node-name--H3 ">建议安装的插件☆</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-23" class="tag-cloud-link node-name--H3 ">我也在使用的插件</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-24" class="tag-cloud-link node-name--H3 ">兼容性</a>
                <ol class="tag-cloud-list  is-collapsible is-collapsed">
                  <li class="tag-cloud-list-item"><a href="#tag-cloud-head-25" class="tag-cloud-link node-name--H4 ">服务器端</a></li>
                  <li class="tag-cloud-list-item"><a href="#tag-cloud-head-26" class="tag-cloud-link node-name--H4 ">前端</a></li>
                </ol>
              </li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-27" class="tag-cloud-link node-name--H3 ">写作规范</a></li>
              <li class="tag-cloud-list-item"><a href="#tag-cloud-head-28" class="tag-cloud-link node-name--H3 ">API</a></li>
            </ol>
          </li>
          <li class="tag-cloud-list-item"><a href="#tag-cloud-head-29" class="tag-cloud-link node-name--H2 ">III. FAQ</a></li>
          <li class="tag-cloud-list-item"><a href="#tag-cloud-head-30" class="tag-cloud-link node-name--H2 ">IV. 下载</a></li>
        </ol>
      </div>
    </div>
    <div class="notice" style="margin-top:60px">
      <i class="iconfont icon-notification"></i>
      <div class="notice-content">思绪如风，来得快去得也快，偶尔在这里停留  ~&gt;ω&lt;</div>
    </div>
    <top-list/>
    <main-list :categoryUid="'1'"></main-list>

  </div>


</template>

<script>
import {visitRecord} from "../api/article";

export default {
  name: "index",
  components: {
    TopList: () => import('@/components/TopList'),
    MainList: () => import('@/components/List'),
  },
  data: () => ({

  }),
  created() {
    visitRecord();
  }
}
</script>

<style scoped>
.tag-cloud-container {
  z-index: 98;
  width: 200px;
  height: 100%;
  background-color: rgba(255, 255, 255, 0);
  transform: translateX(0);
  right: calc((100% - 950px - 250px)/ 2);
  position: absolute !important;
  top: 100%;
  position: absolute;
  padding-top: 10px;
  padding-bottom: 10px
}

.tag-cloud {
  position: -webkit-sticky;
  position: sticky;
  top: 100px
}

@media (max-width:860px) {
  .tag-cloud-container {
    display: none
  }
}

.tag-cloud {
  overflow-y: auto
}

.tag-cloud>.tag-cloud-list {
  overflow: hidden;
  position: relative
}

.tag-cloud>.tag-cloud-list li {
  list-style: none
}

.tag-cloud-list {
  margin: 0;
  padding-left: 10px
}

a.tag-cloud-link {
  color: currentColor;
  height: 100%
}

.tag-cloud-link::before {
  background-color: #EEE;
  content: ' ';
  display: inline-block;
  height: inherit;
  left: 0;
  margin-top: -1px;
  position: absolute;
  width: 2px
}
h1[id*=tag-cloud-head]::before,h2[id*=tag-cloud-head]::before,h3[id*=tag-cloud-head]::before,h4[id*=tag-cloud-head]::before,h5[id*=tag-cloud-head]::before,h6[id*=tag-cloud-head]::before {
  display:block;
  content:" ";
  height:80px;
  margin-top:-80px;
  visibility:hidden
}

.sticky_layout {
  position: sticky;
  position: -webkit-sticky;
  top: 20px;
}
.card-widget, #recent-posts > .recent-post-item, .layout_page > div:first-child:not(.recent-posts), .layout_post > #page, .layout_post > #post, .read-mode .layout_post > #post {
  background: var(--light_bg_color);
}
.card-widget {
  position: relative;
  overflow: hidden;
  margin-top: 1rem;
  border-radius: 8px;
  background: var(--card-bg);
  -webkit-box-shadow: 0 4px 8px 6px rgba(7,17,27,0.06);
  box-shadow: 0 4px 8px 6px
  rgba(7,17,27,0.06);
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -o-transition: all 0.3s;
  -ms-transition: all 0.3s;
  transition: all 0.3s;
}
.card-content {
  padding: 1rem 1.2rem;
}
.item-headline {
  padding-bottom: 0.3rem;
  font-size: 1.2em;
}
.aside-list > .aside-list-item:not(:last-child) {
  border-bottom: 1px dashed
  #f5f5f5;
}
.aside-list > .aside-list-item:first-child {
  padding-top: 0;
}
.aside-list > .aside-list-item {
  display: -webkit-box;
  display: -moz-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: box;
  display: flex;
  -webkit-box-align: center;
  -moz-box-align: center;
  -o-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  align-items: center;
  padding: 0.3rem 0;
  padding-top: 0.3rem;
}
.aside-list > .aside-list-item .content {
  -webkit-box-flex: 1;
  -moz-box-flex: 1;
  -o-box-flex: 1;
  box-flex: 1;
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  padding-left: 10px;
  word-break: break-all;
}
</style>