package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 14:30
 */

@Data
@TableName(value = "s_category")
public class Category extends SuperEntity<Category>{

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer clickCount;

    private Integer sort;

}
