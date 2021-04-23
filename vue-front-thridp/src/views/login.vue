<template>
  <div class="sora-form">
    <div class="sora-form__logo-box">
      <div class="sora-form__logo">
        悠の空
      </div>
      <div class="sora-form__catchphrase">让创作变得更有乐趣</div>
    </div>


    <div class="major">
      <input type="text" autocomplete="username" placeholder="邮箱/用户名" autocapitalize="off" v-model="username">
      <input type="password" autocomplete="current-password" placeholder="密码" autocapitalize="off" v-model="password">
    </div>

    <p class="forgetmenot">
      <label for="rememberme">
        <input name="rememberme" type="checkbox" id="rememberme" value="forever" v-model="remember">
        Remember Me
      </label>
    </p>
    <ul class="uuvlpj-0 jpfKJC"></ul>
    <div class="major">
      <input class="login-button" value="登录" type="button" @click="handleLogin">
    </div>
    <div class="sora-form-nav">
      <a href="">忘记密码</a> |
      <a href="/register" data-click-category="signup_pc_page" data-click-action="step1" data-click-label="mail">注册账号</a>
    </div>


    <!--
    <a href="" class="sora-form__submit--login">登录</a>
    -->

    <div class="sora-form__sns-btn-area">
      <div class="sora-form__sns-btn-area index">
        <div>使用其他账号登陆</div>
        <div class="sns-button-list"><a
            href="https://accounts.pixiv.net/gigya-auth?mode=signin&amp;provider=apple&amp;source=pc&amp;view_type=page&amp;lang=zh&amp;ref=wwwtop_accounts_index_apple"
            class="btn-item btn-apple js-click-trackable compact index" data-click-category="signup_page_pc"
            data-click-action="step1" data-click-label="apple" rel="nofollow">QQ</a>
          |
          <a
              href="https://accounts.pixiv.net/gigya-auth?mode=signin&amp;provider=sina&amp;source=pc&amp;view_type=page&amp;lang=zh&amp;ref=wwwtop_accounts_index_sina"
              class="btn-item btn-weibo js-click-trackable compact index" data-click-category="signup_page_pc"
              data-click-action="step1" data-click-label="sina" rel="nofollow">Github</a>
          <!--
          <a
            href="https://accounts.pixiv.net/gigya-auth?mode=signin&amp;provider=googleplus&amp;source=pc&amp;view_type=page&amp;lang=zh&amp;ref=wwwtop_accounts_index_google"
            class="btn-item btn-gplus js-click-trackable compact index" data-click-category="signup_page_pc"
            data-click-action="step1" data-click-label="googleplus" rel="nofollow">通过 Google 继续</a><a
            href="https://accounts.pixiv.net/gigya-auth?mode=signin&amp;provider=facebook&amp;source=pc&amp;view_type=page&amp;lang=zh&amp;ref=wwwtop_accounts_index_facebook"
            class="btn-item btn-facebook js-click-trackable compact index" data-click-category="signup_page_pc"
            data-click-action="step1" data-click-label="facebook" rel="nofollow">通过 Facebook 继续</a> -->
        </div>
      </div>
    </div>
    <div class="recaptcha-terms">
      This site is protected by reCAPTCHA Enterprise and the Google
      <a href="https://policies.google.com/privacy"> Privacy Policy</a> and
      <a href="https://policies.google.com/terms"> Terms of Service</a> apply.
    </div>

  </div>
</template>

<script>

import {mapMutations} from "vuex";
import {login} from "../api/user";
import {message} from "../components/Message";

export default {
  name: "login",
  data: () => ({
    username: '',
    password: '',
    remember: 1,
  }),
  methods: {
    ...mapMutations(["setToken"]),
    handleLogin(){
      let param = {};
      param.username = this.username;
      param.password = this.password;
      param.remember = this.remember;
      login(param).then(response => {
        if (response.state === this.$STATE.SUCCESS) {
          message('aa');
          this.setToken(response.data);
          this.$router.push('/');
        } else {
          message(response.message);
        }
      })
    },
  }
}
</script>

<style scoped>

.sora-form {
  margin: 20vh auto 0;
  text-align: center;
  background-color: rgba(255, 255, 255, 0.75);
  width: 363px;
  padding: 40px 0 38px;
  border-radius: 4px;
}

.sora-form .major input {
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

.sora-form input.login-button {
  box-shadow: none;
  text-shadow: none;
  background-color: #eee
}

.sora-form input.login-button {
  margin-bottom: 20px
}

.sora-form input.login-button:hover {
  background-color: #E67474;
  border-color: #E67474;
  color: #fff
}

.sora-form .sora-form__logo-box {
  margin-bottom: 30px;
}

.sora-form .sora-form__logo {
  margin-bottom: 6px;
}

.logo, .sora-form .sora-form__logo {
  display: inline-block;
  width: 164px;
  height: 64px;
  background-image: url(/src/assets/background.jpg);
  background-repeat: no-repeat;
  background-size: contain;
}

.sora-form__catchphrase {
  color: #757c80;
  font-size: 12px;
  font-weight: bold;
}

.sora-form__submit, .sora-form__submit--login {
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

.sora-form__submit + .sora-form__submit--login, .sora-form__submit--login + .sora-form__submit--login {
  margin-top: 8px;
}

.sora-form__submit--login {
  background: rgba(0, 0, 0, 0.04);
  color: #5C5C5C;
  transition: 0.2s background-color;
}


.sora-form__sns-btn-area.index {
  color: #5C5C5C;
  font-size: 12px;
  line-height: 16px;
  max-width: 288px;
  box-sizing: border-box;
  margin: 22px auto 0;
}

.sora-form__sns-btn-area.index .sns-button-list {
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
</style>