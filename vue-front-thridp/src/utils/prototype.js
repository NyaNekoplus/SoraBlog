import general from "@/utils/general";


export default {
    // eslint-disable-next-line no-unused-vars
    install(Vue, options) {
        Vue.prototype.$STATE = general.STATE
    }
}
