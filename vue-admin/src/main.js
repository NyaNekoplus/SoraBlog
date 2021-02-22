import Vue from 'vue'
import App from './App.vue'

import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'

Vue.prototype.$tinymce = tinymce
Vue.use(VueTinymce)


import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'


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

sync(store, router)

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
