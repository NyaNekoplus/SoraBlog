/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/7 13:36
 */
import axios from "axios";
import store from "../store";
import router from "../router";
import {getToken} from "./cookie";

const request = axios.create({
  baseURL: "/",
  timeout:20000
})

// request拦截器
/*
request.interceptors.request.use(
    config => {
        if (getCookie("token") != undefined) {
            config.headers.Authorization = getCookie("token") // 让每个请求携带自定义token 请根据实际情况自行修改
        }
        return config
    },
    error => {
        // Do something with request error
        console.log(error) // for debug
        Promise.reject(error)
    }
)
*/
request.defaults.headers.common['Authorization'] = getToken();

request.interceptors.request.use(
  config=>{
    if(store.getters.token){
      config.headers.common['Authorization']=getToken(); // todo
    }
    return config;
  },
  error => {
    router.push('500')
    return Promise.reject(error)
  }
)

// response 拦截器
request.interceptors.response.use(
  response => {
    // return response.data
    const res = response.data
    //res.data = {}
    return res;
    /*if (res.state === 'success' || res.state === 'error') {
        return res
    }
    else if (res.state === 401 || res.state === 400) {
        console.log('返回错误内容', res)
        router.push('404')
        return res
    } else if (res.state === 500) {
        router.push('500')
        return Promise.reject('error')
    } else if (res.state === 502) {
        router.push('502')
        return Promise.reject('error')
    } else {
        return Promise.reject('error')
    }*/
  },
  error => {
    // 出现网络超时
    router.push('500')
    return Promise.reject(error)
  }
)
export default request;
