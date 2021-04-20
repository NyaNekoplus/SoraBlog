/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/12 21:01
 */

import router from "./index";
import store from "../store";
import {getToken} from "../util/cookie";

const allow = ['/login', '/404', '/500']
router.beforeEach((to, from, next) => {
  if (getToken()){
    if (to.path==='/login'){
      console.log('fuck')
      next({path:'/'})
    }else {
      if (store.getters.proxy !== null){
        store.dispatch('logger/GetInfo').then(res=>{
          console.log('getinfo')
          next()
        }).catch(err=>{
          store.dispatch('logger/FrontEndLogout').then(()=>{
            console.log('获取用户信息失败'+err)
            next('/')
          })
        });
        //store.dispatch('GetDisplayList')
      }else {
        if (allow.indexOf(to.path) !== -1){
          console.log('fuck4')
          next()
        }else {
          next({path:'/404'})
        }
      }
    }
  }else {
    if (allow.indexOf(to.path)!==-1){
      console.log('fuck3')
      next()
    }else {
      console.log('fuck2')
      next(`/login?redirect=${to.path}`)
    }
  }
})
