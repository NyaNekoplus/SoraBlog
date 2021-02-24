/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:56
 */

const VuetifyLoaderPlugin = require('vuetify-loader/lib/plugin')

module.exports = {
    lintOnSave: false,
    devServer: {
        port: 8082,
        disableHostCheck: true,
    },
    configureWebpack: {
        plugins: [
            new VuetifyLoaderPlugin()
        ],
    },


    transpileDependencies: ['vuetify'],
}