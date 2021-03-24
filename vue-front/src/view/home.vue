<!-- 个人中心 -->

<template>
  <article>
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-container>
          <b-navbar-brand to="/">悠远的苍穹</b-navbar-brand>
          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item to="life">Life</b-nav-item>
              <b-nav-item to="tech">Tech</b-nav-item>
              <b-nav-item href="#">流水账</b-nav-item>
              <b-nav-item>标签</b-nav-item>
              <b-nav-item to="about">关于</b-nav-item>
              <b-nav-item href="#">友情链接</b-nav-item>
              <b-nav-item href="#">留言板</b-nav-item>
              <b-nav-item href="#" disabled>成就墙</b-nav-item>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
              <b-nav-item>归档</b-nav-item>

              <b-nav-form right>
                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
              </b-nav-form>

              <b-nav-item-dropdown text="Lang" right>
                <b-dropdown-item href="#">简体</b-dropdown-item>
                <b-dropdown-item href="#">繁体</b-dropdown-item>
                <b-dropdown-item href="#">English</b-dropdown-item>
                <b-dropdown-item href="#">日本語</b-dropdown-item>
              </b-nav-item-dropdown>

              <b-nav-item-dropdown right>
                <!-- Using 'button-content' slot -->
                <template #button-content>
                  <!--<em>User</em>-->
                  <b-avatar v-if="isLogin" src="../assets/sora.png"
                            class="d-inline-block align-top" alt="Kitten"></b-avatar>
                  <b-avatar v-if="!isLogin" src="../assets/favicon-32x32.png"
                            class="d-inline-block align-top" alt="Kitten"></b-avatar>
                </template>
                <b-dropdown-item v-show="!isLogin" to="login">登陆</b-dropdown-item>
                <b-dropdown-item v-show="!isLogin" to="register">注册</b-dropdown-item>
                <b-dropdown-item v-show="isLogin" v-b-toggle.sidebar-no-header>Profile</b-dropdown-item>
                <b-dropdown-item v-show="isLogin" @click="signOut">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-collapse>
        </b-container>


      </b-navbar>
    </div>

    <div>
      <b-sidebar
          id="sidebar-no-header"
          aria-labelledby="sidebar-no-header-title"
          no-header
          right
          backdrop
          shadow="lg"
          lazy
          width="600px"
      >

            <b-card no-body header-tag="header">
              <b-tabs  card vertical justified class="w-auto vsidebar">
                <b-tab title="个人信息" active  >
                  <b-card-text>个人信息</b-card-text>
                  <h4 id="sidebar-no-header-title">Custom header sidebar</h4>
                  <p>
                    Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
                    in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
                  </p>
                  <nav class="mb-3">
                    <b-nav vertical>
                      <b-nav-item active @click="hide">Active</b-nav-item>
                      <b-nav-item href="#link-1" @click="hide">Link</b-nav-item>
                      <b-nav-item href="#link-2" @click="hide">Another Link</b-nav-item>
                    </b-nav>
                  </nav>
                  <b-button variant="primary" block @click="hide">Close Sidebar</b-button>
                </b-tab>
                <b-tab title="我的评论">
                  <b-card-text>我的评论</b-card-text>
                </b-tab>
                <b-tab title="我的回复">
                  <b-card-text>我的回复</b-card-text>
                </b-tab>
                <b-tab title="交换友链" active>
                  <b-card-text>交换友链</b-card-text>
                </b-tab>
                <b-tab title="修改密码">
                  <b-card-text>修改密码</b-card-text>
                </b-tab>
                <b-tab title="Disabled">
                  <b-card-text>Disabled</b-card-text>
                </b-tab>

              </b-tabs>

              <template #header>
                <em>Footer Slot</em>
              </template>
              <!--
              <template #footer>
                <em>Footer Slot</em>
              </template>
              -->
            </b-card>

      </b-sidebar>
    </div>

    <!-- 跳转的内容 -->
    <div>
      <router-view/>
    </div>
    <!---->

    <footer class="vfooter">
      <b-container>
        <b-row>
          <p class="col-md-12">
            <small>
              <a href="javasrcipt:void(0);">Copyright 2021&nbsp;Vincent Tsai&nbsp;</a>
              <a href="http://www.tooisorahe.com">悠远的苍穹</a>
            </small>
          </p>
        </b-row>

      </b-container>
    </footer>

  </article>
</template>

<script>
import {authToken} from "@/api/user"
import {getCookie, removeCookie, setCookie} from "@/util/cookie";
import {mapMutations} from "vuex";

export default {
  name: "index",
  data(){
    return{
      isLogin: false,
    }
  },
  created() {
    this.verifyLogin();
  },
  methods:{
    ...mapMutations(['removeToken','setLoginState']),
    toLoginPage(){
      this.$router.replace('/login')
    },
    verifyLogin(){
      let token = this.$store.state.token;
      if (token !== ''){
        console.log('验证store中的token不为空，写入cookie');
        setCookie("token", token, 7);
      }else {
        console.log('store中token为空，从cookie中获取');
        token = getCookie("token");
      }
      console.log('token: '+token);
      if (token !== null){
        authToken(token).then(response=>{
          if(response.state === this.$STATE.SUCCESS){
            this.isLogin = true;
            //this.userInfo
            this.setToken(response.data);
            console.log(response.data);
          }else {
            this.isLogin = false;
            removeCookie("token");
            console.log(response.data);
            alert("后台消息："+response.data.message);
          }
          this.setLoginState(this.isLogin);
        });
      }else {
        this.isLogin = false;
        this.setLoginState(this.isLogin);
      }
    },
    signOut(){
      this.isLogin = false;
      this.removeToken();
      removeCookie("token");
    }
  }
}
</script>

<style scoped>
.vsidebar{
  height: 96.5vh;
}
.vfooter{
  background-color: #1E90FF;
  border: 0;
  border-radius: 0;
  color: rgba(255,255,255,0.84);
  height: 60px;
  margin-top: 80px;
  clear: both;
  position: relative;
}
</style>