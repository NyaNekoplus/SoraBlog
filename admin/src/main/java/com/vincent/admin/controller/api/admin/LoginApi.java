package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.User;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Vincent Tsai
 * @date 2021/4/12 22:05
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class LoginApi {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    String login(HttpServletRequest request,
                 @RequestParam(value = "username",required = false)String username,
                 @RequestParam(value = "password",required = false)String password,
                 @RequestParam(value = "rememberMe",required = false)Boolean rememberMe){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.failure("账号或密码不能为空");
        }
        String ip = IpUtil.getIpAddr(request);
        String failureTime = redisUtil.get("LOGIN_FAILURE_TIME" + ':' + ip);
        if (!StringUtils.isEmpty(failureTime)){
            int time = Integer.parseInt(failureTime);
            if (time >= 5){
                return Result.failure("失败超过5次，30分钟内禁止登陆");
            }
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.and((w) -> w.eq("username",username).or().eq("email",username));
        User user = userService.getOne(wrapper);
        if (user == null){
            return Result.failure("账号不存在，登陆失败!剩余尝试次数："+getRestNumberOfTryLogin(request));
        }
        if (user.getUserProxy()==0){  // 0 为普通用户
            return Result.failure("权限不足,登陆失败");
        }
        if(user.getPassword().equals(MD5Util.string2MD5(password))){
            Map<String,String> userMap = IpUtil.getOsAndBrowserInfo(request);
            user.setBrowser(userMap.get("BROWSER"));
            user.setOs(userMap.get("OS"));
            user.setLastLoginIp(ip);
            user.setLastLoginTime(new Date());
            user.updateById();

            //过滤密码
            user.setPassword("");
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String msg = "Back-end: 登錄成功, token: " + uuid;
            //ACTIVE_USER
            long expireTime = rememberMe?24:1;
            redisUtil.setExpire("LOGIN_TOKEN"+':'+uuid, JsonUtil.objectToJson(user),expireTime, TimeUnit.HOURS);
            log.info(msg);
            return Result.success(msg,uuid);
        }
        else {
            return Result.failure("Back-end: 账号或密碼錯誤，登陆失败!剩余尝试次数："+getRestNumberOfTryLogin(request));
        }
    }
    Integer getRestNumberOfTryLogin(HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        String failureTime = redisUtil.get("LOGIN_FAILURE_TIME" + ':' + ip);
        int total = 5;
        if (StringUtils.isEmpty(failureTime)){
            --total;
            redisUtil.setExpire("LOGIN_FAILURE_TIME" + ':' + ip,"1",30, TimeUnit.MINUTES);
        }else {
            Integer count = Integer.parseInt(failureTime) + 1;
            total-=count;
            redisUtil.setExpire("LOGIN_FAILURE_TIME" + ':' + ip,count.toString(),30, TimeUnit.MINUTES);
        }
        return total;
    }

    @GetMapping("/getInfo")
    String getInfo(@RequestParam(name = "token",required = false) String token){
        if (StringUtils.isEmpty(token)){
            return Result.failure("无法获取用户信息");
        }
        String user = redisUtil.get("LOGIN_TOKEN"+':'+token);
        log.info("Back-end Login从redis中获取的用户数据："+user);
        if (StringUtils.isEmpty(user)){
            log.info("Back-end Login：验证失败，用户数据为空");
            return Result.failure("Back-end Login：Token已失效");
        }else {
            Map<String,Object> map = JsonUtil.jsonToMap(user);
            log.info("Back-end Login：验证成功，redis管理员数据："+map);
            map.put("avatarUrl","https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png");
            return Result.success("Back-end: 获取管理员数据成功",map);
        }
    }

    @PostMapping("/logout")
    String logout(@RequestBody String token){
        /*
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getAttribute("token");*/
        log.info("logout token: "+token);
        if (StringUtils.isEmpty(token)){
            return Result.failure("token为空");
        }
        redisUtil.delete("LOGIN_TOKEN"+':'+token);
        return Result.success("登出成功");
    }
}
