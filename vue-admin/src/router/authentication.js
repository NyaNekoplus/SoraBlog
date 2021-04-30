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
      next({path:'/'})
    }else {
      if (store.getters.proxy !== null){
        store.dispatch('logger/GetInfo').then(res=>{
          next()
        }).catch(err=>{
          store.dispatch('logger/FrontEndLogout').then(()=>{
            alert('获取用户信息失败'+err)
            next('/')
          })
        });
        //store.dispatch('GetDisplayList')
      }else {
        if (allow.indexOf(to.path) !== -1){
          next()
        }else {
          next({path:'/404'})
        }
      }
    }
  }else {
    if (allow.indexOf(to.path)!==-1){
      next()
    }else {
      next(`/login?redirect=${to.path}`)
    }
  }
})
