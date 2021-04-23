/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:17
 */

import Vue from "vue";
import Vuex from "vuex"

import user from "./user";
import blog from "./blog";
import theme from "./theme";
import data from "./data";

Vue.use(Vuex);



const store = new Vuex.Store({
    modules: {
        user,
        blog,
        theme,
        data
    },
});

export default store