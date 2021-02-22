package com.vincent.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.BlogUser;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:20
 */


public interface UserService extends IService<BlogUser> {

    String login(String username, String password);

    String register(BlogUser user);
}
