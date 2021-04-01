/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/31 20:59
 */
import {SET_WIDE_SCREEN_COVER} from "./mutation-types";

const theme = {
    state: {
        wideScreenCover: false,
    },
    getters: {
        wideScreenCover: state => {
            if (!state.wideScreenCover){
                state.wideScreenCover = localStorage.getItem('wideScreenCover');
            }
            return state.wideScreenCover;
        },
    },
    mutations: {
        [SET_WIDE_SCREEN_COVER](state, wideScreenCover){
            state.wideScreenCover = wideScreenCover;
            localStorage.setItem('wideScreenCover', wideScreenCover);
        },
    }
}
export default theme