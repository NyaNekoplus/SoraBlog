<template>
  <div class="sora-form">
    <div class="sora-form__logo-box">
      <div class="sora-form__logo">
        全て遠き理想郷
      </div>
      <div class="sora-form__catchphrase">Avalon</div>
    </div>

    <div class="major">
      <input type="text" required autocomplete="username" placeholder="邮箱/用户名" autocapitalize="off" v-model="username">
      <input type="password" required autocomplete="current-password" placeholder="密码" autocapitalize="off" v-model="password">
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
  </div>
</template>

<script>

export default {
  name: "Login",
  data: () => ({
    username: '',
    password: '',
    remember: 1,
  }),
  methods: {
    handleLogin(){
      if (this.username.length===0||this.password.length===0){
        alert('用户名或密码不能为空')
        return ;
      }
      let param = {};
      param.username = this.username;
      param.password = this.password;
      param.rememberMe = this.remember;
      this.$store.dispatch('logger/Login',param)
        .then(response => {
        if (response.state === this.$STATE.SUCCESS) {
          this.$router.push({path: '/'});
        } else {
          alert(response.message);
        }
      }).catch(()=>{
        console.log('login index: false')
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
