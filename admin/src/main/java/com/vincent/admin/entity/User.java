package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/1/28 15:00
 */

@Data
@TableName("BlogUser")
public class User extends SuperEntity<User> {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private String os;

    private String browser;

    private String ipAddress;

    private String lastLoginIp;

    private String avatarPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastVisitDate;

    private Boolean isAdmin;

    public User(){
        super();
        this.lastVisitDate = new Date();
    }
}
