<template>
  <div>
    <cover title="分类归档" cover-src="https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.3/img/cover/category.png"/>
    <page-content>
      <article class="page type-page status-publish has-post-thumbnail hentry">
        <div class="entry-content">

          <div class="poem-wrap">
            <div class="poem-border poem-left"></div>
            <div class="poem-border poem-right"></div>
            <h1>念两句诗</h1>
            <!--及至归来无一事，始知空费草鞋钱-->
            <!--红桶凉席今犹在，我辈岂是打工人
                胸中有千言,下笔无一字
            -->
            <p id="poem">别看你今天闹得欢,小心今后拉清单,这都得应验的。
              不要干这种事情，头上三尺有神明，啊 一定要有敬畏之心</p>
            <p id="info">小熊维尼</p></div>

          <div class="links">
            <ul class="link-items fontSmooth">
              <!------------------------------技术 START---------------------------------->
              <li class="link-item">
                <a class="link-item-inner effect-apollo" href="https://sora.vin/Category/Tech" title="技术" target="_blank" rel="friend noopener noreferrer">
                  <!--<img class="lazyload" src="https://cdn.jsdelivr.net/gh/honjun/cdn@1.6/img/loader/orange.progress-bar-stripe-loader.svg" data-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/lizard.png">-->
                  <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/lizard.png">
                  <span class="sitename">技术</span>
                  <div class="linkdes">Technology</div>
                </a>
              </li>
              <!------------------------------END------------------------------------->

              <!------------------------------资源 START---------------------------------->
              <li class="link-item">
                <a class="link-item-inner effect-apollo" href="https://sora.vin/Category/Life" title="生活" target="_blank" rel="friend noopener noreferrer">
                  <!--<img class="lazyload" src="https://cdn.jsdelivr.net/gh/honjun/cdn@1.6/img/loader/orange.progress-bar-stripe-loader.svg" data-src="/wp-content/uploads/2020/05/资源.png">-->
                  <img src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/1618926500372.jpg">
                  <span class="sitename">生活</span>
                  <div class="linkdes">Life</div>
                </a>
              </li>
              <!------------------------------END------------------------------------->
            </ul>
          </div>
        </div>
      </article>
    </page-content>
  </div>
</template>

<script>

export default {
  name: "categories",
  components: {
    PageContent: () => import('@/components/PageContent'),
    Cover: () => import('@/layouts/sora/widgets/Cover'),
  },
  data: () => ({}),
}
</script>

<style scoped>
.poem-wrap {
  position: relative;
  width: 730px;
  max-width: 80%;
  border: 2px solid #797979;
  border-top: none;
  text-align: center;
  margin: 80px auto;
}
.poem-left {
  left: 0;
}
.poem-border {
  position: absolute;
  height: 2px;
  width: 27%;
  background-color: #797979;
}
.poem-right {
  right: 0;
}
.poem-border {
  position: absolute;
  height: 2px;
  width: 27%;
  background-color: #797979;
}
.poem-wrap p {
  width: 70%;
  margin: auto;
  line-height: 30px;
  color: #797979;
}
.poem-wrap p#poem {
  font-size: 25px;
}
.poem-wrap p#info {
  font-size: 15px;
  margin: 15px auto;
}
/**/
.links {
  margin-bottom: 80px !important;
}
.link-items{
  border:none !important;
  padding-left:30px;
  padding-right:30px;
}

.entry-content ol li,
.entry-content ul li {
  padding: 8px 30px !important;
}
.good{
  position:absolute;
  right:0;
  top:0;
  border-style: solid;
  border-width: 0px 50px 40px 0px;
  border-color: transparent #ff0033 transparent transparent !important;
  width: 0px;
  height: 0px;
}
.good span{
  margin-left: 32px;
  color: white;
  font-size: 13px;
}



</style>