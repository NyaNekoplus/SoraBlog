package com.vincent.admin.vo;

import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/4/3 16:41
 */
@Data
public class FileClassificationVO extends BaseVO<FileClassificationVO>{

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
