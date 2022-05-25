<template>
  <div class="sora-form">
    <div class="sora-form__logo-box">
      <div class="sora-form__logo">
        悠の空
      </div>
      <div class="sora-form__catchphrase">
        <div style="display: inline-block">
          <input v-show="false" type="file" accept="image/png, image/jpeg, image/gif, image/jpg" @change="previewImage($event)" ref="input" />
          <div style="width:64px;height:64px;text-align:center;" @click="openImg">
            <!--<span v-if="imgUrl==''">点击上传</span>-->
            <img style="height:100%;width:100%;"
                 :src="imgUrl===''?'https://fastly.jsdelivr.net/gh/Nyanekoplus/js@master/data/none.png':imgUrl" alt="上传头像"/>
          </div>
        </div>
      </div>
    </div>

    <input type="text" required autocomplete="username" placeholder="用户名" autocapitalize="off" v-model="username">
    <input type="password" autocomplete="current-password" placeholder="密码" autocapitalize="off" v-model="password">
    <input type="text" required autocomplete="email" placeholder="邮箱" autocapitalize="off" v-model="email">
    <input class="register-button" value="发送验证邮件" type="button" @click="sendAuthEmail">
    <input type="text" required autocomplete="username" placeholder="Authentication Code" autocapitalize="off" v-model="code">

    <ul class="uuvlpj-0 jpfKJC"></ul>
    <input class="register-button" value="注册" type="button" @click="handleRegister">
    <div class="sora-form-nav">
      <router-link to="/login">已有账号?->登陆</router-link>
      <!--<a href="/login"></a>-->
    </div>

    <div class="sora-form__sns-btn-area">
      <div class="sora-form__sns-btn-area index">
        <div>使用其他账号登陆</div>
        <div class="sns-button-list"><a
            href="javascript:void(0)"
            class="btn-item btn-apple js-click-trackable compact index" data-click-category="signup_page_pc"
            data-click-action="step1" data-click-label="apple" rel="nofollow">Twitter</a>
           |
          <a
            href="javascript:void(0)"
            class="btn-item btn-weibo js-click-trackable compact index" data-click-category="signup_page_pc"
            data-click-action="step1" data-click-label="sina" rel="nofollow">Github</a>
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

import {getQQInfo, register, sendAuthEmail} from "../api/user";
import {message} from "../components/Message";
import {uploadImage} from "../api/file";
import {mapMutations} from "vuex";

export default {
  name: "register",
  data: () => ({
    username: '',
    email: '',
    password: '',
    code: '',

    image: null,
    imgUrl: '',

    requestParam: {
      projectName: 'sora-front',
      classificationName: 'user-avatar'
    }
  }),
  methods: {
    ...mapMutations(["setToken"]),
    getQQInfo(){
      let q = this.qq;
      console.log('qq:'+q)
      if (!q || q.length<7 || q.length > 14){
        message('一般人q号多长心里没点b树吗')
        return
      }
      let param = new URLSearchParams();
      param.append('qq',q);
      getQQInfo(param).then(response => {
        message(response.message)
        if (response.state === this.$STATE.SUCCESS){
          // this.setUserInfo(response.data) // bug 会导致vuex中的userid被修改，应等到验证完成再设置
          this.qqAvatar = response.data.qqAvatar
          this.qq = response.data.username
          this.email = response.data.email
        }
      })
    },
    sendAuthEmail(){
      let emReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
      if (!emReg.test(this.email)){
        message('请输入有效的邮箱地址')
        return
      }
      let param = new URLSearchParams();
      param.append('email',this.email);
      sendAuthEmail(param).then(response=> {
        message(response.message);
      })
    },
    handleRegister(){
      //return // TODO 暫時暫停注冊
      let c = this.code.trim();
      if (c.length !== 6){
        message('验证码长度不符')
        return
      }
      let emReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
      if (!emReg.test(this.email)){
        message('请输入有效的邮箱地址')
        return
      }
      let params = {};
      params.username = this.username;
      params.password = this.password;
      params.email = this.email;
      params.code = c
      if (this.image!=null){
        let formData = new FormData();
        formData.append('imageList', this.image);
        for (let key in this.requestParam){formData.append(key,this.requestParam[key]);}
        uploadImage(formData).then(fileRes=>{
          message(fileRes.message);
          if (fileRes.state === this.$STATE.SUCCESS){
            params.avatarUid = fileRes.data[0].uid;
            register(params).then(response => {
              message(response.message);
              if (response.state === this.$STATE.SUCCESS) {
                let token = response.data
                this.setToken(token);
                this.$router.push('/');
              }
            })
          }
        });
      }else {
        register(params).then(response => {
          message(response.message);
          if (response.state === this.$STATE.SUCCESS) {
            let token = response.data
            this.setToken(token);
            this.$router.push('/');
          }
        })
      }
    },
    previewImage(e){
      let file = e.target.files[0];
      console.log(URL.createObjectURL(file))
      this.image = file;
      console.log('file: '+file)
      this.imgUrl = URL.createObjectURL(file)
      /*
      let url = "";
      var reader = new FileReader();
      reader.readAsDataURL(file);
      let that = this;
      reader.onload = function(e) {
        url = this.result.substring(this.result.indexOf(",") + 1);
        that.imgUrl = //"data:image/png;base64," + url;
        // that.$refs['imgimg'].setAttribute('src','data:image/png;base64,'+url);
      };
      */
    },
    openImg() {
      this.$refs.input.click();
    }
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

.sora-form input, .ex-register input {
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


.sora-form input.register-button {
  box-shadow: none;
  text-shadow: none;
  background-color: #eee
}

.sora-form input.register-button {
  margin-bottom: 20px
}

.sora-form input.register-button:hover {
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
