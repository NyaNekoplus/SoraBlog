package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.entity.User;
import com.vincent.admin.mapper.UserMapper;
import com.vincent.admin.service.SystemConfigService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:22
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private SystemConfigService systemConfigService;
    @Override
    public String getUserByPageWithAvatar(Page<User> page, Boolean enableComment) {
        IPage<User> userListPage = baseMapper.getUserByPageWithAvatar(page,enableComment);
        List<User> userList = userListPage.getRecords();
        SystemConfig config = systemConfigService.getConfig((long) 1);
        userList.forEach(user -> {
            if (user.getAvatarUrl() == null){
                user.setAvatarUrl(config.getDefaultAvatarUrl());
            }else {
                user.setAvatarUrl(config.getLocalImageBaseUrl()+user.getAvatarUrl());
            }
        });
        return Result.success("获取用户列表成功",userListPage);
    }

    @Override
    public String register(User user) {

        return null;
    }
}
