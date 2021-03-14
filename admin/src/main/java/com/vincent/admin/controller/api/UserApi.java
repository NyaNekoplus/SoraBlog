package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.User;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.IpUtil;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:34
 */

@RestController
@CacheConfig(cacheNames = "user")
@RequestMapping("/front/user")
@Slf4j
public class UserApi {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    //@Cacheable("")
    String login(@RequestBody UserVO userVO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and((wrapper) -> wrapper.eq("userName",userVO.getUserName()).or().eq("email",userVO.getUserName()));
        User user = userService.getOne(queryWrapper);
        System.out.println(userVO);
        System.out.println(user);
        if (user == null){
            return Result.failure("用户不存在");
        }
        if(!StringUtils.isEmpty(userVO.getPassword()) && user.getPassword().equals(userVO.getPassword())){
            HttpServletRequest request = RequestHolder.getRequest();
            String ip = IpUtil.getIpAddr(request);
            Map<String,String> userMap = IpUtil.getOsAndBrowserInfo(request);
            user.setBrowser(userMap.get("BROWSER"));
            user.setOs(userMap.get("OS"));
            user.setLastLoginIp(ip);
            user.setLastVisitDate(new Date());
            user.updateById();

            //过滤密码
            user.setPassword("");
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String msg = "登錄成功, token: " + uuid;
            log.info(msg);
            return Result.success(msg,uuid);

        }
        else {
            String msg = "账号或密碼錯誤";
            return Result.failure(msg);
        }
    }

    @PostMapping("register")
    String register(@RequestBody UserVO userVO){
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setNickName(userVO.getNickName());
        user.setPassword(userVO.getPassword());
        user.setEmail(userVO.getEmail());
        //user.setSignupDate(userVO.getSignupDate());
        //user.setLastVisitDate(userVO.getLastVisitDate());
        user.setIsAdmin(true);

        boolean isSaved = userService.save(user);
        if(isSaved){
            return Result.success("注册成功");
        }
        else {
            return Result.failure("注册失败");
        }
    }
}
