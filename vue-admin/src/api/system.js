/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/5 20:41
 */
import request from "../util/request";

export function getSystemConfig(){
  return request({
    url: process.env.VUE_APP_ADMIN_API  + '/system/getConfig',
    method: 'get',
  })
}

export function setDefaultCover(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/system/setDefaultCover',
    method: 'post',
    data: param
  })
}

export function updateSystemConfig(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API  + '/system/updateConfig',
    method: 'post',
    data: param
  })
}


