/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/27 11:12
 */
import request from "@/utils/request";

export function getCategoryByName(params){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/category/getCategoryByName/'+params,
        method: 'get',
    })
}