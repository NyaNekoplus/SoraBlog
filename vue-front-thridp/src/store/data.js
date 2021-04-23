import {SET_ABOUT} from "./mutation-types";

/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/22 21:44
 */
const data = {
    state: {
        about: {},
        links: [
            {
                title: 'Github',
                link: '',
                icon: 'https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/github.png'
            },
            {
                title: 'Telegram',
                link: '',
                icon: 'https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/telegram.svg'
            },
            {
                title: 'Twitter',
                link: '',
                icon: 'https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/twitter.png'
            },
            {
                title: 'E-mail',
                link: 'guoxitsai@gmail.com',
                icon: 'https://cdn.jsdelivr.net/gh/moezx/cdn@3.1.9/img/Sakura/images/sns/email.svg'
            },
        ],
        navbarItems: [
            {
                name: '首页',
                icon: 'fa fa-fort-awesome faa-horizontal',
                link: '/',
                children: []
            },
            {
                name: '归档',
                icon: 'fa fa-archive faa-shake',
                link: '/List',
                children: [
                    {
                        name: 'Life',
                        icon: 'fa fa-commenting-o',
                        link: '/Life',
                    },
                    {
                        name: 'Tech',
                        icon: 'fa fa-file-text-o',
                        link: '/Tech',
                    }
                ]
            },
            {
                name: '留言板',
                icon: 'fa fa-pencil-square-o faa-tada',
                link: '/Board',
                children: []
            },
            {
                name: '友情链接',
                icon: 'fa fa-link faa-shake',
                link: '/Link',
                children: []
            },
            {
                name: '功德箱',
                icon: 'fa fa-heart faa-pulse',
                link: '/Donate',
                children: []
            },
            {
                name: '关于',
                icon: 'fa fa-leaf faa-wrench',
                link: '/About',
                children: [
                    {
                        name: '我',
                        icon: 'fa fa-grav',
                        link: '/About',
                        children: []
                    },
                    {
                        name: '监控',
                        icon: 'fa fa-heartbeat',
                        link: '/Watch',
                        children: []
                    },
                    {
                        name: '统计',
                        icon: 'fa fa-area-chart',
                        link: '/Statistics',
                        children: []
                    },
                    {
                        name: '地图',
                        icon: 'fa fa-map-signs',
                        link: '/Map',
                        children: []
                    }
                ]
            },
            {
                name: '标签',
                icon: 'fa fa-android faa-vertical',
                link: '/Tags',
                children: []
            },
            {
                name: '时光轴',
                icon: 'fa fa-film faa-vertical',
                link: '/Timeline',
                children: []
            }
        ],
    },
    getters: {
        navbarItems: state => {
            return state.navbarItems;
        },
        links: state => {
            return state.links;
        },
        about: state => {
            return state.about;
        }
    },
    mutations: {
        [SET_ABOUT](state,about){
            state.about = about;
        },
    }
}
export default data