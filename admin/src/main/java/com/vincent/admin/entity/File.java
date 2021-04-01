package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 16:45
 */

@TableName(value = "s_file")
@Data
public class File extends SuperEntity<File>{

    private static final long serialVersionUID = 1L;

    private String originalName;

    private String newName;

    private String url;

    private String extension;

    private Long fileSize;

    private String qiNiuUrl;

    private Long fileClassificationUid; // 上传者

    private Long userUid; // 上传者

    private Integer status;


}
