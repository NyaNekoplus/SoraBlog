/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/7/21 14:01
 */

import request from "@/utils/request";

export function getTimeLine(){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/timeline/getTimeLine',
        method: 'get',
        //data: param lang
    })
}