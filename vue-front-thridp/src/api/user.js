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
        url: process.env.VUE_APP_FRONT_API+'/user/register',
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