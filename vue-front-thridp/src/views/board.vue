<template><!-- 留言板 -->
  <div>
    <cover
        title="留言板"
        cover-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/board.jpg"
    />
    <comment-block :blog-id="0" :comment-source="'BOARD'" :enable-comment="true"></comment-block><!--:enable-comment="blog.enableComment" -->
  </div>
</template>

<script>
import {mapMutations} from "vuex";
//import {getBlogByTitle} from "../api/article";

export default {
  name: "board",
  components: {
    Cover: () => import('@/layouts/sora/widgets/Cover'),
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({}),
  methods: {
    ...mapMutations(['setBlog', 'removeToInfo']),

  },
  created() {
  },
  mounted() {
    this.removeToInfo();
  }
}
</script>

<style scoped>

</style>