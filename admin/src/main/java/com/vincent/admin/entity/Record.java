package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 23:13
 */
@Data
@TableName(value = "s_visit_record")
public class Record extends SuperEntity<Record>{

    private static final long serialVersionUID = 1L;

    private Long userUid;

    private String ip;

    private String ipSource;

    private String browser;

    private String os;

    private String operation;

    private String addOn;
}
