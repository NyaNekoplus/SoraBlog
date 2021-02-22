import request from "@/util/request";

export function addComment(params){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/comment/add',
        method: 'post',
        data: params
    })
}

export function getCommentList(params){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/comment/list',
        method: 'post',
        data: params
    })
}