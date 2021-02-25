import Vue from 'vue'
import App from './App.vue'

import router from './router'


import './styles/style.css'
//import './assets/fonts/SAKURASO.old/icon.css'
Vue.config.productionTip = false

import store from "./store";

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
