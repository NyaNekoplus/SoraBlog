package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.vincent.admin.entity.BlogUser;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:34
 */

@RestController
@RequestMapping("/front/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    String login(@RequestBody UserVO userVO){
        BlogUser user = userService.getOne(new QueryWrapper<BlogUser>().eq("userName",userVO.getUserName()));
        System.out.println(userVO);
        System.out.println(user);
        if(ObjectUtils.isNotNull(user)){
            if(user.getPassword().equals(userVO.getPassword())){
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                return Result.success("登錄成功",uuid);
            }
            else {
                return Result.failure("密碼錯誤");
            }
        }
        else {
            return Result.failure("用戶不存在");
        }
    }

    @PostMapping("register")
    String register(@RequestBody UserVO userVO){
        BlogUser user = new BlogUser();
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
