package com.vincent.admin.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/4/5 22:18
 */
@Data
public class SystemConfigVO extends BaseVO<SystemConfigVO>{

    private Boolean uploadLocal;

    private Boolean uploadGithub;

    private String localImageBaseUrl;

    private String jsdelivrImageBaseUrl;

    private String imagePriority;

    private String email;

    /**
     * 邮箱发件人用户名
     */
    private String emailSender;

    /**
     * 邮箱密码
     */
    private String emailPassword;

    /**
     * SMTP地址
     */
    private String smtpAddress;

    /**
     * SMTP端口
     */
    private String smtpPort;

    /**
     * 是否开启邮件通知(0:否， 1:是)
     * 当有新的反馈，友链申请时进行通知，首先需要在系统管理处设置接收通知的邮箱
     */
    private String enableEmailNotification;
}
