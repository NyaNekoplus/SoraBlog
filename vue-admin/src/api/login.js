/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/14 9:30
 */
import request from "@/util/request";
export function login(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/user/login',
    method: 'post',
    data: param
  })
}
export function getInfo(){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/user/getInfo',
    method: 'get',
    //data: {token}
  })
}
export function logout(token){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/user/logout',
    method: 'post',
    data: token
  })
}
