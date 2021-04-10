/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/31 20:59
 */
import {SET_WIDE_SCREEN_COVER, SET_THEME_WIDGET_STATE} from "./mutation-types";

const theme = {
    state: {
        isThemeWidgetShow: true,
        wideScreenCover: false,
    },
    getters: {
        isThemeWidgetShow: state => {
            return state.isThemeWidgetShow;
        },
        wideScreenCover: state => {
            if (!state.wideScreenCover){
                state.wideScreenCover = JSON.parse(localStorage.getItem('wideScreenCover'));
            }
            return state.wideScreenCover;
        },
    },
    mutations: {
        [SET_WIDE_SCREEN_COVER](state, wideScreenCover){
            state.wideScreenCover = wideScreenCover;
            localStorage.setItem('wideScreenCover', wideScreenCover);
        },
        [SET_THEME_WIDGET_STATE](state, isThemeWidgetShow){
            state.isThemeWidgetShow = isThemeWidgetShow;
        },
    }
}
export default theme