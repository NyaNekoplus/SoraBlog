module.exports = {
  lintOnSave: false,
  devServer: {
    port: 9528,
    disableHostCheck: true,
  },
  // 配置webpack打包
  configureWebpack: (config) => {
    // 取消console打印
    config.optimization.minimizer[0].options.terserOptions.compress.drop_console = true
  },

  //publicPath: process.env.NODE_ENV === "production" ? "/solar/" : "./",
  /* 输出文件目录：在npm run build时，生成文件的目录名称 */
  outputDir: "sora-admin",
  /*
  // 放置生成的静态资源 (json、css、img、fonts) 的 (相对于 outputDir 的) 目录
  assetsDir: "static",
  configureWebpack: {
    resolve: {
      alias: {
        "@": resolve("src")
      }
    }
  },
  */
  //修改或新增html-webpack-plugin的值，在index.html里面能读取htmlWebpackPlugin.options.title
  chainWebpack: config =>{
    config.plugin('html')
      .tap(args => {
        args[0].title = "Admin | すべて遠き理想郷";
        return args;
      })
  },
  /* 是否在构建生产包时生成 sourceMap 文件，false将提高构建速度 */
  productionSourceMap: false,

  transpileDependencies: ['vuetify'],
}
