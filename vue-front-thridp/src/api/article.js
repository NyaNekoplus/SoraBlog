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

export function getRecommend(){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/getRecommend',
        method: 'get',
    })
}

export function visitRecord(){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/visitRecord',
        method: 'get',
    })
}

export function likedIncrement(param){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/blog/likedIncrement?'+ param,
        method: 'get',
    })
}

export function searchArticle(param){
    return request({
        url: process.env.VUE_APP_FRONT_API + '/search/searchArticle?'+ param,
        method: 'get',
    })
}