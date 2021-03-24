<template>
  <div id="background-slideshow">
    <div class=" slide">
      <div class="wallpaper"
           style="background-image:url('https://picsum.photos/510/300?random')">
        <div class="user-info">
          <a href="https://www.pixiv.net/users/772547" target="_blank">
            <div class="user-icon"
                 style="background-image:url('https://picsum.photos/id/11/100/60')">
            </div>
          </a>
          <div class="description">
            <div class="illust-title">
              <a href="https://www.pixiv.net/artworks/34512986" target="_blank">
                さざなみ
              </a>
            </div>
            <div class="user-name">
              <a href="https://www.pixiv.net/users/772547" target="_blank">
                loundrawさんの作品
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class=" slide">
      <div class="wallpaper"
           style="background-image:url('https://www.bing.com/th?id=OHR.JouxFog_ZH-CN9947036409_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=HpEdgeAn')">
        <div class="user-info">
          <a href="https://www.pixiv.net/users/76266" target="_blank">
            <div class="user-icon"
                 style="background-image:url('https://i.pximg.net/user-profile/img/2019/03/04/21/32/39/15481009_3e9c633d4ba157c79be31d983c978e6b_50.jpg')">
            </div>
          </a>
          <div class="description">
            <div class="illust-title">
              <a href="https://www.pixiv.net/artworks/57196809" target="_blank">
                居酒屋街
              </a>
            </div>
            <div class="user-name">
              <a href="https://www.pixiv.net/users/76266" target="_blank">
                室埴 ポコさんの作品
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SlideBg",
  data: () => ({}),
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