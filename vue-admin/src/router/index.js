// Imports
import Vue from 'vue'
import Router from 'vue-router'
import { trailingSlash } from '@/util/helpers'
import {
  layout,
  route,
} from '@/util/routes'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash }
    if (savedPosition) return savedPosition

    return { x: 0, y: 0 }
  },
  routes: [
    layout('Default', [
      route('Dashboard'),

      route('Writing', null, 'writing'),
      route('UserList', null, 'users'),
      route('ArticleTable', null, 'articles'),
      // Pages
      route('UserProfile', null, 'profile'),

      route('ImageClassification', null, 'image/classification'),
      route('ImageList', null, 'image/list'),

      route('SystemConfig', null, 'system/config'),
      route('WebsiteConfig', null, 'system/website'),

      // Components
      route('Notifications', null, 'components/notifications'),
      route('Icons', null, 'components/icons'),
      route('Typography', null, 'components/typography'),

      // Tables
      route('Regular Tables', null, 'tables/regular'),

      // Maps
      route('Google Maps', null, 'maps/google'),
    ]),
    layout('Pixiv', [
      route('Login', null, 'login')
    ])
  ],
})
export default router
