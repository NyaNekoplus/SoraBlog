package com.vincent.admin.annotation.proxy;

import com.vincent.admin.enums.Proxy;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.util.RedisUtil;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/27 20:37
 */
@Slf4j
@Component
@Aspect
public class ProxyAuthAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut(value = "@annotation(proxyAuth)")
    public void pointcut(ProxyAuth proxyAuth){

    }
    @Around(value = "pointcut(proxyAuth)")
    public Object doAround(ProceedingJoinPoint point, ProxyAuth proxyAuth) throws Throwable {
        HttpServletRequest request = RequestHolder.getRequest();
        String token = request.getHeader("Authorization");
        log.info("token: "+token);
        if (token != null && !token.equals("undefined")) {
            String user = redisUtil.get("LOGIN_TOKEN"+':'+token);
            if (StringUtils.isEmpty(user)){
                return Result.failure("已过期，请重新登录");
            }else {
                Map<String,Object> info = JsonUtil.jsonToMap(user);
                log.info("用户信息： "+info.get("uid"));
                if (info.get("userProxy")!=null){
                    Integer proxy = (Integer) info.get("userProxy");
                    if (proxy.equals(Proxy.USER)){
                        return Result.failure("权限不足, 无法操作");
                    }else {
                        //执行业务
                        return point.proceed();
                    }
                }
                return Result.failure("权限不足, 无法操作");
            }
        }else {
            return Result.failure("请先登录");
        }
    }
}
