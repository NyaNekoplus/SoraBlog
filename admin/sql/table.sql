#CREATE DATABASE IF NOT EXISTS sora_blog;

USE sora_blog;

DROP TABLE IF EXISTS `s_user`;
# 严格模式不支持插入0时间
# tinyint 表示1byte的数字

CREATE TABLE `s_user` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码MD5',
  `avatar` bigint unsigned DEFAULT NULL COMMENT '个人头像uid', # 不知道，长度待调整
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `qq_number` varchar(16) DEFAULT NULL COMMENT 'QQ号',
  `age` int unsigned DEFAULT NULL COMMENT '年龄',
  `location` varchar(8) DEFAULT NULL COMMENT 'QQ资料位置',
  `gender` tinyint unsigned DEFAULT 1 COMMENT '性别（qq）',
  `qq_avatar` varchar(255) DEFAULT NULL COMMENT 'qq头像url',
  `phone_number` varchar(16) DEFAULT NULL COMMENT '手机',
  `auth_code` varchar(50) DEFAULT NULL COMMENT '邮箱验证码',
  `introduction` varchar(200) DEFAULT NULL COMMENT '自我简介最多150字',
  `login_count` int unsigned DEFAULT '0' COMMENT '登录次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(15) DEFAULT '127.0.0.1' COMMENT '最后登录IP',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `source` varchar(32) DEFAULT NULL COMMENT '资料来源',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `occupation` varchar(16) DEFAULT NULL COMMENT '职业',
  `enable_comment` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '评论状态 1:正常 0:禁言',
  `ip_source` varchar(64) DEFAULT NULL COMMENT 'ip来源',
  `browser` varchar(32) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `enable_email_notification` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否开启邮件通知 1:开启 0:关闭',
  `user_proxy` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '用户标签：0：普通用户，1：管理员，2：博主 等',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

#首先数据库设计Sort字段，字段默认值0. 然后查询的时候根据字段的降序排序。假如想对哪条数据进行上升排名只需要往对应的sort字段插入当前的时间戳。
#假如想让第一名变成第四名，这时候优势就显示出来了。只需要拿到第四名的后面一名，既:第五名的时间戳，假如为1500只需要将1500+1就OK了。这时候就把第一名换到第四名了。

DROP TABLE IF EXISTS `s_blog`;
CREATE TABLE `s_blog` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `title` varchar(64) NOT NULL COMMENT '博客标题',
  `link` varchar(255) NOT NULL COMMENT '博客链接',
  `lang` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '语言',
  `summary` varchar(200) NOT NULL COMMENT '博客简介', # 根据css调整
  `content` longtext COMMENT '博客内容',
  `content_md` longtext COMMENT 'MarkDown博客内容', # 节约空间，不存html了，转为使用markd和highlight.js解析
  `tag_uid` varchar(64) DEFAULT NULL COMMENT '标签uid', # 该文章下的所有tag的id。之间使用分号或逗号隔开，后台设置tagList时再split循环获取。
  `view_count` int DEFAULT '0' COMMENT '博客浏览量',
  `liked_count` int DEFAULT '0' COMMENT '点赞数',
  `comment_count` int DEFAULT '0' COMMENT '博客评论数',
  `cover_uid` bigint unsigned DEFAULT NULL COMMENT '标题图片uid', # 不知道，长度待调整
  #`status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT '2021-03-18 14:08:05' COMMENT '更新时间',
  `is_original` tinyint unsigned DEFAULT '1' COMMENT '是否原创（0:不是 1：是）',
  `author` varchar(64) DEFAULT 'Vincent Tsai' COMMENT '作者',
  `article_source` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `category_uid` varchar(16) DEFAULT NULL COMMENT '博客分类UID', # 同tag的处理方式
  `level` tinyint unsigned DEFAULT '0' COMMENT '推荐等级(0:正常)',
  `is_draft` tinyint unsigned DEFAULT '1' COMMENT '是否发布：0：否，1：是',
  `enable_comment` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否开启评论(0:否 1:是)',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序字段',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型【0 博客， 1：推广】',
  `outside_link` varchar(255) DEFAULT NULL COMMENT '外链【如果是推广，那么将跳转到外链】',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客表';

DROP TABLE IF EXISTS `s_tag`;
CREATE TABLE `s_tag` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `name` varchar(32) DEFAULT NULL COMMENT '标签内容',
  `click_count` int DEFAULT '0' COMMENT '标签简介',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `weight` int DEFAULT '0' COMMENT '排序字段，越大越靠前',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

DROP TABLE IF EXISTS `s_category`;
CREATE TABLE `s_category` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `name` varchar(8) DEFAULT NULL COMMENT '分类内容',
  `click_count` int DEFAULT '0' COMMENT '分类点击数',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `sort` int DEFAULT '0' COMMENT '排序字段，越大越靠前',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';



DROP TABLE IF EXISTS `s_comment`;
CREATE TABLE `s_comment` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `user_uid` bigint unsigned DEFAULT NULL COMMENT '用户uid',
  `to_uid` bigint unsigned DEFAULT NULL COMMENT '回复某条评论的评论uid',
  `to_user_uid` bigint unsigned DEFAULT NULL COMMENT '回复某个人的用户uid，冗余处理',
  `content` varchar(2048) DEFAULT NULL COMMENT '评论内容',
  `blog_uid` bigint unsigned DEFAULT NULL COMMENT '博客uid',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态，1存在，0删除',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `source` varchar(16) NOT NULL COMMENT '评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0:评论，1：点赞',
  #`target_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '评论类型 1:点赞 0:评论',
  `root_uid` bigint unsigned DEFAULT NULL COMMENT '根评论UID',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';


DROP TABLE IF EXISTS `s_link`;
CREATE TABLE `s_link` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `title` varchar(64) DEFAULT NULL COMMENT '友情链接标题',
  `introduction` varchar(200) DEFAULT NULL COMMENT '友情链接介绍',
  `url` varchar(32) DEFAULT NULL COMMENT '友情链接URL',
  `click_count` int DEFAULT '0' COMMENT '点击数',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `sort` int DEFAULT '0' COMMENT '排序字段，越大越靠前',
  #`link_status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '友链状态： 0 申请中， 1：已上线，  2：已下架',
  `user_uid` bigint unsigned DEFAULT NULL COMMENT '申请用户UID',
  #`admin_uid` bigint unsigned DEFAULT NULL COMMENT '操作管理员UID',
  `email` varchar(32) DEFAULT NULL COMMENT '站长邮箱',
  `image_uid` bigint unsigned DEFAULT NULL COMMENT '网站图标',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='友情链接表';


DROP TABLE IF EXISTS `s_file`;
CREATE TABLE `s_file` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一uid',
  `original_name` varchar(64) DEFAULT NULL COMMENT '旧文件名',
  `new_name` varchar(64) DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) DEFAULT NULL COMMENT '文件本地地址',
  `js_delivr_url` varchar(255) DEFAULT NULL COMMENT 'jsDelivrUrl地址',
  `extension` varchar(5) DEFAULT NULL COMMENT '文件扩展名',
  `file_size` int DEFAULT '0' COMMENT '文件大小',
  `file_classification_uid` bigint unsigned DEFAULT NULL COMMENT '文件分类uid',
  `user_uid` bigint unsigned DEFAULT NULL COMMENT '用户uid',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态:失效/存在',
  `create_time` timestamp NOT NULL DEFAULT '2021-03-18 14:08:05' COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表';