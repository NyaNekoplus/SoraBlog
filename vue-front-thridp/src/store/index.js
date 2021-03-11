/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:17
 */

import Vue from "vue";
import Vuex from "vuex"
import {SET_TOKEN,REMOVE_TOKEN,SET_LOGIN_STATE} from "@/store/mutation-types";
import {SET_TO_ID, REMOVE_TO_ID, SET_BLOG} from "./mutation-types";

Vue.use(Vuex);

const state = {
    isLogin: false,
    token:'',
    blogId: null,
    toId: null, // number
    commentList: Array,
    //userInfo
    //token: localStorage.getItem('token')?localStorage.getItem('token'):'',
};

const store = new Vuex.Store({
    state,
    getters:{
        toId: state => {
            if (!state.toId){
                state.toId = JSON.parse(sessionStorage.getItem('toId'));
            }
            return state.toId;
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

        /*
        getStorage(state){   // 获取本地存储的登录信息
            if(!state.token){
                state.token =JSON.parse(sessionStorage.getItem('token'))
            }
            return state.token
        }
    */
    },
    mutations:{
        [SET_TOKEN](state, token) { // 设置存储token
            state.token = token;
            //sessionStorage.token = token;
            //localStorage.setItem('token', token);
        },
        [REMOVE_TOKEN](state){  // 删除token
            state.token = '';
            //sessionStorage.removeItem('token');
            //localStorage.removeItem('token');
        },
        [SET_LOGIN_STATE](state, isLogin){
            state.isLogin = isLogin;
        },
        [SET_TO_ID](state, toId){
            state.toId = toId;
            sessionStorage.setItem('toId', toId);
        },
        [REMOVE_TO_ID](state){
            state.toId = null;
            sessionStorage.removeItem('toId');
        },
        [SET_BLOG](state, blog){
            state.blog = blog;
            sessionStorage.setItem('blog', JSON.stringify(blog));
        },
    }
});

export default store