<template>
  <!-- 标签 -->
  <div>
    <cover title="标签云"/>
    <div id="content" class="site-content">
      <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
          <article id="post-202" class="post-202 post type-post status-publish format-standard hentry category-uncategorized tag-bilibili tag-3 tag-5 tag-4">
            <header class="entry-header">
              <h1 class="entry-title">使用腾讯云CDN解除哔哩哔哩番剧区域限制</h1>
              <p class="entry-census">发布于 2020-12-20&nbsp;&nbsp;12589 次阅读 </p>
              <hr>
            </header>
            <ul class="cloud" role="navigation" aria-label="Webdev tag cloud">
              <li v-for="tag in tagList" :id="tag.uid">
                <a :data-weight="tag.weight" :href="tag.name">{{ tag.name }}</a>
              </li>

            </ul>
            <!--
<li><a data-weight="2" href="/tag/ember">Ember</a></li>
<li><a data-weight="5" href="/tag/sass">Sass</a></li>
<li><a data-weight="8" href="/tag/html">HTML</a></li>
<li><a data-weight="6" href="/tag/flexbox">FlexBox</a></li>
<li><a data-weight="4" href="/tag/api">API</a></li>
<li><a data-weight="5" href="/tag/vuejs">VueJS</a></li>
<li><a data-weight="6" href="/tag/grid">Grid</a></li>
<li><a data-weight="3" href="/tag/rest">Rest</a></li>
<li><a data-weight="9" href="/tag/javascript">JavaScript</a></li>
<li><a data-weight="3" href="/tag/animation">Animation</a></li>
<li><a data-weight="7" href="/tag/react">React</a></li>
<li><a data-weight="8" href="/tag/css">CSS</a></li>
<li><a data-weight="1" href="/tag/cache">Cache</a></li>
<li><a data-weight="3" href="/tag/less">Less</a></li>
<li><a data-weight="4" href="/tag/http">HTTP</a></li>
<li><a data-weight="2" href="/tag/ember">Ember</a></li>
<li><a data-weight="5" href="/tag/sass">Sass</a></li>
<li><a data-weight="8" href="/tag/html">HTML</a></li>
<li><a data-weight="6" href="/tag/flexbox">FlexBox</a></li>
<li><a data-weight="4" href="/tag/api">API</a></li>
<li><a data-weight="5" href="/tag/vuejs">VueJS</a></li>
<li><a data-weight="6" href="/tag/grid">Grid</a></li>
<li><a data-weight="3" href="/tag/rest">Rest</a></li>
<li><a data-weight="9" href="/tag/javascript">JavaScript</a></li>
<li><a data-weight="3" href="/tag/animation">Animation</a></li>
<li><a data-weight="7" href="/tag/react">React</a></li>
<li><a data-weight="8" href="/tag/css">CSS</a></li>
<li><a data-weight="1" href="/tag/cache">Cache</a></li>
<li><a data-weight="3" href="/tag/less">Less</a></li>
<li><a data-weight="4" href="/tag/http">HTTP</a></li>
<li><a data-weight="2" href="/tag/ember">Ember</a></li>
<li><a data-weight="5" href="/tag/sass">Sass</a></li>
<li><a data-weight="8" href="/tag/html">HTML</a></li>
<li><a data-weight="6" href="/tag/flexbox">FlexBox</a></li>
<li><a data-weight="4" href="/tag/api">API</a></li>
<li><a data-weight="5" href="/tag/vuejs">VueJS</a></li>
<li><a data-weight="6" href="/tag/grid">Grid</a></li>
<li><a data-weight="3" href="/tag/rest">Rest</a></li>
<li><a data-weight="9" href="/tag/javascript">JavaScript</a></li>
<li><a data-weight="3" href="/tag/animation">Animation</a></li>
<li><a data-weight="7" href="/tag/react">React</a></li>
<li><a data-weight="8" href="/tag/css">CSS</a></li>
<li><a data-weight="1" href="/tag/cache">Cache</a></li>
<li><a data-weight="3" href="/tag/less">Less</a></li>
-->
          </article>

        </main>
      </div>
    </div>
  </div>
</template>

<script>
import {getTagList} from "../api/tag";

export default {
  name: "tag",
  components: {
    Cover: () => import('@/layouts/sora/widgets/Cover'),
  },
  data: () => ({
    tagList: [],
  }),
  methods: () => ({

  }),
  created() {
    getTagList().then(response=>{
      if (response.state === this.$STATE.SUCCESS){
        this.tagList = response.data;
        console.log(response.data);
      }else {
        alert(response.message);
        alert('获取标签列表失败');
      }
    })
  }
}
</script>

<style scoped>
.site-content {
  height: 81.00347vh;
  max-width: 800px;
  padding: 0 10px;
  margin-left: auto;
  margin-right: auto;
  background-color: rgba(255, 255, 255, .8)
}

</style>