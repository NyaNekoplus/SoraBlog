<template>
  <div>
    <cover
        :author="blog.author"
        :title="blog.title"
        :release-time="blog.createTime"
        :view-count="blog.viewCount"
        :comment-count="blog.commentCount"
        :tag-list="blog.tagList"
        :cover-src="blog.coverUrl"
        avatar-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png"
    />
    <page-content>
      <article class="post type-post status-publish format-standard has-post-thumbnail hentry category-hacking tag-graphql tag-javascript tag-wordpress">

        <div class="toc-container" :style="`height: ${tocHeight}px;`">
          <div class="toc" style="background: none;">
            <!--
            <ol class="toc-list ">
              <li class="toc-list-item"><a href="#toc-head-1" class="toc-link node-name--H2  is-active-link">I. 特性</a>
                <ol class="toc-list  is-collapsible">
                  <li class="toc-list-item"><a href="#toc-head-2" class="toc-link node-name--H3 ">随机封面图</a></li>
                  <li class="toc-list-item"><a href="#toc-head-3" class="toc-link node-name--H3 ">Lazyload</a></li>
                  <li class="toc-list-item"><a href="#toc-head-4" class="toc-link node-name--H3 ">文章封面视频</a></li>
                  <li class="toc-list-item"><a href="#toc-head-5" class="toc-link node-name--H3 ">定制登陆及后台界面</a></li>
                  <li class="toc-list-item"><a href="#toc-head-6" class="toc-link node-name--H3 ">支持 Bilibili 表情</a></li>
                  <li class="toc-list-item"><a href="#toc-head-7" class="toc-link node-name--H3 ">QQ/Gravatar 头像</a></li>
                  <li class="toc-list-item"><a href="#toc-head-8" class="toc-link node-name--H3 ">评论插图</a></li>
                  <li class="toc-list-item"><a href="#toc-head-9" class="toc-link node-name--H3 ">用户 UA 及 IP 定位</a></li>
                  <li class="toc-list-item"><a href="#toc-head-10" class="toc-link node-name--H3 ">文章目录</a></li>
                  <li class="toc-list-item"><a href="#toc-head-11" class="toc-link node-name--H3 ">Mac 风格代码块</a></li>
                  <li class="toc-list-item"><a href="#toc-head-12" class="toc-link node-name--H3 ">前端主题切换</a></li>
                  <li class="toc-list-item"><a href="#toc-head-13" class="toc-link node-name--H3 ">时光轴</a></li>
                  <li class="toc-list-item"><a href="#toc-head-14" class="toc-link node-name--H3 ">相册</a></li>
                  <li class="toc-list-item"><a href="#toc-head-15" class="toc-link node-name--H3 ">邮件模板</a></li>
                  <li class="toc-list-item"><a href="#toc-head-16" class="toc-link node-name--H3 ">Aplayer/HermitX 支持</a></li>
                  <li class="toc-list-item"><a href="#toc-head-17" class="toc-link node-name--H3 ">CDN 优化</a></li>
                  <li class="toc-list-item"><a href="#toc-head-18" class="toc-link node-name--H3 ">Github Cards</a></li>
                  <li class="toc-list-item"><a href="#toc-head-19" class="toc-link node-name--H3 ">移动客户端*</a></li>
                </ol>
              </li>
              <li class="toc-list-item"><a href="#toc-head-20" class="toc-link node-name--H2 ">II. 注意事项</a>
                <ol class="toc-list  is-collapsible is-collapsed">
                  <li class="toc-list-item"><a href="#toc-head-21" class="toc-link node-name--H3 ">重要★★：</a></li>
                  <li class="toc-list-item"><a href="#toc-head-22" class="toc-link node-name--H3 ">建议安装的插件☆</a></li>
                  <li class="toc-list-item"><a href="#toc-head-23" class="toc-link node-name--H3 ">我也在使用的插件</a></li>
                  <li class="toc-list-item"><a href="#toc-head-24" class="toc-link node-name--H3 ">兼容性</a>
                    <ol class="toc-list  is-collapsible is-collapsed">
                      <li class="toc-list-item"><a href="#toc-head-25" class="toc-link node-name--H4 ">服务器端</a></li>
                      <li class="toc-list-item"><a href="#toc-head-26" class="toc-link node-name--H4 ">前端</a></li>
                    </ol>
                  </li>
                  <li class="toc-list-item"><a href="#toc-head-27" class="toc-link node-name--H3 ">写作规范</a></li>
                  <li class="toc-list-item"><a href="#toc-head-28" class="toc-link node-name--H3 ">API</a></li>
                </ol>
              </li>
              <li class="toc-list-item"><a href="#toc-head-29" class="toc-link node-name--H2 ">III. FAQ</a></li>
              <li class="toc-list-item"><a href="#toc-head-30" class="toc-link node-name--H2 ">IV. 下载</a></li>
            </ol>
            -->
          </div>
        </div>
        <div id="entry-content" class="entry-content" ref="content" @click="enlargeImage">
          <div v-html="contentMD"></div>
        </div>

        <donate-icon/>

        <footer class="post-footer">
          <div class="post-lincenses">
            <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/deed.zh"
               target="_blank" rel="nofollow">
              <i class="fa fa-creative-commons" aria-hidden="true"></i>
            知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议
            </a>
          </div>
          <div class="post-tags">
            <i class="iconfont icon-tags"></i>
            <a v-for="tag in blog.tagList" :key="tag.uid" rel="tag">{{tag.name}}</a>
          </div>
          <div class="post-like">
            <a href="javascript:void(0);" @click="like" class="specsZan ">
              <i class="iconfont icon-like"></i>
              <span class="count"> {{ blog.likedCount }}</span>
            </a>
          </div>
          <div class="post-share">
            <!--
            <div class="social-share sharehidden share-component">
              <a class="social-share-icon icon-weibo"
                 href="http://service.weibo.com/share/share.php?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;pic=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;appkey="
                 target="_blank">
              </a>
              <a class="social-share-icon icon-qq"
                 href="http://connect.qq.com/widget/shareqq/index.html?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;source=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;desc=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;pics=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;summary=&quot;%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&quot;"
                target="_blank">
              </a>
              <a class="social-share-icon icon-wechat" href="javascript:">
                <div class="wechat-qrcode">
                  <h4>微信扫一扫：分享</h4>
                  <div class="qrcode" title="https://2heng.xin/2019/12/31/graphql-recursion/">
                    <canvas width="100" height="100" style="display: none;"></canvas>
                    <img alt="Scan me!" style="display: block;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAECklEQVR4nO2VQY7jQAwD/f9Pey+7QDboloqS7DgzFOCL3JZIVk/mOI7j7D7/Knr3WtG71bkJHeqMTEdXa/AYyCOBqKUGQQ12QiJeKlCredAykG8CMiFYhUWfwER6rqPtKs8GYiAG8lEgnTBVcFTHVXOvAm0gvwHIKvwMiPpEpWqjcyNfBgLCMhADmQeiCsoEEDNZrwomm0/3RJ7JO6rNQOCeyDN5R7X9B6TzRKYnezSkT2trPgYyrc1AfhKQ8+LqmN7NUoBUz6tzp8pANucfCSRaGomiQWS7xn4GNrvVi0GBqPvxT5aB7H3dBuQOE9U/9w7oju6qtkjH9iEfZiYMxEDKQVR0V7VFOlIgUWBqLxOizqVBkO9ocJ0d0XfphSdLDeQDQFRx0WIqTq3KDDkQ2FP10p6BGIiBSECo+KinCFWeSoXmxQtE8+iANpCnAamGH4lXQ+qcp9qIbuVCTIDevDcQAzGQHAgdTBarZrKeGlJ2qciFUC9mx/PbYyCr+hgQxfyUGRocmauapiGqAasZbXVEAw3EQAxE/Xg1ZCcyO796X905cZF289QiAIOegex2fAUQNbjqz9NqDjU48VOkAqnM3TwGks1QcmkDiQzKw8Twq7MqRedOAIl6qab3l6oZA2H+sKb3l6oZA2H+BE1aAJGJjpCJoheCAKnsUucayDcAiRZNmJ/odaoavqqNwkkvKxFCjBqIgaD6eiB0SfW7nTgaDjlH310FnvaWD11iIHHdCiQToJ7r/EmretVLEPWu1nae8Ccremcgc9rO00CeB0QNSTUd9TLTkwAnQ73yEhqIULcCoUuVRa/nBwVLu6ozVB0V0Jv9BpKdq+rOKgRChxHT6vmKaXWuWpk24m81D/QMpKKN+FvNAz0DqWgj/lbzQK/2u9cxPbHzKt1CcNtZlYvz8hhIpu0xQKpBZOcnTWfV+bmrZlO9qH/nGoiincw1EFHL1wKhy4kZ2lN1rN5H32Zz6a7onArk7TsDeRQQIrg0+N5blYYZAbxTdzrv/WWnDEQrA/kGIMSMGkhFiCA4PZ/1lD0df/ScgQh7bgeiiiMCVudpjxpQZ1Q9VS5B1d95ngZiIAaS6dTMqIKpuOwiTJmu7KjqreRmIE8DghUURUbnaI8GSHp0hhr0RB7HcRjI44CoAsiSChDybSdMdYbay3YJ+w0k02YgBqL/K+kEF52LTKy+pXOplyrobC71bCDg/EeBqCFVYEbiox4NjuxR9KuQKMBNz0A62gzEQDiQiihimoRFA6Yhqbqn8jCQnwykY1qdq+5Sq7IrmiGAM5BVGYg4V92l1mOAdARTIdHOq4Eo+5VLFe0UewZCtUfnop1ij4WnBlutCjgSunoJKHwCS8nPQJLzBvLLgfwBkdSMuSRKV6oAAAAASUVORK5CYII=">
                  </div>
                  <div class="help"><p>微信里点“发现”，扫一下</p>
                    <p>二维码便可将本文分享至朋友圈。</p></div>
                </div>
              </a>
              <a class="social-share-icon icon-tencent"
                   href="http://share.v.t.qq.com/index.php?c=share&amp;a=index&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;pic=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg"
                   target="_blank"></a>
              <a class="social-share-icon icon-douban"
                                          href="http://shuo.douban.com/!service/share?href=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;name=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;text=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;image=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;starid=0&amp;aid=0&amp;style=11"
                                          target="_blank"></a>
              <a class="social-share-icon icon-qzone"
                                                                 href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;desc=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;summary=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;site=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB"
                                                                 target="_blank"></a>
              <a
                class="social-share-icon icon-linkedin"
                href="http://www.linkedin.com/shareArticle?mini=true&amp;ro=true&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;summary=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;source=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;armin=armin"
                target="_blank"></a>
              <a class="social-share-icon icon-diandian"
                                       href="http://www.diandian.com/share?lo=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;ti=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;type=link"
                                       target="_blank"></a>
              <a class="social-share-icon icon-facebook"
                                                              href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F"
                                                              target="_blank"></a>
              <a
                class="social-share-icon icon-twitter"
                href="https://twitter.com/intent/tweet?text=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;via=https%3A%2F%2F2heng.xin"
                target="_blank"></a>
              <a class="social-share-icon icon-google"
                                       href="https://plus.google.com/share?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F"
                                       target="_blank"></a>
            </div>
            -->
            <i class="iconfont show-share icon-forward"></i>
          </div>
        </footer>
      </article>
      <!--
      <section class="post-squares nextprev">
        <div class="post-nepre full previous">
          <a href="https://2heng.xin/2019/09/18/fix-100-vh-in-mobile-browser/" rel="prev">
            <div class="background">
              <img class="lazyload"
                   data-src="https://2heng.xin/wp-content/uploads//2018/06/child-1024x576.jpg"
                   style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;"
                   src="https://2heng.xin/wp-content/uploads//2018/06/child-1024x576.jpg">
            </div>
            <span class="label">Previous Post</span>
            <div class="info"><h3>解决移动端浏览器 vh 单位异常问题</h3>
              <hr>
            </div>
          </a></div>
        <div class="post-nepre half next"></div>
      </section>
      <section class="post-squares nextprev">
        <div class="post-nepre half previous">
          <a href="https://yremp.live/spring-cloud-aibaba-sentinel-openfeign/" rel="prev">
            <div class="background lazyload"
                 style="background-image: url(&quot;/wp-content/uploads/2020/08/B144246C-2DE7-4BC1-A0FE-011385FC6436bf8d7-1-e1596867142294.png&quot;);"
                 data-src="/wp-content/uploads/2020/08/B144246C-2DE7-4BC1-A0FE-011385FC6436bf8d7-1-e1596867142294.png"></div>
            <span class="label">Previous Post</span>
            <div class="info"><h3>Spring Cloud Aibaba Sentinel服务熔断OpenFeign</h3>
              <hr>
            </div>
          </a>
        </div>
        <div class="post-nepre half next">
          <a href="https://yremp.live/bt-sites/" rel="next">
            <div class="background lazyload"
                 style="background-image: url(&quot;/wp-content/uploads/2020/12/6E808C9D-A183-41DF-9D6E-68E4AE0F0129-e1607945706154.png&quot;);"
                 data-src="/wp-content/uploads/2020/12/6E808C9D-A183-41DF-9D6E-68E4AE0F0129-e1607945706154.png"></div>
            <span class="label">Next Post</span>
            <div class="info"><h3>宝塔多站点域名解析问题</h3>
              <hr>
            </div>
          </a>
        </div>
      </section>
      -->
    </page-content>

    <comment-block v-if="blog.uid" :blog-id="blog.uid" :enable-comment="blog.enableComment" :comment-source="'BLOG'"></comment-block>
  </div>
</template>

<script>
import Viewer from "viewerjs";
import 'viewerjs/dist/viewer.css';
import * as tocbot from "tocbot";
import {getBlogByTitle, likedIncrement} from "../api/article";
import {mapMutations} from "vuex";
import PageContent from "../components/PageContent";
import {message} from "../components/Message";

export default {
  name: "page",
  components: {
    PageContent,
    DonateIcon: () => import('@/components/DonateIcon'),
    Cover: () => import('@/layouts/sora/widgets/Cover'),
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({
    tocHeight: 2800,
    contentMD: '',
    blog: [],
  }),
  methods: {
    ...mapMutations(['setBlog', 'removeToInfo']),
    enlargeImage(event) {
      //this.$nextTick(function (){})
      //console.log(event.target) // testssss
      //console.log(event.target.nodeName) // p
      let target = event.target;
      if (target.nodeName === 'IMG') {
        // 获取触发事件对象的属性
        const viewer = new Viewer(target, {
          inline: false,
        })
        viewer.view(0);
      }
    },
    getBlogByLink() {
      let t = this.$route.params.title
      if (t.length === 0){
        this.$router.push('/')
        return
      }
      getBlogByTitle(t).then(response => {
        console.log("page: " + response.state);
        if (response.state === this.$STATE.SUCCESS) {
          console.log("blog: " + response.data);
          this.blog = response.data;
          this.contentMD = response.data.contentMd;
          this.setBlog(response.data)
          //this.$nextTick(this.genToc)
        } else {
          this.$router.push('/404');
        }
      });
    },
    like(){
      if (this.$store.getters.userInfo === null){
        message("登录后才可以点赞哦~")
        return
      }
      let param = new URLSearchParams();
      param.append('uid',this.blog.uid);
      console.log("blog uid:"+param)
      likedIncrement(param).then(response => {
        message(response.message)
        if (response.state === this.$STATE.SUCCESS){
          this.blog.likedCount = this.blog.likedCount+1;
        }
      })
    },
    genToc: function () {
      tocbot.init({
        // Where to render the table of contents.
        tocSelector: '.toc', // 放置目录的容器
        // Where to grab the headings to build the table of contents.
        contentSelector: '.entry-content', // 正文内容所在
        // Which headings to grab inside of the contentSelector element.
        headingSelector: 'h1, h2, h3, h4, h5', // 需要索引的标题级别
        //positionFixedSelector: ".toc", //目录位置固定
        scrollSmooth: true,
        scrollSmoothOffset: -80,
        headingsOffset: -500,
        /*
        scrollEndCallback: function (e) { //回调函数
          window.scrollTo(window.scrollX, window.scrollY - 80);
          //修正滚动后页面的位置，80 是自己顶部栏的高度
        },
        */
        hasInnerContainers: true,
      });
      this.$nextTick(()=>{
        console.log('content height: '+this.$refs.content.offsetHeight)
        //console.log('toc height: '+this.$refs.toc.offsetHeight)
        this.tocHeight = this.$refs.content.offsetHeight
        //this.$refs.toc.style.height = 2800//this.$refs.content.offsetHeight
      })
    }
  },
  watch: {
    contentMD: function (){
      this.$nextTick(function (){
        // 服，自己加锚点得了
        var headerEl = 'h1,h2,h3,h4',  //headers
            content = '.entry-content',//文章容器
            idArr = {};  //标题数组以确定是否增加索引id//add #id
        let c = this.$refs.content.childNodes[0].childNodes//document.getElementById('entry-content').children
        console.log('children: '+c.toString())
        c.forEach(function (i) {
          //console.log('tagname: '+i.textContent)
          if (i.tagName==='H1'||i.tagName === 'H2'||i.tagName === 'H3'
              ||i.tagName === 'H4'||i.tagName === 'H5'){
            //去除空格以及多余标点
            var headerId = i.textContent.replace(/[\s|\~|`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\_|\+|\=|\||\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?|\：|\，|\。]/g, '');
            headerId = headerId.toLowerCase();
            if (idArr[headerId]) {
              //id已经存在
              i.id = headerId + '-' + idArr[headerId];
              //i.attr('id', headerId + '-' + idArr[headerId]);
              idArr[headerId]++;
            } else {
              //id未存在
              idArr[headerId] = 1;
              i.id = headerId
            }
            console.log('id: '+i.id)
          }
        });
        this.genToc()
      })
    }
  },
  created() {
    this.removeToInfo();

  },
  mounted() {
    //console.log('mount toc:'+this.$refs.content)
    //setTimeout(this.genToc,1000)
    this.getBlogByLink();
  },
  updated() {
    //this.genToc()
  },
  destroyed() {
    console.log('remove blog')
    tocbot.destroy()
    this.setBlog(null)
  }
}
</script>

<style scoped>

</style>