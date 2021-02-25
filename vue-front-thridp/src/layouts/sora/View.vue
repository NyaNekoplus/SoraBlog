<template>

  <div id="content" class="site-content">
      <router-view :key="$router.path"/>
  </div>

</template>

<script>
/*
 style="background-repeat: repeat;background-image: url(https://view.moezx.cc/images/2018/01/10/star02.png);"
何时需要给router-view组件绑定 key

由Vue文档-动态路由匹配部分可知：

当使用路由参数时，例如从 /user/foo 导航到 /user/bar，原来的组件实例会被复用。因为两个路由都渲染同个组件，比起销毁再创建，复用则显得更加高效。不过，这也意味着组件的生命周期钩子不会再被调用。复用组件时，想对路由参数的变化作出响应的话，你可以简单地 watch (监测变化) $route 对象或使用 2.2 中引入的 beforeRouteUpdate 导航守卫。

由于Vue会复用相同的组件， 将不在执行created, mounted之类的钩子, 这时候就需要通过设置路由钩子beforeRouteUpdate或者监听$route的变化来执行相关方法更新数据。如果不想要复用组件，可以根据需要进行如下设置。
1.设置router-view 的key为$route.path

    从/user/foo => /user/bar, 由于这两个路由的$route.path并不一样, 所以组件被强制不复用,组件会执行created与 mounted中的内容。

    从/user?name=foo => /user?name=bar, 由于这两个路由的$route.path一样, 所以和没设置 key 属性一样, 会复用组件。

2.设置router-view 的key为$route.fullPath

从/user/foo => /user/bar 或者 /user?name=foo => /user?name=bar , 由相应前后跳转的两个路由的$route.fullPath并不一样, 所以组件被强制不复用。这也意味着组件的生命周期钩子会再次被调用，created与 mounted中的内容会被执行。

参考：https://blog.csdn.net/Terenceno/article/details/90033967
*/
import star from '../../assets/star.png'
export default {
  name: "SoraView",
  data: ()=>({
    background: star
  })
}
</script>