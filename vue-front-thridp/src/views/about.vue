<template>
  <div>
    <cover
        title="关于"
        cover-src="https://fastly.jsdelivr.net/gh/Nyanekoplus/js@master/data/about.png"
    />
    <page-content>
      <article class="page type-page status-publish has-post-thumbnail hentry">
        <div class="entry-content">
          <div v-html="about.detail"></div>
        </div>
      </article>
    </page-content>
    <comment-block :blog-id="0" :comment-source="'ABOUT'" :enable-comment="about.enableComment"></comment-block>
  </div>
</template>

<script>
import {getAboutMe} from "../api/user";

export default {
  name: "about",
  components: {
    PageContent: () => import('@/components/PageContent'),
    Cover: () => import('@/layouts/sora/widgets/Cover'),
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({
    about: {}
  }),
  created() {
    console.log('about created')
    getAboutMe().then(response => {
      if (response.state === this.$STATE.SUCCESS){
        this.about = response.data;
        //this.setAbout(this.about);
      }
    })
  }
}
</script>

<style scoped>

</style>
