package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/1/28 15:00
 */

@Data
@TableName("s_user")
public class User extends SuperEntity<User> {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Long avatar; // 头像uid

    private String email;

    private Date birthday;

    private String phoneNumber;

    private String authCode; // email authenticate code

    private String introduction; // 个人介绍,用于友链

    private Integer loginCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private String lastLoginIp;

    private Integer status; // 是否在线

    private String source; // 个人资料来源

    private String qqNumber;

    private String wechat;

    private String occupation;

    private Integer enableComment;

    private String ipSource; // ip归属地

    private String os;

    private String browser;

    private Integer enableEmailNotification; // 开启邮件通知

    private Integer userProxy; // 0:普通用户 1：测试用户 2：博主

    @TableField(exist = false)
    private String avatarUrl;

    public User(){
        super();
        this.lastLoginTime = new Date();
    }
}
