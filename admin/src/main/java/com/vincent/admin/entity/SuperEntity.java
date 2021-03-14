package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/3/13 12:15
 */

@Data
@SuppressWarnings("rawtypes")
public class SuperEntity<T extends Model> extends Model {

    private static final long serialVersionUID = -4851055162892178225L;

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public SuperEntity(){
        this.createTime = new Date();
    }

}
