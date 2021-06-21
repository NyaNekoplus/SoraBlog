import request from "@/utils/request";

export function login(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/user/login',
        method: 'post',
        data: param
    })
}

export function register(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/auth/verify/email',
        method: 'post',
        data: param
    })
}

export function authToken(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/auth/verify/'+param,
        method: 'get',
    })
}

export function authEmail(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/auth/verify/email',
        method: 'post',
        data: param
    })
}

export function sendAuthEmail(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/auth/sendAuthEmail?'+param,
        method: 'get',
    })
}

export function getQQInfo(param){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/user/getQQInfo?'+ param,
        method: 'get',
    })
}

export function getAboutMe(){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/user/getAboutMe',
        method: 'get',
        //data: param lang
    })
}