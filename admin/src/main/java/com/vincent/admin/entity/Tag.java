package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vincent Tsai
 * @date 2021/2/16 15:23
 */

@Data
@NoArgsConstructor
@TableName(value = "s_tag")
public class Tag extends SuperEntity<Tag> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer clickCount;

    private Integer weight;


    public Tag(String name) {
        super();
        this.name = name;
    }

}
