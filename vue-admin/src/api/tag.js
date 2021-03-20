/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/20 17:09
 */
import request from "../util/request";

export function addCategory(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/category/add',
    method: 'post',
    data: param
  })
}

export function getTagList(){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/tag/list',
    method: 'get',
  })
}
