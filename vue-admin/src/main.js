import Vue from 'vue'
import App from './App.vue'

import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'

Vue.prototype.$tinymce = tinymce
Vue.use(VueTinymce)


import router from './router'
import './router/authentication'
import vuetify from './plugins/vuetify'
import './plugins'

import './directive'

import VuetifyMessage from './components/message/index';
Vue.use(VuetifyMessage.register);
//Vue.prototype.$message = VuetifyMessage;
/*
Vue.use(VuetifyMessage, {
  timeout: 2000,
  property: '$message', // property: '$message' will create property with this name in Vue prototype
});
*/

import store from './store'
import { sync } from 'vuex-router-sync'

Vue.config.productionTip = false
import prototype from "./util/prototype";
Vue.use(prototype);
import VueClipboard from "vue-clipboard2";
Vue.use(VueClipboard);
sync(store, router)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
