<template>
  <div id="search-bar">
    <div class="_3t-4thH">
      <a class="_369FxZ0" href="/"></a> <!-- 博客LOGO svg图片 -->
      <!--<form class="_3wjhj7j"><input class="_2tR5RNf" type="text" placeholder="搜索pixiv" value=""></form>-->
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  data: () => ({}),
}
</script>

<style scoped>
._3t-4thH {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
  align-items: center;
  box-sizing: border-box;
  height: 68px;
  margin-bottom: -68px;
  padding: 16px;
}

._369FxZ0

._3wjhj7j {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  -webkit-box-flex: 1;
  -webkit-flex: auto;
  flex: auto;
  -webkit-box-align: center;
  -webkit-align-items: center;
  align-items: center;
}

._3wjhj7j:before {
  content: "";
  display: block;
  width: 14px;
  height: 14px;
  margin: 2px -22px 0 8px;
  background: url(/www/js/build/628fc86….svg) 50% no-repeat;
  z-index: 1;
  pointer-events: none;
}

._3wjhj7j ._2tR5RNf {
  -webkit-box-flex: 1;
  -webkit-flex: 1 1 auto;
  flex: 1 1 auto;
  box-sizing: border-box;
  max-width: 500px;
  height: 36px;
  border: none;
  outline: none;
  border-radius: 4px;
  padding: 0 8px 0 26px;
  color: #666;
  font-size: 14px;
  background: #fff;
}
</style>