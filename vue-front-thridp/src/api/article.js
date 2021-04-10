import request from "@/utils/request";

export function getBlogList(params){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/list',
        method: 'post',
        data: params
    })
}

export function getBlogByTitle(params){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/getBlogByTitle/' + params,
        method: 'get',
    })
}

export function getTopBlog(){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/getTop',
        method: 'get',
    })
}