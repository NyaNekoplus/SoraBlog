import request from "@/utils/request";

export function getBlogList(){
    return request({
        url: '/mock/article.json',
        method: 'get'
        //method: 'post',
        //data: userData
    })
}