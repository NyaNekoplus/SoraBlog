/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/31 21:10
 */
import request from "@/utils/request";

export function getTagList(){
    return request({
        url: process.env.VUE_APP_FRONT_API+'/tag/list',
        method: 'get',
    })
}
