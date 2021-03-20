/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/13 14:31
 */

import {REMOVE_TOKEN, SET_LOGIN_STATE, SET_TOKEN, SET_USER_INFO} from "./mutation-types"

const user = {
    state: {
        isLogin: false,
        token: null,
        userInfo: {}
    },
    getters: {
        isLogin: state => {
            return state.isLogin;
        },
        token: state => {
            return state.token;
        },
        userInfo: state => {
            return state.userInfo;
        }
    },
    mutations: {
        [SET_LOGIN_STATE](state, isLogin){
            state.isLogin = isLogin;
        },
        [SET_USER_INFO](state, userInfo){
            state.userInfo = userInfo;
        },
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
    }
}
export default user