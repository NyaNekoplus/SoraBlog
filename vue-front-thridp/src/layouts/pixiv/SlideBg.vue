<template>
  <div id="background-slideshow">
    <div class="slide">
      <div class="wallpaper"
           style="background-image:url('https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE4wyTr?ver=ecf5')">
      </div>
    </div>
    <div class="slide">
      <div :class="`wallpaper ${changeBg?'fadeout':''}`"
           style="background-image:url('https://www.bing.com/th?id=OHR.JouxFog_ZH-CN9947036409_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=HpEdgeAn')">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SlideBg",
  data: () => ({
    changeBg: true,
  }),
  methods: {
    changeBackground: function () {
      this.changeBg = !this.changeBg;
      setTimeout(this.changeBackground,3000);
    }
  },
  mounted() {
    this.changeBackground()
  },
  created() {

  }
}
</script>

<style scoped>
#background-slideshow .slide {
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  z-index: -1;
  position: fixed;
}

#background-slideshow .wallpaper.fadeout {
  opacity: 0;
}
#background-slideshow .wallpaper {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position-x: 50%;
  background-position-y: 0;
  opacity: 1;
  -webkit-transition-property: opacity;
  transition-property: opacity;
  -webkit-transition-duration: 1s;
  transition-duration: 1s;
  -webkit-transition-timing-function: ease;
  transition-timing-function: ease;
}

#background-slideshow .user-info {
  display: block;
  position: fixed;
  width: 200px;
  height: 50px;
  bottom: 38px;
  right: 8px;
  font-family: HiraginoSans-W3, Hiragino Kaku Gothic Pro, \\30E1\30A4\30EA\30AA, Meiryo, Verdana, sans-serif;
  border-radius: 4px;
  background-color: rgba(23, 24, 25, .3);
  color: #fff;
}

#background-slideshow .user-info .description {
  display: inline-block;
  margin-top: 8px;
  margin-left: 8px;
  vertical-align: top;
  width: 132px;
}

#background-slideshow .user-info .description .illust-title {
  display: block;
  font-size: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

#background-slideshow .user-info .description .user-name {
  display: block;
  font-size: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
