import request from "@/util/request";

export function getArticleList(){
    return request({
        url: '/mock/article.json',
        method: 'get'
        //method: 'post',
        //data: userData
    })
}