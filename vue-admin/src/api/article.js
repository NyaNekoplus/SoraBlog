/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/7 13:25
 */
import request from "../util/request";

export function submitArticle(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/article/add',
    method: 'post',
    data: param
  })
}

export function getBlogListByPage(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/article/getArticleListByPage',
    method: 'post',
    data: param
  })
}

export function updateArticle(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/article/update',
    method: 'post',
    data: param
  })
}

export function deleteArticle(param){
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/article/delete/' + param,
    method: 'delete'
  })
}
