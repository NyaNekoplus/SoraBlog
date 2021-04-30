import {SET_ABOUT} from './mutation-types';

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
                link: '/Category',
                children: [
                    {
                        name: 'Life',
                        icon: 'fa fa-commenting-o',
                        link: '/Category/Life',
                    },
                    {
                        name: 'Tech',
                        icon: 'fa fa-file-text-o',
                        link: '/Category/Tech',
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
        tiebaEmoji: {
            'excited': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_excited.png',
            'despise': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_despise.png',
            'nervous': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_nervous.png',
            'thumb': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_thumb.png',
            'money': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_money.png',
            'rainbow': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_rainbow.png',
            'rose': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_rose.png',
            'tongue': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_tongue.png',
            'theblackline': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_theblackline.png',
            'tear': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_tear.png',
            'scared': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_scared.png',
            'surprised': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_surprised.png',
            'spray': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_spray.png',
            'spit': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_spit.png',
            'smilingeyes': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_smilingeyes.png',
            'sleep': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_sleep.png',
            'sinister': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_sinister.png',
            'se': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_se.png',
            'rmb': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_rmb.png',
            'reluctant': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_reluctant.png',
            'rbq': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_rbq.png',
            'naive': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_naive.png',
            'ku': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_ku.png',
            'huaji': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_huaji.png',
            'hu': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_hu.png',
            'han': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_han.png',
            'haha': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_haha.png',
            'noob': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_noob.png',
            'doubt': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_doubt.png',
            'coffee': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_coffee.png',
            'bbd': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_bbd.png',
            'smile': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_smile.png',
            'angry': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_angry.png',
            'aa': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_aa.png',
            'happy': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_happy.png',
            'grievance': 'https://cdn.jsdelivr.net/gh/NyaNekoplus/cdn@0.0.1/img/emoji/tieba_grievance.png',
        }
    },
    getters: {
        navbarItems: state => {
            return state.navbarItems
        },
        links: state => {
            return state.links;
        },
        about: state => {
            return state.about;
        },
        tiebaEmoji: state => {
            return state.tiebaEmoji
        }
    },
    mutations: {
        [SET_ABOUT](state, about) {
            state.about = about;
        },
    }
}
export default data