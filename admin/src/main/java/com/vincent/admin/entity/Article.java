package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "s_blog")
public class Article extends SuperEntity<Article> {

    private static final long serialVersionUID = 1L;

    private String title;

    private String link;

    private Integer lang;

    private String summary;

    private String content;

    private String contentMd;

    private String tagUid; // uids

    private String categoryUid;

    private Long imageUid;

    private Boolean enableComment;

    private Boolean isDraft;

    private Integer level; // 置顶等级

    private Integer viewCount;

    private Boolean isOriginal; // 是否原创

    private String author;

    private String articleSource; // 文章来源

    private Integer sort;

    private Integer type; // 0:博客，1：推广

    private String outsideLink;

    @TableField(exist = false)
    private List<Tag> tagList;

}
