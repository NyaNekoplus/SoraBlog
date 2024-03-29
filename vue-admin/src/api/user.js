/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/12 14:59
 */
import request from "@/util/request";

export function login(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API +'/user/login',
    method: 'post',
    data: param
  })
}

export function updateAboutMe(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API+'/user/updateAboutMe',
    method: 'post',
    data: param
  })
}
export function getAboutMe(){
  return request({
    url: process.env.VUE_APP_ADMIN_API+'/user/getAboutMe',
    method: 'get',
    //data: param lang
  })
}

export function getUserListByPage(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/user/getUserListByPage',
    method: 'post',
    data: param
  })
}

export function updateUserState(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/user/update',
    method: 'post',
    data: param
  })
}

export function removeUser(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/user/delete/' + param,
    method: 'delete'
  })
}



