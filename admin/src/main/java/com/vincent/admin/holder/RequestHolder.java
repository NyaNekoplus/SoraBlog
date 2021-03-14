package com.vincent.admin.holder;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vincent Tsai
 * @date 2021/3/12 17:11
 */

//RequestHolder其实也就是类似于项目级别的全局变量。
public class RequestHolder {
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (null == servletRequestAttributes){
            return null;
        }
        return servletRequestAttributes.getRequest();
    }
}
