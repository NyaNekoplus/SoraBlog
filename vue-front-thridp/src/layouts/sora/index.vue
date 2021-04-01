<template>
  <div>
    <section id="main-container" :class="sidebarOn?'open':''">
      <div class="headertop filter-dot headertop-bar" style="height: auto;">
        <div id="banner_wave_1" class="banner_wave_hide_fit_skin" style="left: -1233px;"></div>
        <div id="banner_wave_2" class="banner_wave_hide_fit_skin" style="left: -1009px;"></div>

        <figure id="centerbg" class="centerbg"><!-- style="height: 938px;"-->
          <!--
          <div class="focusinfo">
            <div class="header-tou"><a href="https://www.lovewx.club"><img src="https://q1.qlogo.cn/g?b=qq&amp;nk=2808694449&amp;s=5"></a></div>
            <div class="header-info">
              <p></p>
              <div class="top-social_v2">
                <li id="bg-pre"><img class="flipx" src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg"></li>
                <li><a href="https://github.com/MoeACG-Xyrh" target="_blank" class="social-github" title="github"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/github.png"></a></li>

                <li><a href="https://weibo.com/lovewx99" target="_blank" class="social-sina" title="sina"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/sina.png"></a></li>
                <li><a href="https://t.me/moexyrh" target="_blank" class="social-lofter" title="telegram"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/telegram.svg"></a></li>

                <li class="qq"><a href="tencent://message/?uin=2808694449" title="Initiate chat ?"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/qq.png"></a></li>



                <li><a href="http://space.bilibili.com/67428761" target="_blank" class="social-bili" title="bilibili"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/bilibili.png"></a></li>
                <li><a href="https://music.163.com/#/user/home?id=472641677" target="_blank" class="social-wangyiyun" title="CloudMusic"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/wangyiyun.png"></a></li>
                <li><a href="https://twitter.com/moeacg_xyrh" target="_blank" class="social-wangyiyun" title="Twitter"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/twitter.png"></a></li>




                <li><a onclick="mail_me()" class="social-wangyiyun" title="E-mail"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/email.svg"></a></li>

                <li id="bg-next"><img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg"></li>
              </div>
            </div>
          </div>
          -->


          <div class="focusinfo no-select">
            <div class="header-tou">
              <a href="https://www.lovewx.club"><img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png"></a>
            </div>
            <!--<h1 class="center-text glitch is-glitching Ubuntu-font" data-text="遠い空へ">遠い空へ</h1>-->
            <div class="header-info">
              <p><i class="fa fa-quote-left"></i> 思绪如风，来得快去得也快，偶尔在这里停留 <i class="fa fa-quote-right"></i></p>
              <div class="top-social_v2">
                <li id="bg-pre">
                  <img class="flipx" src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg">
                </li>
                <li>
                  <a href="http://github.com/mashirozx" target="_blank" class="social-github" title="github">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/github.png">
                  </a>
                </li>
                <li>
                  <a href="http://weibo.com/mashirozx?is_all=1" target="_blank" class="social-sina" title="sina">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/sina.png">
                  </a>
                </li>
                <li>
                  <a href="https://t.me/SakurasoNoMashiro" target="_blank" class="social-lofter" title="telegram">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/telegram.svg">
                  </a>
                </li>
                <li>
                  <a href="https://music.163.com/m/user/home?id=2655217" target="_blank" class="social-wangyiyun"
                     title="CloudMusic">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/wangyiyun.png"></a>
                </li>
                <li>
                  <a href="https://twitter.com/2hengxin" target="_blank" class="social-wangyiyun" title="Twitter">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/twitter.png"></a>
                </li>
                <li>
                  <a onclick="mail_me()" class="social-wangyiyun" title="E-mail">
                    <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/email.svg"></a>
                </li>
                <li id="bg-next">
                  <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/next-b.svg">
                </li>
              </div>
            </div>
          </div>

        </figure>
        <div id="video-container" style="">
          <video id="bgvideo" class="video" video-name="" src="" preload="auto" width="auto"></video><!-- style="min-height: 1344px;"-->
          <div id="video-btn" class="loadvideo videolive"></div>
          <!--<div id="video-add"></div>-->
          <div class="video-stu"></div>
        </div>

        <div class="headertop-down faa-float animated" @click="headertop_down0">
          <span> <i class="fa fa-chevron-down" aria-hidden="true"></i> </span>
        </div>
      </div>

      <div id="page" class="site-wrapper"
           style="background-repeat: repeat;background-image: url(https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/star.png);">

        <sora-bar :is-index="true"/>

        <div class="blank" style="padding-top: 0px;"></div>


        <sora-view/>


        <sora-footer/>

        <div :class="`openNav no-select ${sidebarOn?'open sidebar-on':'sidebar-off'}`">
          <div class="iconflat no-select control-icon" @click="showSidebar">
            <div class="icon"></div>
          </div>
          <div class="site-branding">
            <h1 class="site-title"><a href="https://www.lovewx.club"></a>悠遠的蒼穹</h1>
          </div>
        </div> <!--在section内 -->



        <sora-setting/>

      </div>

    </section>


    <div id="mo-nav" :class="sidebarOn?'open':''">
      <div class="m-avatar"><img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png"></div>
      <p style="text-align: center; color: #333; font-weight: 900; font-family: 'Ubuntu', sans-serif; letter-spacing: 1.5px">
        Mashiro Sama...</p>
      <p style="text-align: center; word-spacing: 20px;">
        <a href="http://twitter.com/2hengxin" class="fa fa-twitter" target="_blank" style="color: #00aced"></a>
        <a href="http://weibo.com/2960337711" class="fa fa-weibo" target="_blank" style="color: #dd4b39"></a>
        <a href="http://github.com/mashirozx" class="fa fa-github" target="_blank" style="color: #333"></a>
        <a onclick="mail_me()" class="fa fa-envelope" style="color: #ffbf00"></a>
      </p>
      <div class="m-search">
        <form class="m-search-form" method="get" action="https://2heng.xin" role="search">
          <input class="m-search-input" type="search" name="s" placeholder="搜索..." required="">
        </form>
      </div>
      <ul id="menu-new-1" class="menu">
        <li class="current-menu-item">
          <a href="/" aria-current="page">
            <span class="faa-parent animated-hover"><i class="fa fa-fort-awesome faa-horizontal" aria-hidden="true"></i> 首页</span>
          </a>
        </li>
        <li>
          <a href="/time-series/">
                  <span class="faa-parent animated-hover">
                    <i class="fa fa-archive faa-shake" aria-hidden="true"></i> 归档
                  </span>
          </a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/archives/hacking/"><i class="icon-uni304F" aria-hidden="true"></i>极客</a></li>
            <li><a href="https://2heng.xin/archives/article/"><i class="fa fa-file-text-o" aria-hidden="true"></i>文章</a></li>
            <li><a href="https://2heng.xin/archives/review/"><i class="fa fa-quote-right" aria-hidden="true"></i>影评</a></li>
            <li><a href="https://2heng.xin/archives/thingking/"><i class="fa fa-commenting-o" aria-hidden="true"></i> 随想</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://mashiro.top/"><i class="fa fa-book" aria-hidden="true"></i>笔记</a></li>
          </ul>
        </li>
        <li><a><span class="faa-parent animated-hover"><i class="fa fa-list-ul faa-vertical" aria-hidden="true"></i> 清单</span></a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/book-list/"><i class="fa fa-th-list faa-bounce" aria-hidden="true"></i>书单</a></li>
            <li><a href="https://2heng.xin/bangumi/"><i class="fa fa-film faa-vertical" aria-hidden="true"></i>番组</a></li>
            <li><a href="https://2heng.xin/music/"><i class="fa fa-headphones" aria-hidden="true"></i> 歌单</a></li>
            <li><a href="https://2heng.xin/hearthstone-deck-index/"><i class="iconfont icon-Hearthstone" aria-hidden="true"></i> 卡组</a></li>
          </ul>
        </li>
        <li><a href="/board"><span class="faa-parent animated-hover"><i
            class="fa fa-pencil-square-o  faa-tada" aria-hidden="true"></i> 留言板</span></a></li>
        <li><a href="https://2heng.xin/friends/"><span class="faa-parent animated-hover"><i
            class="fa fa-link faa-shake" aria-hidden="true"></i> 友人帐</span></a></li>
        <li><a href="https://2heng.xin/donate/"><span class="faa-parent animated-hover"><i
            class="fa fa-heart faa-pulse" aria-hidden="true"></i> 赞赏</span></a></li>
        <li><a href="#"><span class="faa-parent animated-hover"><i class="fa fa-leaf faa-wrench" aria-hidden="true"></i> 关于</span></a>
          <ul class="sub-menu">
            <li><a href="https://2heng.xin/about/"><i class="fa fa-grav" aria-hidden="true"></i> 我？</a></li>
            <li><a href="https://2heng.xin/analytics/"><i class="fa fa-area-chart" aria-hidden="true"></i> 统计</a></li>
            <li><a href="https://2heng.xin/server-status/"><i class="fa fa-heartbeat" aria-hidden="true"></i>监控</a></li>
            <li><a href="https://2heng.xin/theme-sakura/"><i class="iconfont icon-sakura" aria-hidden="true"></i>主题</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://2heng.xin/sitemap_index.xml"><i class="fa fa-map-signs" aria-hidden="true"></i> MAP</a></li>
            <li><a target="_blank" rel="noopener noreferrer" href="https://2heng.xin/feed"><i class="fa fa-rss " aria-hidden="true"></i>RSS</a></li>
          </ul>
        </li>
        <li><a href="/tags"><span class="faa-parent animated-hover"><i class="fa fa-android faa-vertical" aria-hidden="true"></i> 标签</span></a></li>
        <li>
          <a target="_blank" rel="noopener noreferrer" href="https://hello.2heng.xin/about/more">
            <span class="faa-parent animated-hover"><i class="fa fa-tree faa-pulse" aria-hidden="true"></i> 时光轴</span>
          </a>
        </li>
      </ul>
      <p style="text-align: center; font-size: 13px; color: #b9b9b9;">© 2021 Vincent Tsai</p></div>
  </div>
</template>

<script>
export default {
  name: 'SoraLayout',
  components: {
    SoraView: () => import('./View'),
    SoraBar: () => import('./AppBar'),
    SoraFooter: () => import('./Footer'),
    SoraSetting: () => import('./Setting'),
  },
  data: () => ({
    coverHeight: 1440,
    sidebarOn: false,
  }),
  methods: {
    showSidebar(){
      this.sidebarOn = !this.sidebarOn;
    },
    headertop_down() {
      let b = document.querySelector(".site-content")
      //let b = document.body;
      console.log(b);
      this.startMove(b, {'scroll-margin-top': this.coverHeight});
      //var coverOffset=$('#content').offset().top;$('html,body').animate({scrollTop:coverOffset},600);}
    },
    headertop_down0() {
      console.log('滚动');
      //let pos = document.querySelector(".site-content").offsetTop; // 当前下部到顶端的距离
      let pos = document.documentElement.scrollTop;
      console.log('pos: ' + pos);
      let h = this.coverHeight
      let distance = h - pos;

      let step = 200;
      let part = distance / step;

      if (pos < h) {
        setTimeout(function () {
          smoothDown();
        }, 10);
      }

      function smoothDown() {
        console.log('down')
        if (pos < h) {
          pos += part;
          document.documentElement.scrollTop = pos
          setTimeout(smoothDown, 10 * Math.sin(Math.random()))
        }
      }
    },
    /*
    scrollBar(){
      if(document.body.clientWidth>860){
        window.scroll(function(){
          let s=$(window).scrollTop();
          let a=$(document).height();
          let b=$(window).height();
          let result=parseInt(s/(a-b)*100);$("#bar").css("width",result+"%");
        }
      }else {

      }
    }
    */
  },
  mounted() {
    this.coverHeight = window.innerHeight;
    //如果是手机，则不设置
    document.getElementById('centerbg').style.height = this.coverHeight.toString() + 'px';
    //this.coverHeight =
  }

}
</script>
<style>
.headertop-down {
  position: absolute;
  bottom: 50px;
  left: 50%;
  cursor: pointer;
  z-index: 5;
}
.headertop-down i {
  font-size: 28px;
  color:
      #fff;
  -ms-transform: scale(1.5,1);
  -webkit-transform: scale(1.5,1);
  transform: scale(1.5,1);
}
.centerbg{
  background-image: url('https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/landscape.jfif');
  background-position: center center;
  background-attachment: inherit;
}
</style>