/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/19 19:54
 */

import request from "../util/request";

export function addCategory(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/category/add',
    method: 'post',
    data: param
  })
}

export function getCategoryList(){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/category/list',
    method: 'get',
  })
}
