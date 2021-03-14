package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/20 16:30
 */

@Data
public class Article extends SuperEntity<Article> {

    private static final long serialVersionUID = 1L;

    private String category;

    private String link;

    private String title;

    private Integer language;

    private String summary;

    private String content;

    private String contentMd;

    private Boolean enableComment;

    private Boolean isDraft;

    private Boolean isTop;

    private Integer viewCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private List<Tag> tagList;

}
