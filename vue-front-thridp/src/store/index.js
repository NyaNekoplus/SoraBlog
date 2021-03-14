/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:17
 */

import Vue from "vue";
import Vuex from "vuex"
import user from "./user";
import blog from "./blog";

Vue.use(Vuex);



const store = new Vuex.Store({
    modules: {
        user,
        blog
    }
});

export default store