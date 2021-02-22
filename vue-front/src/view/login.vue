<template>

<div class="container bg-transparent">

  <!-- Outer Row -->
  <div class="row justify-content-center bg-transparent">

    <div class="col-xl-10 col-lg-12 col-md-9 bg-transparent">

      <div class="card o-hidden border-0 shadow-lg my-5 bg-transparent">
        <div class="card-body p-0 bg-transparent">
          <!-- Nested Row within Card Body -->
          <div class="row bg-transparent">
            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
            <div class="col-lg-6">
              <div class="p-5">
                <div class="text-center">
                  <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                </div>
                <b-form class="user"><!-- action="@{/user/login}-->
                  <div class="form-group">
                    <b-form-input required type="text" v-model="userData.userName" class="form-control form-control-user" id="inputEmail" placeholder="输入邮箱或账号"></b-form-input>
                  </div>
                  <div class="form-group">
                    <b-form-input required type="password" v-model="userData.password" class="form-control form-control-user" id="inputPassword" placeholder="输入密码"></b-form-input>
                  </div>
                  <div class="form-group">
                    <div class="custom-control custom-checkbox small">
                      <input type="checkbox" class="custom-control-input" id="customCheck">
                      <label class="custom-control-label" for="customCheck">Remember Me</label>
                    </div>
                  </div>
                  <b-button @click="handleLogin" class="btn btn-primary btn-user btn-block"></b-button><!-- onclick="return checkNull()" -->

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
                </b-form>
                <hr>
                <div class="text-center">
                  <b-link class="small" href="forgot-password.html">Forgot Password?</b-link>
                </div>
                <div class="text-center">
                  <b-link class="small" to="register">Create an Account!</b-link>
                </div>
                <div class="text-center">
                  <!--国际化，给不同参数-->
                  <b-link class="small" href="@{/admin/login.html(lang='zh_CN')}">中文</b-link>
                  <a class="small" href="@{/admin/login.html(lang='en_US')}">English</a>
                  <a class="small" href="@{/admin/login.html(lang='ja_JP')}">日本語</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

</div>

</template>

<script>
//import BgSlide from '@/components/BgSlide'
import {login} from "@/api/user";
import {mapMutations} from "vuex";

export default {
  name: "login",
  components:{

  },
  data(){
    return{
      userData:{
        userName: '',
        nickName: '',
      },
      imgs:[],
      bgImg:[
        "../../bg/Vincent_van_Gogh_-_Green_Wheat_Fields_Auvers_(1890).png",
        "../../bg/Vincent_van_Gogh_-_Starry_Night_Over_the_Rhone.png",
        "../../Vincent_van_Gogh_-_The_yellow_house(1887).png",
        "../../Vincent_van_Gogh_-_Tuin_in_Montmartre_met_geliefden(1887)_-_Google_Art_Project.png"
      ]
    }
  },
  /*
  mounted() {
    this.imgs = [this.bgImg[0]];
    this.startChange();
  },
  */
  methods:{
    ...mapMutations(['setToken','removeToken']),
    handleLogin(){
      login(this.userData).then(response=>{
        if(response.state === this.$STATE.SUCCESS){
          //alert('跳轉');
          //alert(response.data);
          this.setToken(response.data);
          console.log(response.data);

          this.$router.push('/');
          //location.replace('localhost:8000'+'/?token='+response.data);
          //window.location.reload();
          //this.$refs
          //
        }
      });
    },
  }
}
</script>

<style scoped>

.flip-list-enter-active, .flip-list-leave-active {
  transition: all 1s;
}
.flip-list-enter, .flip-list-leave-active {
  opacity: 0;
}

.text-gray-900 {
  color: #3a3b45 !important;
}
.btn-user {
  font-size: 0.8rem;
  border-radius: 10rem;
  padding: 0.75rem 1rem;
}
.form-control-user {
  font-size: 0.8rem;
  border-radius: 10rem;
  padding: 1.5rem 1rem;
}

.bg-login-image {
  background: url("https://source.unsplash.com/K4mSJ7kc0As/600x800");
  background-position: center;
  background-size: cover;
}

.bg-register-image {
  background: url("https://source.unsplash.com/Mv9hjnEUHR4/600x800");
  background-position: center;
  background-size: cover;
}
</style>