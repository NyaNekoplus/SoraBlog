package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.About;
import com.vincent.admin.entity.User;
import com.vincent.admin.mapper.UserMapper;
import com.vincent.admin.service.AboutMeService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/4/19 20:06
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private AboutMeService aboutMeService;

    @GetMapping("/getAboutMe")
    public String getAboutMe(){
        return aboutMeService.getAboutMeByLang(1);
    }

    @PostMapping("/updateAboutMe")
    public String updateAboutMe(@RequestBody About about){
        return aboutMeService.updateAboutMe(about);
    }

    @PostMapping("/getUserListByPage")
    public String getUserListByPage(@RequestBody UserVO userVO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enableComment",userVO.getEnableComment());
        Page<User> page = new Page<>();
        page.setSize(userVO.getPageSize());
        page.setCurrent(userVO.getCurrentPage());
        String result = userService.getUserByPageWithAvatar(page,userVO.getEnableComment());
        return result;
        /*
        IPage<User> userListPage = userService.page(page,queryWrapper);
        List<User> userList = userListPage.getRecords();
        userList.forEach(user -> {
            if (user.getAvatar()!=null){
                user.setAvatarUrl();
            }
        });
        return Result.success("获取用户列表成功",userListPage);

         */
    }
}
