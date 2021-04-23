import Vue from 'vue'
import App from './App.vue'

import router from './router'

/*
import Viewer from "v-viewer";
import 'viewerjs/dist/viewer.min.css'
Vue.use(Viewer);
Viewer.setDefaults({
  Options: { "inline": true, "button": true, "navbar": true, "title": true, "toolbar": true, "tooltip": true, "movable": true, "zoomable": true, "rotatable": true, "scalable": true, "transition": true, "fullscreen": true, "keyboard": true, "url": "data-source" }
});
 */
//import './styles/style.css'
//import './assets/fonts/SAKURASO.old/icon.css'
Vue.config.productionTip = false

import prototype from "./utils/prototype";
Vue.use(prototype);
import VueClipboard from "vue-clipboard2";
Vue.use(VueClipboard);
import store from "./store";

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
