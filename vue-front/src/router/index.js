import Vue from "vue";
import Router from "vue-router"
import SoraHome from "@/view/home"
Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: SoraHome, // layout
            children: [
                { path:'/',component:()=>import('@/view/index') },
                { path:'/about',component:()=>import('@/view/about') },
                { path:'/tech',component:()=>import('@/view/tech') },
                { path:'/life',component:()=>import('@/view/life') },

            ]
        },
        { path:'/login',component:()=>import('@/view/login') },
        { path:'/register',component:()=>import('@/view/register') },
        { path: '/404', component: () => import('@/view/404') },
        { path: '/*', component: () => import('@/view/404') },

    ],
})
/*
router.beforeEach((to, from, next) => {
    if (to.path === '/login'){
        next();
    }else {
        let token = sessionStorage.getItem('token');
        if (token === 'null' || token === ''){
            next('/login');
        }else {
            next();
        }
    }
})
*/

export default router;