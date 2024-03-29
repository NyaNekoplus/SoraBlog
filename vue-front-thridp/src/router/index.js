/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:17
 */

import Vue from "vue";
import Router from "vue-router"
import indexLayout from "@/layouts/sora/index"
import otherLayout from "../layouts/sora/other";
import formLayout from "@/layouts/pixiv/index";
Vue.use(Router);


const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    scrollBehavior: (to, from, savedPosition) => {
        console.log('to'+ to.hash)
        if (to.hash) return {
            selector: to.hash,
            behavior: 'smooth'
        }
        if (savedPosition) return savedPosition

        return { x: 0, y: 0 }
    },
    routes: [
        {
            path: '',
            component: indexLayout,
            children: [
                { path: '/', component:()=>import('@/views/index') },
            ]
        },
        {
            path: '',
            component: otherLayout,
            children: [
                { path: '/search/:keyword', component:()=>import('@/views/result') },
                { path: '/tags', component:()=>import('@/views/tag') },
                { path: '/category', component:()=>import('@/views/categories') },
                { path: '/category/:title', component:()=>import('@/views/category') },
                { path: '/link', component:()=>import('@/views/links') },
                { path: '/donate', component:()=>import('@/views/donate') },
                { path: '/about', component:()=>import('@/views/about') },
                { path: '/about/:page', component:()=>import('@/views/about') },
                { path: '/blog/:title', component:()=>import('@/views/page') },
                { path: '/blog/:title/:page', component:()=>import('@/views/page') },
                { path: '/board', component:()=>import('@/views/board') },
                { path: '/board/:page', component:()=>import('@/views/board') },
                { path: '/timeline', component:()=>import('@/views/timeline') },
            ]
        },
        {
            path: '',
            component: formLayout,
            children: [
                { path:'/login', component:()=>import('@/views/login') },
                { path:'/register', component:()=>import('@/views/register') },
            ]
        },
        { path: '/404', component: () => import('@/views/404') },
        { path: '/*', component: () => import('@/views/404') },

    ],

})
/*
function trailingSlash (str) {
    return str.endsWith('/') ? str : str + '/'
}
router.beforeEach((to, from, next) => {
    return to.path.endsWith('/') ? next() : next(trailingSlash(to.path))
})
*/
export default router