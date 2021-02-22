import Vue from "vue";
import Vuex from "vuex"
import {SET_TOKEN,REMOVE_TOKEN,SET_LOGIN_STATE} from "@/store/mutation-types";

Vue.use(Vuex);

const state = {
    isLogin: false,
    token:'',
    //userInfo
    //token: localStorage.getItem('token')?localStorage.getItem('token'):'',
};

const store = new Vuex.Store({
    state,
    /*
    getters:{
        getStorage(state){   // 获取本地存储的登录信息
            if(!state.token){
                state.token =JSON.parse(sessionStorage.getItem('token'))
            }
            return state.token
        }
    },
    */
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
        }
    }
});

export default store