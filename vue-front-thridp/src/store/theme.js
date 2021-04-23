/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/3/31 20:59
 */
import {
    SET_WIDE_SCREEN_COVER,
    SET_THEME_WIDGET_STATE,
    SET_SAKURA_EFFECT,
    SET_SNOW_EFFECT,
    SET_BACKGROUND, SET_DARK
} from "./mutation-types";

const theme = {
    state: {
        isThemeWidgetShow: true,
        wideScreenCover: false,
        sakuraEffect: false,
        snowEffect: false,
        dark: false,

        background: 0,
        images: [
            'background-image: none;',
            'background-image: url("https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/star.png");',//star
            'background-image: url("https://view.moezx.cc/images/2018/01/03/plaid2dbf8.jpg");',//star
            'background-image: url("https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.2/other-sites/api-index/images/me.png");',//dark
        ],
    },
    getters: {
        isThemeWidgetShow: state => {
            return state.isThemeWidgetShow;
        },
        background: state => {
            if (!state.background){
                state.background = JSON.parse(localStorage.getItem('background'));
            }
            return state.images[state.background];
        },
        dark: state => {
            if (!state.dark){
                state.dark = JSON.parse(localStorage.getItem('dark'));
            }
            return state.dark;
        },
        sakuraEffect: state => {
            if (!state.sakuraEffect){
                state.sakuraEffect = JSON.parse(localStorage.getItem('sakuraEffect'));
            }
            return state.sakuraEffect;
        },
        snowEffect: state => {
            if (!state.snowEffect){
                state.snowEffect = JSON.parse(localStorage.getItem('snowEffect'));
            }
            return state.snowEffect;
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
        [SET_SAKURA_EFFECT](state, sakuraEffect){
            state.sakuraEffect = sakuraEffect;
            localStorage.setItem('sakuraEffect', sakuraEffect);
        },
        [SET_SNOW_EFFECT](state, snowEffect){
            state.snowEffect = snowEffect;
            localStorage.setItem('snowEffect', snowEffect);
        },
        [SET_DARK](state, dark){
            state.dark = dark;
            document.getElementById('night-mode-cover').style.visibility = dark?'visible':'hidden';
            console.log('dark: '+document.getElementById('night-mode-cover').style.visibility)
            localStorage.setItem('dark', dark);
        },
        [SET_THEME_WIDGET_STATE](state, isThemeWidgetShow){
            state.isThemeWidgetShow = isThemeWidgetShow;
        },
        [SET_BACKGROUND](state,background){
            state.background = background;
            document.body.style = state.images[background];
            localStorage.setItem('background', background);
        }
    }
}
export default theme