import Vue from 'vue'
import App from './App.vue'

import router from './router'


import './styles/style.css'
//import './assets/fonts/SAKURASO.old/icon.css'
Vue.config.productionTip = false

import prototype from "./utils/prototype";
Vue.use(prototype);

import store from "./store";

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
