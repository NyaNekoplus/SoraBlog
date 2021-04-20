/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/14 8:20
 */

import permission from './permission'

const install = function(Vue) {
  Vue.directive('permission', permission)
}

if (window.Vue) {
  window['permission'] = permission
  Vue.use(install); // eslint-disable-line
}

permission.install = install
export default permission

