package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.log.Log;
import com.vincent.admin.annotation.record.VisitRecord;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.entity.User;
import com.vincent.admin.enums.Proxy;
import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.interceptor.UserAgentInterceptor;
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
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public String login(@RequestBody UserVO userVO){
        if (StringUtils.isEmpty(userVO.getEmail())||StringUtils.isEmpty(userVO.getPassword())){
            return Result.failure("登录邮箱或密码不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",userVO.getEmail());
        //queryWrapper.and((wrapper) -> wrapper.eq("email",userVO.getEmail()));
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
        if (userVO.getUsername().equals("春日野穹")){
            return Result.failure("Deny");
        }
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);
        Map<String, String> map = IpUtil.getOsAndBrowserInfo(request);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper->{
            wrapper.eq("email",userVO.getEmail());
        });
        queryWrapper.last("LIMIT 1");
        User user = userService.getOne(queryWrapper);
        if (user!=null){
            return Result.failure("Back-end: 邮箱已被使用, 换一个吧~");
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
    @VisitRecord(value = "关于我",operation = UserOperation.VISIT_ABOUT_ME)
    public String getAboutMe(){
        return aboutMeService.getAboutMeByLang(1);
    }
    @GetMapping("/getQQInfo")
    public String getQQInfo(@RequestParam(name = "qq", required = false) String qq){
        if (StringUtils.isEmpty(qq))
            return Result.failure("QQ号不能为空");
        if (qq.length() < 7 || qq.length() > 14)
            return Result.failure("一般人的Q号多长心里没b数吗");
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);

        String uri = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins="+qq;
        log.info(uri);
        StringBuilder resultBuilder = new StringBuilder();
        BufferedReader in = null;
        try{
            URL realUrl = new URL(uri);
// 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
// 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
// 建立实际的连接
            connection.connect();// 定义 BufferedReader输入流来读取URL的响应
            InputStreamReader reader = new InputStreamReader(
                    connection.getInputStream(),"gbk");
            in = new BufferedReader(reader);
            String line;
            while((line = in.readLine()) != null) {
                resultBuilder.append(line);
            }
        } catch(Exception e) {
            log.error("发送GET请求出现异常！"+ e);
            e.printStackTrace();
            return Result.failure("腾讯接口抽风了");
        }// 使用finally块来关闭输入流
        finally{
            try{
                if(in != null) {
                    in.close();
                }
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        String result = resultBuilder.toString();
        log.info(result);
        Pattern p = Pattern.compile("portraitCallBack\\(\\{\"\\d+\":(.*)}\\)");
        Matcher m = p.matcher(result);
        if (!m.find()){
            return Result.failure("腾讯接口抽风了");
        }
        List<String> list = JsonUtil.jsonToList(m.group(1),String.class);
        log.info(list.toString());
        String avatarUrl = "http://q.qlogo.cn/headimg_dl?dst_uin="+qq+"&spec=100&img_type=jpg";
        User user = new User();
        user.setQqNumber(qq);
        user.setUsername(list.get(6)); // 用户昵称位于第六位
        user.setQqAvatar(avatarUrl);
        user.setEmail(qq+"@qq.com");
        log.info("请求腾讯接口得到的qq信息："+user);
        redisUtil.setExpire("GET_QQ_INFO"+':'+ip,JsonUtil.objectToJson(user),10,TimeUnit.MINUTES);
        return Result.success("拉取QQ资料成功",user);
    }
/*
    @GetMapping("/getQQInfo")
    public String getQQInfo(@RequestParam(name = "qq", required = false) String qq){
        if (StringUtils.isEmpty(qq))
            return Result.failure("QQ号不能为空");
        if (qq.length() < 7)
            return Result.failure("一般人的Q号多长心里没b数吗");
        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtil.getIpAddr(request);

        String uri = "https://cgi.find.qq.com/qqfind/buddy/search_v3?keyword="+qq;
        log.info(uri);
        restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
        HashMap<String, HashMap<String, HashMap<String,ArrayList<HashMap<String,Object>>>>> json =
                (HashMap<String, HashMap<String, HashMap<String,ArrayList<HashMap<String,Object>>>>>)restTemplate.getForObject(uri, Map.class);
        if (json==null||json.get("resultBuilder")==null)
            return Result.failure("腾讯接口抽风了");
        log.info(json.toString());
        HashMap<String,Object> r = json.get("resultBuilder").get("buddy").get("info_list").get(0);
        log.info(r.toString());;
        log.info(r.get("uin").toString());
        log.info(r.get("url").toString());
        log.info(r.get("age").toString());
        log.info(r.get("country").toString());
        log.info(r.get("gender").toString());
        log.info(r.get("nick").toString());
        log.info(r.get("province").toString());
        log.info(r.get("city").toString());

        User user = new User();
        user.setQqNumber(qq);
        user.setUsername(r.get("nick").toString());
        user.setLocation(r.get("country").toString());
        user.setGender((Integer) r.get("gender"));
        user.setAge((Integer) r.get("age"));
        user.setQqAvatar(r.get("url").toString());
        log.info("请求腾讯接口得到的qq信息："+user);
        redisUtil.setExpire("GET_QQ_INFO"+':'+ip,JsonUtil.objectToJson(user),10,TimeUnit.MINUTES);
        return Result.success("拉取QQ资料成功",user);
    }
 */
}
