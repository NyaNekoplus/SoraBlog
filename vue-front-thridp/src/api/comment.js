import request from "@/utils/request";

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
export function getCommentUpdatedList(params){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/comment/update',
        method: 'post',
        data: params
    })
}