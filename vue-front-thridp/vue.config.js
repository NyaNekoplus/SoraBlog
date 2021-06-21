/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/22 13:56
 */

//const VuetifyLoaderPlugin = require('vuetify-loader/lib/plugin')

module.exports = {
    lintOnSave: false,
    devServer: {
        port: 9529,
        disableHostCheck: true,
        //externals: 'hls.js' // 消去aplayer的警告，hls.js is for play HLS media.
    },
    // 配置webpack打包
    configureWebpack: (config) => {
        // 取消console打印
        config.optimization.minimizer[0].options.terserOptions.compress.drop_console = true
    },
    outputDir: "sora-front",
    chainWebpack: config =>{
        config.plugin('html')
            .tap(args => {
                args[0].title = "悠の空";
                return args;
            })
    },

    /*
    configureWebpack: {
        plugins: [
            new VuetifyLoaderPlugin()
        ],
    },
    transpileDependencies: ['vuetify'],*/
}