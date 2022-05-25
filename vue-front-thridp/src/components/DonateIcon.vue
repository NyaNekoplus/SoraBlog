<template>
  <div class="single-reward">
    <div class="reward-open">赏
      <div class="reward-main">
        <ul class="reward-row">
          <li class="alipay-code">
            <img :src="aliPay">
          </li>
          <li class="wechat-code">
            <img :src="wechat">
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DonateIcon",
  data: () => ({
    aliPay: "https://fastly.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.4/img/alipay.png",
    wechat: "https://fastly.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.4/img/wechat.png"
  }),
}
</script>

<style scoped>

</style>
