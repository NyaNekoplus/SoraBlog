package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.User;
import com.vincent.admin.mapper.UserMapper;
import com.vincent.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:22
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public String login(String username, String password) {

        return null;
    }

    @Override
    public String register(User user) {

        return null;
    }
}
