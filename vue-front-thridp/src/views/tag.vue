<template>
  <div>
    <cover title="标签云"
           cover-src="https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.5/img/cover/tag.jpg"
    />
    <page-content>
      <article class="post type-post status-publish format-standard hentry category-uncategorized tag-bilibili tag-3 tag-5 tag-4">
        <!--
        <header class="entry-header">
          <h1 class="entry-title">使用腾讯云CDN解除哔哩哔哩番剧区域限制</h1>
          <p class="entry-census">发布于 2020-12-20&nbsp;&nbsp;12589 次阅读 </p>
          <hr>
        </header>
        -->
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
    </page-content>
  </div>
</template>

<script>
import {getTagList} from "../api/tag";
import {message} from "../components/Message";

export default {
  name: "tag",
  components: {
    PageContent: () => import('@/components/PageContent'),
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
      }else {message(response.message)
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

ul.cloud {
  list-style: none;
  padding-left: 0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  line-height: 2.5rem;
}
ul.cloud a {
  color: #a33;
  display: block;
  font-size: 0.15rem;
  padding: 0.0125rem 0.025rem;
  text-decoration: none;
  position: relative;
}
ul.cloud a[data-weight="1"] { --size: 1; }
ul.cloud a[data-weight="2"] { --size: 2; }
ul.cloud a[data-weight="3"] { --size: 3; }
ul.cloud a[data-weight="4"] { --size: 4; }
ul.cloud a[data-weight="5"] { --size: 5; }
ul.cloud a[data-weight="6"] { --size: 6; }
ul.cloud a[data-weight="7"] { --size: 7; }
ul.cloud a[data-weight="8"] { --size: 8; }
ul.cloud a[data-weight="9"] { --size: 9; }

ul.cloud a:focus {
  outline: 1px dashed;
}
ul.cloud a::before {
  content: "";
  position: absolute;
  top: 0;
  left: 50%;
  width: 0;
  height: 100%;
  background: var(--color);
  transform: translate(-50%, 0);
  opacity: 0.15;
  transition: width 0.25s;
}

ul.cloud a:focus::before,
ul.cloud a:hover::before {
  width: 100%;
}
ul.cloud a {
  --size: 4;
  font-size: calc(var(--size) * 0.025rem + 0.05rem);
  /* ... */
}

ul.cloud[data-show-value] a::after {
  content: " (" attr(data-weight) ")";
  font-size: 0.1rem;
}
ul.cloud li:nth-child(2n+1) a { --color: #181; }
ul.cloud li:nth-child(3n+1) a { --color: #33a; }
ul.cloud li:nth-child(4n+1) a { --color: #c38; }

@media (prefers-reduced-motion) {
  ul.cloud * {
    transition: none !important;
  }
}
</style>