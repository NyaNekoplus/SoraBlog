<template>
  <div class="top-feature-row">
    <h1 class="fes-title" style="font-family: 'Ubuntu', sans-serif;">
      <i class="fa fa-anchor" aria-hidden="true"></i> Zero: </h1>
    <!--
    <div class="top-feature-v2">
      <div class="the-feature square from_left_and_right">
        <a href="/theme-sakura/" target="_blank">
          <div class="img">
            <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.3.2/img/other/sakura.md.png">
          </div>
          <div class="info">
            <h3>{{ topBlogs[0].title }}</h3>
            <p>{{ topBlogs[0].coverUrl }}</p>
          </div>
        </a>
      </div>
    </div>
    <div class="top-feature-v2">
      <div class="the-feature square from_left_and_right" style="margin: 0 4.5px">
        <a href="/2017/09/19/pixiv/" target="_blank">
          <div class="img">
            <img src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.6/img/other/th%20(3).jpg">
          </div>
          <div class="info">
            <h3>Pixiv</h3>
            <p>P 站的正确打开方式</p>
          </div>
        </a>
      </div>
    </div>
    <div class="top-feature-v2">
      <div class="the-feature square from_left_and_right">
        <a href="https://ak.2heng.xin/" target="_blank">
          <div class="img">
            <img src="https://view.moezx.cc/images/2019/06/11/74751807_angel.jpg">
          </div>
          <div class="info">
            <h3>明日方舟</h3>
            <p>游戏主界面 H5 复刻</p></div>
        </a>
      </div>
    </div>
    -->
      <div class="top-feature-v2" v-for="(top,index) in topBlogs" :key="top.level" @click="intoDetail(top)">
        <div class="the-feature square from_left_and_right" :style="index===1?'margin: 0 4.5px':''">
          <a target="_blank">
            <div class="img">
              <img :src="top.coverUrl">
            </div>
            <div class="info">
              <h3>{{ top.title }}</h3>
              <!--<p>P 站的正确打开方式</p>-->
            </div>
          </a>
        </div>
      </div>

  </div>
</template>

<script>
import {mapMutations} from 'vuex'
import {getTopBlog} from "../api/article";

export default {
  name: "TopList",
  data: () => ({
    topBlogs: [],
  }),
  methods: {
    ...mapMutations(['setBlog']),
    intoDetail(blog){
      if (blog.link[0] !== '/'){
        blog.link = '/' + blog.link;
      }
      this.setBlog(blog);
      let routeData = this.$router.resolve({
        path: '/blog' + blog.link,
      });
      this.$router.push(routeData.href);
      //window.open(routeData.href, '_blank');
    },

  },
  created() {
    getTopBlog().then(response => {
      console.log('getTopBlog: '+ response.data.data);
      this.topBlogs = response.data;
    })
  }
}
</script>

<style scoped>

</style>