import {REMOVE_TO_INFO, REMOVE_TOKEN, SET_BLOG, SET_BLOG_LIST, SET_TO_INFO, SET_TOKEN} from "./mutation-types";

/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/13 14:54
 */

const state = {
    blog: null,
    toInfo: null,
    commentList: null,
    //userInfo
    //token: localStorage.getItem('token')?localStorage.getItem('token'):'',
};

const blog = {
    state,
    getters:{
        toInfo: state => {
            if (!state.toInfo){
                state.toInfo = JSON.parse(sessionStorage.getItem('toInfo'));
            }
            return state.toInfo;
        },
        blog: state => {
            if (!state.blog){
                state.blog = JSON.parse(sessionStorage.getItem('blog'));
            }
            return state.blog;
        },
        commentList: state => {
            if (!state.commentList){
                state.commentList = JSON.parse(sessionStorage.getItem('commentList'));
            }
            return state.commentList;
        },
    },
    mutations:{
        [SET_TO_INFO](state, toInfo){
            state.toInfo = toInfo;
            sessionStorage.setItem('toInfo', JSON.stringify(toInfo));
        },
        [REMOVE_TO_INFO](state){
            state.toInfo = null;
            sessionStorage.removeItem('toInfo');
        },
        [SET_BLOG](state, blog){
            state.blog = blog;
            sessionStorage.setItem('blog', JSON.stringify(blog));
        }
    }
}
export default blog