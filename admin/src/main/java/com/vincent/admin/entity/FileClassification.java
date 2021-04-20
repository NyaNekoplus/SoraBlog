package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.vincent.admin.vo.FileClassificationVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 10:51
 */
@TableName(value = "s_file_classification")
@Data
@NoArgsConstructor
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

    public FileClassification(FileClassificationVO vo){
        this.classificationName = vo.getClassificationName();
        this.projectName = vo.getProjectName();
        this.url = vo.getUrl();
    }
}
