package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.entity.User;
import com.vincent.admin.enums.Proxy;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.service.AboutMeService;
import com.vincent.admin.service.FileService;
import com.vincent.admin.service.SystemConfigService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.*;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:34
 */

@RestController("FrontUserApi")
@CacheConfig(cacheNames = "user")
@RequestMapping("/front/user")
@Slf4j
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AboutMeService aboutMeService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public String login(@RequestBody UserVO userVO){
        if (StringUtils.isEmpty(userVO.getPassword())||StringUtils.isEmpty(userVO.getPassword())){
            return Result.failure("用户名或密码不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and((wrapper) -> wrapper.eq("username",userVO.getUsername()).or().eq("email",userVO.getUsername()));
        User user = userService.getOne(queryWrapper);
        if (user == null){
            return Result.failure("Back-end: 用户不存在");
        }
        if(user.getPassword().equals(MD5Util.string2MD5(userVO.getPassword()))){
            HttpServletRequest request = RequestHolder.getRequest();
            String ip = IpUtil.getIpAddr(request);
            Map<String,String> userMap = IpUtil.getOsAndBrowserInfo(request);
            user.setBrowser(userMap.get("BROWSER"));
            user.setOs(userMap.get("OS"));
            user.setLastLoginIp(ip);
            user.setIpSource(IpUtil.getCityInfo(ip));
            user.setLoginCount(user.getLoginCount()+1);
            user.setLastLoginTime(new Date());
            user.updateById();

            SystemConfig config;
            if (user.getAvatar() != null){
                config = systemConfigService.getConfig((long) 1);
                File avatar = fileService.getById(user.getAvatar());
                if (avatar != null){
                    if (avatar.getJsDelivrUrl() != null){
                        user.setAvatarUrl(avatar.getJsDelivrUrl());
                    }else if (avatar.getUrl() != null){
                        user.setAvatarUrl(config.getLocalImageBaseUrl()+avatar.getUrl());
                    }else {
                        user.setAvatarUrl(config.getDefaultAvatarUrl());
                    }
                }
            }else {
                config = systemConfigService.getConfig((long) 1);
                user.setAvatarUrl(config.getDefaultAvatarUrl());
            }
            //过滤密码
            user.setPassword("");
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String msg = "登錄成功";
            //ACTIVE_USER
            int timeout = userVO.getRemember()?48:1;

            redisUtil.setExpire("LOGIN_TOKEN"+':'+uuid, JsonUtil.objectToJson(user),timeout, TimeUnit.HOURS);
            log.info(user.getUsername());
            return Result.success(msg,uuid);

        }
        else {
            String msg = "Back-end: 账号或密碼錯誤";
            return Result.failure(msg);
        }
    }

    @PostMapping("register")
    public String register(@RequestBody UserVO userVO){
        if (userVO.getUsername().length()<2||userVO.getUsername().length()>16 ||
                userVO.getPassword().length()<6||userVO.getPassword().length()>32){
            return Result.failure("Back-end: 注册失败，用户名或密码的长度不符合");
        }
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);
        Map<String, String> map = IpUtil.getOsAndBrowserInfo(request);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper->{
            wrapper.eq("username",userVO.getUsername()).or().eq("email",userVO.getEmail());
        });
        queryWrapper.last("LIMIT 1");
        User user = userService.getOne(queryWrapper);
        if (user!=null){
            return Result.failure("Back-end: 注册失败，用户已存在");
        }
        user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(MD5Util.string2MD5(userVO.getPassword()));
        user.setAvatar(userVO.getAvatarUid());
        user.setEmail(userVO.getEmail());
        user.setLastLoginIp(ip);
        user.setIpSource(IpUtil.getCityInfo(ip));
        user.setBrowser(map.get("BROWSER"));
        user.setOs(map.get("OS"));
        log.info("MD5值："+user.getPassword());
        //user.setSignupDate(userVO.getSignupDate());
        //user.setLastVisitDate(userVO.getLastVisitDate());
        user.setUserProxy(Proxy.USER);
        log.info("注册用户信息："+user);
        user.insert();

        return Result.success("Back-end: 注册成功");
    }

    @GetMapping("/getAboutMe")
    public String getAboutMe(){
        return aboutMeService.getAboutMeByLang(1);
    }
}
