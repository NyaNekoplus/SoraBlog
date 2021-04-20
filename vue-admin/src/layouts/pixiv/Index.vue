<template>
  <div id="wrapper">
    <slide-bg/>

    <search-bar/>
    <pixiv-view/>

   <pixiv-footer/>

  </div>
</template>

<script>
export default {
  name: "Index",
  components: {
    PixivView: () => import("./View"),
    SlideBg: () => import("./SlideBg"),
    SearchBar: () => import("./SearchBar"),
    PixivFooter: () => import("./Footer")
  },
  data: () => ({}),
}
</script>

<style scoped>
abbr, acronym, address, applet, big, blockquote, body, caption, cite, code, dd, del, dfn, div, dl, dt, em, fieldset, form, h1, h2, h3, h4, h5, h6, html, iframe, img, ins, kbd, label, legend, li, object, ol, p, pre, q, s, samp, small, span, strike, strong, sub, sup, table, tbody, td, tfoot, th, thead, tr, tt, ul, var {
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  font-weight: inherit;
  font-style: inherit;
  font-family: inherit;
  font-size: 100%;
  vertical-align: baseline;
}



.signup-form {
  margin: 20vh auto 0;
  text-align: center;
  background-color: rgba(255, 255, 255, 0.92);
  width: 363px;
  padding: 40px 0 38px;
  border-radius: 4px;
}

.signup-form input, .ex-register input {
  width: 300px;
  margin-bottom: 6px;
  padding: 12px 10px;
  border: 1px
  #ddd solid;
  outline: none;
  border-radius: 3px;
  font-size: 16px;
  font: 300 14px miranafont,"Hiragino Sans GB","Microsoft YaHei",STXihei,SimSun,sans-serif;
  -webkit-transition: .4s ease all;
  -moz-transition: .4s ease all;
  -o-transition: .4s ease all;
  transition: .4s ease all;
}

.signup-form input.login-button,
.ex-register input.register-button {
  box-shadow: none;
  text-shadow: none;
  background-color: #eee
}

.ex-register input.register-button {
  margin-bottom: 20px
}

.signup-form input.login-button:hover,
.ex-register input.register-button:hover {
  background-color: #E67474;
  border-color: #E67474;
  color: #fff
}

.signup-form .signup-form__logo-box {
  margin-bottom: 30px;
}

.signup-form .signup-form__logo {
  margin-bottom: 6px;
}

.logo, .signup-form .signup-form__logo {
  display: inline-block;
  width: 164px;
  height: 64px;
  background-image: url(/src/assets/background.jpg);
  background-repeat: no-repeat;
  background-size: contain;
}

.signup-form__catchphrase {
  color: #757c80;
  font-size: 12px;
  font-weight: bold;
}

.signup-form__submit, .signup-form__submit--login {
  display: block;
  margin: 0 auto;
  width: 300px;
  height: 40px;
  line-height: 40px;
  border-radius: 3px;
  border-style: none;
  color: #FFF;
  background: #0096FA;
  outline: none;
  font-weight: bold;
  font-size: 14px;
  text-align: center;
  -webkit-transition: .4s ease all;
  -moz-transition: .4s ease all;
  -o-transition: .4s ease all;
  transition: .4s ease all;
}

.signup-form__submit + .signup-form__submit--login, .signup-form__submit--login + .signup-form__submit--login {
  margin-top: 8px;
}

.signup-form__submit--login {
  background: rgba(0, 0, 0, 0.04);
  color: #5C5C5C;
  transition: 0.2s background-color;
}

.btn, .btn-sns, .btn-gplus, .btn-facebook, .btn-twitter, .btn-weibo, .btn-apple, .signup-form__submit, .signup-form__submit--login {
  cursor: pointer;
  display: inline-block;
  text-decoration: none;
}

.signup-form__sns-btn-area.index {
  color: #5C5C5C;
  font-size: 12px;
  line-height: 16px;
  max-width: 288px;
  box-sizing: border-box;
  margin: 22px auto 0;
}

.signup-form__sns-btn-area.index .sns-button-list {
  margin-top: 14px;
}

.recaptcha-terms {
  font-style: normal;
  font-weight: normal;
  font-size: 12px;
  line-height: 16px;
  text-align: center;
  color: rgba(0, 0, 0, 0.32);
  width: 300px;
  margin: 16px auto 0;
  padding-top: 14px;
  border-top: 1px solid rgba(0, 0, 0, 0.08);
}

._footer-container {
  height: 30px;
  background-color: #464a4d;
  width: 100%;
  position: fixed;
  left: 0;
  bottom: 0;
  color: hsla(0, 0%, 100%, .5);
  z-index: 997;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

._footer-content {
  position: relative;
  height: 100%;
  margin: 0 auto;
  padding-left: 10px;
}

._footer-content-item {
  display: inline-block;
  position: relative;
  z-index: 997;
  line-height: 30px;
  height: 30px;
  padding: 0 10px;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

._footer-content-item .about {
  box-sizing: border-box;
  padding: 20px 20px 0;
  width: 890px;
  max-width: 80vw;
  color: #fff;
}

._footer-content-item .content-popup {
  display: none;
  position: absolute;
  left: 0;
  bottom: 30px;
  padding: 10px;
  background-color: #757c80;
  border-radius: 5px 5px 0 0;
  min-width: 100%;
  cursor: default;
  -webkit-user-select: auto;
  -moz-user-select: auto;
  -ms-user-select: auto;
  user-select: auto;
}

._footer-content-item .about .links {
  width: 170px;
  display: inline-block;
  vertical-align: top;
  padding: 0 10px;
  box-sizing: border-box;
  white-space: nowrap;
  overflow: hidden;
  margin-top: 15px;
}
</style>
