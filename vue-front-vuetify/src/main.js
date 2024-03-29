import Vue from 'vue'
import App from './App.vue'

import router from './router'
import vuetify from '@/plugins/vuetify'
import './plugins'

import 'vuetify/dist/vuetify.css'
import './styles/reference.css'

Vue.config.productionTip = false

import store from "./store";

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
