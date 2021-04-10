/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/8 21:50
 */

import general from "./general";

export default {
  // eslint-disable-next-line no-unused-vars
  install(Vue, options) {
    Vue.prototype.$STATE = general.STATE
  }
}
