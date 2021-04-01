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
