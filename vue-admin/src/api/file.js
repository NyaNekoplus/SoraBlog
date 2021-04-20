/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/27 23:06
 */

import request from "../util/request";

export function uploadImage(param){
  return request({
    url: process.env.VUE_APP_FILE_API + '/images',
    method: 'post',
    data: param,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function getImageClassification(){
  return request({
    url: process.env.VUE_APP_FILE_API + '/getImageClassification',
    method: 'get',
  })
}

export function addImageClassification(params){
  return request({
    url: process.env.VUE_APP_FILE_API + '/addClassification',
    method: 'post',
    data: params
  })
}

export function updateImageClassification(params){
  return request({
    url: process.env.VUE_APP_FILE_API + '/updateClassification',
    method: 'post',
    data: params
  })
}

export function deleteImageClassification(param){
  return request({
    url: process.env.VUE_APP_FILE_API + '/deleteClassification/' + param,
    method: 'get'
  })
}

export function getImageListByPge(params){
  return request({
    url: process.env.VUE_APP_FILE_API + '/getImageListByPge',
    method: 'post',
    data: params
  })
}


