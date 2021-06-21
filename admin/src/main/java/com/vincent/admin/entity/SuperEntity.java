package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public SuperEntity(){
        this.createTime = this.updateTime = new Date();
    }

}
