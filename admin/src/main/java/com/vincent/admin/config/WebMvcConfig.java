package com.vincent.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Vincent Tsai
 * @date 2021/1/19 16:01
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value(value = "${file.upload-folder}")
    private String uploadFolder;
    @Value(value = "${file.static-path}")
    private String staticPath;

    /*
    @Override // 前后台拦截
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CrosInterceptor())
                .addPathPatterns("/**");
    }*/
    // 线上使用nginx
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(staticPath).addResourceLocations("file:/"+uploadFolder);
    }
//已在nginx中配置跨域
    /*
    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
    }
*/

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有的当前站点的请求地址，都支持跨域访问。
                .allowedOrigins("*") // 所有的外部域都可跨域访问。 如果是localhost则很难配置，因为在跨域请求的时候，外部域的解析可能是localhost、127.0.0.1、主机名
                .allowCredentials(true) // 是否支持跨域用户凭证
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT") // 当前站点支持的跨域请求类型是什么
                .maxAge(3600); // 超时时长设置为1小时。 时间单位是秒。
    }

}
