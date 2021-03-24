/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/13 14:31
 */

import {REMOVE_TOKEN, REMOVE_USER_INFO, SET_LOGIN_STATE, SET_TOKEN, SET_USER_INFO} from "./mutation-types"

const user = {
    state: {
        isLogin: false,
        token: null,
        userInfo: null
    },
    getters: {
        isLogin: state => {
            if (!state.isLogin){
                state.isLogin = JSON.parse(sessionStorage.getItem('isLogin'));
            }
            return state.isLogin;
        },
        token: state => {
            if (!state.token){
                console.log(sessionStorage.getItem('token'));
                state.token = sessionStorage.getItem('token');
            }
            return state.token;
        },
        userInfo: state => {
            if (!state.userInfo){
                state.userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
            }
            return state.userInfo;
        }
    },
    mutations: {
        [SET_LOGIN_STATE](state, isLogin){
            state.isLogin = isLogin;
            sessionStorage.setItem('isLogin', isLogin);
        },
        [SET_USER_INFO](state, userInfo){
            state.userInfo = userInfo;
            sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
        },
        [REMOVE_USER_INFO](state){
            state.userInfo = {};
            sessionStorage.removeItem('userInfo');
        },
        [SET_TOKEN](state, token) { // 设置存储token
            state.token = token;
            sessionStorage.setItem('token', token);
            //localStorage.setItem('token', token);
        },
        [REMOVE_TOKEN](state){  // 删除token
            state.token = '';
            sessionStorage.removeItem('token');
            //localStorage.removeItem('token');
        },
    }
}
export default user