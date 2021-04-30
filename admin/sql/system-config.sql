USE sora_blog;

DROP TABLE IF EXISTS `s_system_config`;
CREATE TABLE `s_system_config` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `qi_niu_access_key` varchar(255) DEFAULT NULL COMMENT '七牛云公钥',
  `qi_niu_secret_key` varchar(255) DEFAULT NULL COMMENT '七牛云私钥',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱账号',
  `email_sender` varchar(255) DEFAULT NULL COMMENT '邮箱发件人用户名',
  `email_password` varchar(255) DEFAULT NULL COMMENT '邮箱密码',
  `smtp_address` varchar(20) DEFAULT NULL COMMENT 'SMTP地址',
  `smtp_port` varchar(6) DEFAULT '1' COMMENT 'SMTP端口',
  `status` tinyint unsigned DEFAULT '1' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `qi_niu_bucket` varchar(255) DEFAULT NULL COMMENT '七牛云上传空间',
  `qi_niu_area` varchar(10) DEFAULT NULL COMMENT '七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0)',
  `upload_qi_niu` tinyint unsigned DEFAULT '1' COMMENT '图片是否上传七牛云 (0:否， 1：是)',
  `upload_local` tinyint unsigned DEFAULT '1' COMMENT '图片是否上传本地存储 (0:否， 1：是)',
  `upload_github` tinyint unsigned DEFAULT '1' COMMENT '图片是否上传本地存储 (0:否， 1：是)',
  `image_priority` varchar(1) DEFAULT '1' COMMENT '图片显示优先级（ 1 展示 七牛云,  0 本地）',
  `qi_niu_image_base_url` varchar(255) DEFAULT NULL COMMENT '七牛云域名前缀：http://images.moguit.cn',
  `local_image_base_url` varchar(255) DEFAULT NULL COMMENT '本地服务器域名前缀：http://localhost:8600',
  `jsdelivr_image_base_url` varchar(255) DEFAULT NULL COMMENT 'jsdelivr域名前缀：https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/landscape.jfif',
  `enable_email_notification` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '是否开启邮件通知(0:否， 1:是)',
  `editor_model` tinyint unsigned NOT NULL DEFAULT 0 COMMENT '编辑器模式，(0：富文本编辑器CKEditor，1：markdown编辑器Veditor)',
  `theme_color` varchar(10) NOT NULL DEFAULT '#409EFF' COMMENT '主题颜色',
  `enable_dashboard_notification` tinyint NOT NULL DEFAULT '0' COMMENT '是否开启仪表盘通知(0:否， 1:是)',
  `dashboard_notification` longtext COMMENT '仪表盘通知【用于首次登录弹框】',
  `default_cover_url` varchar(255) DEFAULT NULL COMMENT '默认封面地址',
  `default_avatar_url` varchar(255) DEFAULT NULL COMMENT '默认头像地址',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';
create unique index uk_system_config on s_system_config( (1) );

DROP TABLE IF EXISTS `s_about_me`;
CREATE TABLE `s_about_me` (
  `uid`  bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `name` varchar(16) NOT NULL DEFAULT 'Vincent Tsai',
  `link` varchar(32) NOT NULL DEFAULT 'https://sora.vin',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像uid', # 不知道，长度待调整
  `motto` varchar(32) DEFAULT NULL COMMENT '格言',
  `lang` tinyint unsigned NOT NULL DEFAULT '1' COMMENT 'language',
  `detail` longtext DEFAULT NULL COMMENT '详细',
  `enable_comment` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否开启评论(0:否 1:是)',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  UNIQUE (`lang`),
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='About me';
#create unique index uk_about_me on s_about_me( (1) );

DROP TABLE IF EXISTS `s_file_classification`;
CREATE TABLE `s_file_classification` (
  `uid`  bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `project_name` varchar(16) DEFAULT NULL COMMENT '项目名',
  `classification_name` varchar(16) DEFAULT NULL COMMENT '分类名',
  `url` varchar(64) DEFAULT NULL COMMENT '分类路径',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件分类表';

DROP TABLE IF EXISTS `s_visit_record`;
CREATE TABLE `s_visit_record` (
  `uid`  bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `user_uid` bigint unsigned DEFAULT NULL COMMENT '用户uid',
  `ip` varchar(15) DEFAULT NULL COMMENT 'ip地址',
  `ip_source` varchar(64) DEFAULT NULL COMMENT 'ip归属地',
  `browser` varchar(32) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `operation` varchar(8) DEFAULT NULL COMMENT '用户操作',
  `add_on` varchar(255) DEFAULT NULL COMMENT '附加数据(比如搜索内容)',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Web访问记录表';