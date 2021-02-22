package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/1/28 15:00
 */

@Data
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private String nickName;

    private String password;

    // male: 1, female:0
    //private Boolean gender;

    private String email;

    private String os;

    private String browser;

    private String ipAddress;

    private String lastLoginIp;

    private String avatarPath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastVisitDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signupDate;

    private Boolean isAdmin;

    public BlogUser(){
        this.lastVisitDate = this.signupDate = new Date();
    }
}
