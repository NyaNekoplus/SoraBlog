<template>
    
  <div class="container bg-transparent">

    <div class="card o-hidden border-0 shadow-lg my-5  bg-transparent">
      <div class="card-body p-0 bg-transparent">
        <!-- Nested Row within Card Body -->
        <div class="row transparency">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
              </div>
              <form class="user" @submit="handleRegister">
                <!--<form class="user"action="@{/admin/user/register}" method="post" onsubmit="return checkEqual();">-->
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input required pattern="[A-Za-z0-9-_]+" type="text" class="form-control form-control-user" id="username" v-model="userData.userName" placeholder="#{username}">
                  </div>
                  <div class="col-sm-6">
                    <b-form-input required type="text" class="form-control form-control-user" id="nickname" v-model="userData.nickName" placeholder="#{nickname}"></b-form-input>
                  </div>
                </div>
                <div class="form-group">
                  <b-form-input required type="email" class="form-control form-control-user" id="inputEmail" v-model="userData.email" placeholder="#{email}"></b-form-input>
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <b-form-input required type="password" class="form-control form-control-user" id="inputPassword" v-model="userData.password" placeholder="#{password}"></b-form-input>
                  </div>
                  <div class="col-sm-6">
                    <b-form-input required type="password" class="form-control form-control-user" id="repeatPassword" v-model="repeatPassword" placeholder="#{repeatPw}"></b-form-input>
                  </div>
                </div>
                <!--<input type="submit" class="btn btn-primary btn-user btn-block"value="#{register}">-->
                <input type="submit" class="btn btn-primary btn-user btn-block" href="javascript(void)">
                <hr>
                <a href="index.html" class="btn btn-google btn-user btn-block">
                  <i class="fab fa-google fa-fw"></i> Login with QQ
                </a>
                <a href="index.html" class="btn btn-facebook btn-user btn-block">
                  <i class="fab fa-facebook-f fa-fw"></i> Login with Wechat
                </a>
                <a href="index.html" class="btn btn-facebook btn-user btn-block">
                  <i class="fab fa-facebook-f fa-fw"></i> Login with Github
                </a>
              </form>
              <hr>
              <div class="text-center">
                <b-link class="small" href="forgot-password.html">Forgot Password?</b-link>
              </div>
              <div class="text-center">
                <b-link class="small" to="login">Already have an account? Login!</b-link>
              </div>
              <div class="text-center">
                <!--国际化，给不同参数-->
                <a class="small" href="@{/admin/register.html(lang='zh_CN')}">中文</a>
                <a class="small" href="@{/admin/register.html(lang='en_US')}">English</a>
                <a class="small" href="@{/admin/register.html(lang='ja_JP')}">日本語</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  

  

</template>

<script>
import {register} from "@/api/user"

export default {
  name: "register",
  data(){
    return{
      repeatPassword: '',
      userData:{
        userName: '',
        nickName: '',
        password: '',
        email: '',
      }
    }
  },
  methods:{
    handleRegister(){
      register(this.userData).then(response=>{
        alert(response.data);
        if(response.state === this.$STATE.SUCCESS){
          alert('跳轉');
        }
      });
    }
  }
}
</script>

<style scoped>
.bg-gradient-primary {
  background-color: #4e73df;
  background-image: linear-gradient(180deg, #4e73df 10%, #224abe 100%);
  background-size: cover;
}
</style>