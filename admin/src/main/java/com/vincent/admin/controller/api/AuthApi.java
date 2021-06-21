package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.EmailAuthInfo;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.entity.User;
import com.vincent.admin.enums.Proxy;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.service.FileService;
import com.vincent.admin.service.SystemConfigService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.*;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private FileService fileService;
    @Autowired
    private RabbitUtil rabbitUtil;

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

    /*@GetMapping("/verify/email/{code}") // 验证token是否仍有效
    public String emailAuthenticate(@PathVariable("code") String code){
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);
        String authInfoJson = redisUtil.get("EMAIL_TOKEN"+':'+ip);
        log.info("Authenticate Api从redis中获取的邮箱验证码："+authInfoJson);
        if (StringUtils.isEmpty(authInfoJson)){
            log.info("Authenticate Api：验证失败，邮箱验证码为空/已过期");
            return Result.failure("Back-end: 邮箱验证码已过期");
        }
        EmailAuthInfo authInfo = JsonUtil.jsonToPojo(authInfoJson, EmailAuthInfo.class);
        if (authInfo.getCode().equals(code)){
            log.info("Authenticate Api：验证成功，邮箱验证码："+authInfo.getCode());
            redisUtil.delete("EMAIL_TOKEN"+':'+ip);
            Map<String,String> OSB = IpUtil.getOsAndBrowserInfo(request);
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            int timeout = 48;

            String qqInfoJson = redisUtil.get("GET_QQ_INFO"+':'+ip);
            if (StringUtils.isEmpty(qqInfoJson)){
                return Result.failure("拉取的QQ资料已过期,验证失败,请重新输入QQ号");
            }
            User qqInfo = JsonUtil.jsonToPojo(qqInfoJson,User.class); // 可以加个try，防null
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",authInfo.getEmail());
            queryWrapper.last("LIMIT 1");
            User exist = userService.getOne(queryWrapper);
            if (exist == null){ // 判断邮箱是否已注册
                User newUser = new User();
                newUser.setUsername(qqInfo.getUsername());
                newUser.setPassword("aoligei");
                newUser.setQqAvatar(qqInfo.getQqAvatar());
                //newUser.setAge(qqInfo.getAge());
                //newUser.setGender(qqInfo.getGender());
                //newUser.setLocation(qqInfo.getLocation());//
                newUser.setEmail(authInfo.getEmail());
                newUser.setLastLoginIp(ip);
                newUser.setIpSource(IpUtil.getCityInfo(ip));
                newUser.setBrowser(OSB.get("BROWSER"));
                newUser.setOs(OSB.get("OS"));
                newUser.setUserProxy(Proxy.USER);
                //log.info("MD5值："+newUser.getPassword());
                log.info("注册用户信息："+newUser);
                newUser.insert();
                redisUtil.setExpire("LOGIN_TOKEN"+':'+uuid, JsonUtil.objectToJson(newUser),timeout, TimeUnit.HOURS);
            }else {
                exist.setUsername(qqInfo.getUsername());
                exist.setQqAvatar(qqInfo.getQqAvatar());
                //exist.setAge(qqInfo.getAge());
                //exist.setGender(qqInfo.getGender());
                //exist.setLocation(qqInfo.getLocation());//
                exist.setBrowser(OSB.get("BROWSER"));
                exist.setOs(OSB.get("OS"));
                exist.setLastLoginIp(ip);
                exist.setIpSource(IpUtil.getCityInfo(ip));
                exist.setLoginCount(exist.getLoginCount()+1);
                exist.setLastLoginTime(new Date());
                exist.updateById();
                redisUtil.setExpire("LOGIN_TOKEN"+':'+uuid, JsonUtil.objectToJson(exist),timeout, TimeUnit.HOURS);
            }
            return Result.success("Back-end: 邮箱验证成功,已自动登录..", uuid);
        }
        log.info("Authenticate Api：验证失败，邮箱验证码："+authInfo.getCode()+':'+code);
        return Result.success("Back-end: 邮箱验证失败,验证码不正确");
    }*/
    @PostMapping("/verify/email") // 验证token是否仍有效
    public String emailAuthenticate(@RequestBody UserVO userVO){
        log.info("uservo:"+userVO);
        if (userVO.getCode()==null||userVO.getCode().length()!=6){
            return Result.failure("Back-end: 验证码长度不正确");
        }
        if (userVO.getUsername().length()<1||userVO.getUsername().length()>16){
            return Result.failure("Back-end: 用户名的长度不正确");
        }
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);
        String authInfoJson = redisUtil.get("EMAIL_TOKEN"+':'+ip);
        log.info("Authenticate Api从redis中获取的邮箱验证码："+authInfoJson);
        if (StringUtils.isEmpty(authInfoJson)){
            log.info("Authenticate Api：验证失败，邮箱验证码为空/已过期");
            return Result.failure("Back-end: 邮箱验证码已过期");
        }
        EmailAuthInfo authInfo = JsonUtil.jsonToPojo(authInfoJson, EmailAuthInfo.class);
        if (authInfo.getCode().equals(userVO.getCode())){
            log.info("Authenticate Api：验证成功，邮箱验证码："+authInfo.getCode());

            Map<String,String> OSB = IpUtil.getOsAndBrowserInfo(request);
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            int timeout = 48;

            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",authInfo.getEmail());
            queryWrapper.last("LIMIT 1");
            User exist = userService.getOne(queryWrapper);
            if (exist == null){ // 判断邮箱是否已注册
                exist = new User();
                if (userVO.getPassword()!=null){
                    if (userVO.getPassword().length()<6||userVO.getPassword().length()>32){
                        return Result.failure("Back-end: 注册失败，密码的长度应处于 [6, 32] 区间内");
                    }
                    exist.setPassword(MD5Util.string2MD5(userVO.getPassword()));
                }else {
                    // qq验证登录的用户的初始密码为邮箱验证码转md5
                    exist.setPassword(MD5Util.string2MD5(authInfo.getCode()));
                }
                exist.setUsername(userVO.getUsername());
                exist.setAvatar(userVO.getAvatarUid());
                exist.setEmail(authInfo.getEmail());
                
                exist.setUserProxy(Proxy.USER);
                log.info("注册用户信息："+exist);
            }else {
                exist.setLoginCount(exist.getLoginCount()+1);
                exist.setLastLoginTime(new Date());
            }
            exist.setQqAvatar(userVO.getQqAvatar());
            exist.setLastLoginIp(ip);
            exist.setIpSource(IpUtil.getCityInfo(ip));
            exist.setBrowser(OSB.get("BROWSER"));
            exist.setOs(OSB.get("OS"));
            //exist.setEnableEmailNotification(true);
            SystemConfig config;
            if (exist.getAvatar() != null){
                config = systemConfigService.getConfig((long) 1);
                File avatar = fileService.getById(exist.getAvatar());
                if (avatar != null){
                    if (avatar.getJsDelivrUrl() != null){
                        exist.setAvatarUrl(avatar.getJsDelivrUrl());
                    }else if (avatar.getUrl() != null){
                        exist.setAvatarUrl(config.getLocalImageBaseUrl()+avatar.getUrl());
                    }else {
                        exist.setAvatarUrl(config.getDefaultAvatarUrl());
                    }
                }
            }else {
                config = systemConfigService.getConfig((long) 1);
                exist.setAvatarUrl(config.getDefaultAvatarUrl());
            }
            userService.saveOrUpdate(exist);
            exist.setPassword(""); // 过滤密码
            redisUtil.setExpire("LOGIN_TOKEN"+':'+uuid, JsonUtil.objectToJson(exist),timeout, TimeUnit.HOURS);
            redisUtil.delete("EMAIL_TOKEN"+':'+ip); // 处理完成后删除验证码缓存
            return Result.success("Back-end: 邮箱验证成功,已自动登录..", uuid);
        }
        log.info("Authenticate Api：验证失败，邮箱验证码："+authInfo.getCode()+':'+userVO.getCode());
        return Result.success("Back-end: 邮箱验证失败,验证码不正确");
    }

    @GetMapping("/sendAuthEmail") // 验证token是否仍有效
    public String sendAuthEmail(@RequestParam(name = "email",required = false) String email){
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);
        String e = redisUtil.get("EMAIL_TOKEN"+':'+ip);
        if (!StringUtils.isEmpty(e)){
            return Result.failure("请求过快- - 好哥哥饶了我罢");
        }
        String authCode = MD5Util.genEmailAuthCode();
        EmailAuthInfo emailAuthInfo = new EmailAuthInfo(email,authCode);
        redisUtil.setExpire("EMAIL_TOKEN"+':'+ip,JsonUtil.objectToJson(emailAuthInfo),3, TimeUnit.MINUTES);
        log.info("email:"+email);
        Map<String,String> info = new HashMap<>();
        info.put("email",email);
        info.put("verificationCode",authCode);
        rabbitUtil.sendVerificationCode(email,info);//todo
        //mailUtil.sendEmail(email,authCode);
        return Result.success("发送邮件成功(3分钟内有效)");
    }

    @RequestMapping("/callback/{platform}")
    public void login(@PathVariable("platform") String platform, HttpServletResponse httpServletResponse){
        log.info("platform: "+platform);
    }
}
