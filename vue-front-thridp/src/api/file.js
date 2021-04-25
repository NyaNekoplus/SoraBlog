/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/3 16:16
 */
import request from "@/utils/request";

export function getImageClassificationList(){
    return request({
        url: process.env.VUE_APP_FILE_API + '/list',
        method: 'get',
    })
}

export function getImageList(params){
    return request({
        url: process.env.VUE_APP_FILE_API + '/list',
        method: 'post',
        data: params
    })
}

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
