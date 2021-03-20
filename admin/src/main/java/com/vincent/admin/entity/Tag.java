package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/2/16 15:23
 */

@Data
@TableName(value = "s_tag")
public class Tag extends SuperEntity<Tag> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer clickCount;

    private Integer sort;

    public Tag(String name) {
        super();
        this.name = name;
    }

}
