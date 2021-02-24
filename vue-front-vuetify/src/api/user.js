import request from "@/util/request";

export function login(userData){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/user/login',
        method: 'post',
        data: userData
    })
}

export function register(userData){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/user/register',
        method: 'post',
        data: userData
    })
}