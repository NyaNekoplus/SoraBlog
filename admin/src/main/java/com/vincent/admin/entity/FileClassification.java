package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 10:51
 */
@TableName(value = "s_file_classification")
@Data
public class FileClassification extends SuperEntity<FileClassification>{
    private static final long serialVersionUID = 1L;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 模块分类名
     */
    private String classificationName;

    /**
     * 存储路径
     */
    private String url;
}
