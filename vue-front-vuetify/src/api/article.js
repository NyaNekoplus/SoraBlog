import request from "@/utils/request";

export function getBlogList(){
    return request({
        url: '/mock/blog.json',
        method: 'get'
        //method: 'post',
        //data: userData
    })
}