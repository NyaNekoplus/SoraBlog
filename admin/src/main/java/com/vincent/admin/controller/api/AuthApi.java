package com.vincent.admin.controller.api;

import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.util.RedisUtil;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/3/13 16:29
 */
//authenticate

@RestController
@RequestMapping("/front/auth")
@Slf4j
public class AuthApi {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/verify/{token}") // 验证token是否仍有效
    public String loginAuthenticate(@PathVariable("token") String token){
        String user = redisUtil.get("LOGIN_TOKEN"+':'+token);
        log.info("Authenticate Api从redis中获取的用户数据："+user);
        if (StringUtils.isEmpty(user)){
            log.info("Authenticate Api：验证失败，用户数据为空");
            return Result.failure("Back-end: Token已失效");
        }else {
            Map<String,Object> map = JsonUtil.jsonToMap(user);
            log.info("Authenticate Api：验证成功，redis数据："+map);
            return Result.success("Back-end: 验证成功",map);
        }
    }
}
