package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/4/19 19:25
 */
@Data
@TableName(value = "s_about_me")
public class About extends SuperEntity<About>{

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer lang;

    private String avatarUrl;

    private String motto;

    private String detail;
}
