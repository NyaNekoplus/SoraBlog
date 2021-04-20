/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/19 11:15
 */
import request from "@/util/request";
export function login(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/user/login',
    method: 'post',
    data: param
  })
}
export function indexInit(){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/summary/indexInit',
    method: 'get',
  })
}
