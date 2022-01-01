<template>
  <div>
    <div class="blank" style="padding-top: 75px;"></div>
    <page-content>
      <div id="main">
        <header class="page-header"><h1 class="cat-title">时光轴</h1> <span class="cat-des"><p>Timeline</p> </span></header>
        <div id="main-part">
          <article class="art">
            <div class="art-main">
              <div class="art-content">
                <div id="archives"><p style="text-align:right;">[<span id="al_expand_collapse" style="cursor: s-resize;">全部展开/收缩</span>]</p>

                  <div v-for="year in yearList">
                    <h3 class="al_year">{{ year.value }} 年</h3>
                    <ul class="al_mon_list" v-for="month in year.monthList">
                      <li class="al_li">
                        <span class="al_mon" style="cursor: s-resize;">{{month.value}}月 <!--(<span id="post-num">1</span>篇文章)--></span>
                        <ul class="al_post_list" v-for="day in month.dayList">
                          <li>
                            <a href="https://sora.vin">
                              <span style="color:#0bf;">{{ day.value }}日 </span>
                              <span v-html="day.description"></span>
                              <!--{{day.description}}-->
                            </a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <!--
                  <h3 class="al_year">2021 年</h3>
                  <ul class="al_mon_list">
                    <li class="al_li">
                      <span class="al_mon" style="cursor: s-resize;">6月 </span>
                      <ul class="al_post_list">
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">13日 </span>修复DELETE跨域问题
                          </a>
                        </li>
                      </ul>
                    </li>
                    <li class="al_li">
                      <span class="al_mon" style="cursor: s-resize;">5月 </span>
                      <ul class="al_post_list">
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">26日 </span>修复后台文本编辑器在dialog中不能focus的问题(retain-focus=false)
                          </a>
                        </li>
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">25日 </span>Now the title will be automatically added with ID as anchor when the article is loading.
                          </a>
                        </li>
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">17日 </span>修复目录加载高度 / IOS getFullYear返回NaN pm8:17
                          </a>
                        </li>
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">14日 </span>修复了已知的所有Bug，前端页面基本完成 pm11:26
                          </a>
                        </li>
                      </ul>
                    </li>
                    <li class="al_li">
                      <span class="al_mon" style="cursor: s-resize;">4月 </span>
                      <ul class="al_post_list">
                        <li>
                          <a href="https://sora.vin">
                            <span style="color:#0bf;">28日 </span>悠远的苍穹-水泥未干版 0.0.1
                            文章的评论数和热度
                            <span>
                              (3560<span class="fa fa-fire" aria-hidden="true"></span> / 46
                              <span class="fa fa-commenting" aria-hidden="true"></span>)
                            </span>

                          </a>
                        </li>
                      </ul>
                    </li>
                  </ul>-->
                  <!--
                  <h3 class="al_year">2019 年</h3>
                  <ul class="al_mon_list">
                    <li class="al_li"><span class="al_mon" style="cursor: s-resize;">12月 (<span id="post-num">1</span>篇文章)</span>
                      <ul class="al_post_list" style="display: none;">
                        <li>
                          <a href="https://2heng.xin/2019/12/31/graphql-recursion/">
                            <span style="color:#0bf;">31日 </span>GraphQL 实现递归查询 <span>(3560
                            <span class="fa fa-fire" aria-hidden="true"></span> / 46
                            <span class="fa fa-commenting" aria-hidden="true"></span>)</span>
                          </a>
                        </li>
                      </ul>
                    </li>
                  </ul>

                  <h3 class="al_year">2018 年</h3>
                  <ul class="al_mon_list">
                    <li class="al_li">
                      <span class="al_mon" style="cursor: s-resize;">12月 (<span id="post-num">2</span>篇文章)</span>
                      <ul class="al_post_list" style="display: none;">
                        <li>
                          <a href="https://2heng.xin/2018/12/16/your-own-ca-with-openssl/">
                            <span style="color:#0bf;">16日 </span>OpenSSL 自签 CA 及 SSL 证书 <span>(7476
                            <span class="fa fa-fire" aria-hidden="true"></span> / 34
                            <span class="fa fa-commenting" aria-hidden="true"></span>)</span>
                          </a>
                        </li>
                        <li>
                          <a href="https://2heng.xin/2018/12/12/embed-hearthstone-deck-with-iframe/">
                            <span style="color:#0bf;">12日 </span>在网页上展示你的炉石卡组吧 <span>(3905
                            <span class="fa fa-fire" aria-hidden="true"></span> / 28
                            <span class="fa fa-commenting" aria-hidden="true"></span>)</span>
                          </a>
                        </li>
                      </ul>
                    </li>
                  </ul>
                  -->
                </div>
              </div>
            </div>
          </article>
        </div>
      </div>
    </page-content>
  </div>
</template>

<script>

import {getTimeLine} from "../api/timeline";

export default {
  name: "timeline",
  components: {
    PageContent: () => import('@/components/PageContent'),
  },
  data: () => ({
    yearList: [
      {
        value: 2021,
        monthList: [
          {
            value: 8,
            dayList: [
              {value: 19, description: 'Update social network links in mobile device'},
              {value: 6, description: 'Fix to tool box and toTop button display problem'},
            ]
          },
          {
            value: 7,
            dayList: [
              {value: 19, description: 'Signed up for Telegram. Contact: <a href="https://t.me/realVincentTsai" target="_blank" rel="noopener noreferrer">@realVincentTsai</a>'},
            ]
          },
          {
            value: 6,
            dayList: [
              {value: 27, description: 'Add Link sample'},
              {value: 20, description: '修复时区问题(Asia/Shanghai-UTC+8)'},
              {value: 19, description: '增加邮件模板，现在被回复/新文章发布时用户将收到邮件通知;使用RabbitMQ作为消息中间件'},
              {value: 13, description: '修复DELETE跨域问题'},
            ]
          },
          {
            value: 5,
            dayList: [
              {value: 26, description: '修复后台文本编辑器在dialog中不能focus的问题(retain-focus=false)'},
              {value: 25, description: 'Now the title will be automatically added with ID as anchor when the article is loading.'},
              {value: 17, description: '修复目录加载高度 / IOS getFullYear返回NaN pm8:17'},
              {value: 14, description: '修复了已知的所有Bug，前端页面基本完成 pm11:26'},
            ]
          },
          {
            value: 4,
            dayList: [
              {value: 28, description: '悠远的苍穹-水泥未干版 0.0.1'},
            ]
          },
        ]
      }
    ]
  }),
  created() {
    getTimeLine(); // Just for record, presently TODO
  }
}
</script>

<style scoped>

</style>