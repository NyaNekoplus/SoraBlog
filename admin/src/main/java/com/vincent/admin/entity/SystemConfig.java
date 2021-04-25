package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 17:01
 */
@TableName(value = "s_system_config")
@Data
public class SystemConfig extends SuperEntity<SystemConfig>{

    private static final long serialVersionUID = 1L;

    /**
     * 七牛云公钥
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String qiNiuAccessKey;

    /**
     * 七牛云私钥
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String qiNiuSecretKey;

    /**
     * 七牛云上传空间
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String qiNiuBucket;

    /**
     * 七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0)
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String qiNiuArea;

    /**
     * 图片是否上传七牛云 (0:否， 1：是)
     */
    private Boolean uploadQiNiu;

    /**
     * 图片是否上传本地存储 (0:否， 1：是)
     */
    private Boolean uploadLocal;

    private Boolean uploadGithub;

    private String defaultCoverUrl;
    private String defaultAvatarUrl;

    /**
     * 图片显示优先级（ 0:本地  1: 七牛云 2: Minio）
     */
    private String imagePriority;

    /**
     * 本地存储图片服务器，域名前缀：   http://localhost:8600
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String localImageBaseUrl;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String jsdelivrImageBaseUrl;

    /**
     * 七牛云存储图片服务器，域名前缀： http://images.moguit.cn
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String qiNiuImageBaseUrl;


    /**
     * 邮箱账号
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * 邮箱发件人用户名
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String emailSender;

    /**
     * 邮箱密码
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String emailPassword;

    /**
     * SMTP地址
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String smtpAddress;

    /**
     * SMTP端口
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String smtpPort;

    /**
     * 是否开启邮件通知(0:否， 1:是)
     * 当有新的反馈，友链申请时进行通知，首先需要在系统管理处设置接收通知的邮箱
     */
    private Boolean enableEmailNotification;

    /**
     * 编辑器模式，(0：富文本编辑器CKEditor，1：markdown编辑器Veditor)
     */
    private Integer editorModel;

    /**
     * 主题颜色
     */
    private String themeColor;

    /**
     * 仪表盘通知【首次进入时弹出】
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String dashboardNotification;

    /**
     * 是否开启仪表盘通知【0 关闭，1 开启】
     */
    private Boolean enableDashboardNotification;

}