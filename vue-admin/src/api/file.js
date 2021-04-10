/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/3 16:28
 */

import request from "../util/request";

export function getImageClassification(){
  return request({
    url: process.env.VUE_APP_FILE_API + '/getImageClassification',
    method: 'get',
  })
}

export function getImageListByPge(params){
  return request({
    url: process.env.VUE_APP_FILE_API + '/getImageListByPge',
    method: 'post',
    data: params
  })
}
