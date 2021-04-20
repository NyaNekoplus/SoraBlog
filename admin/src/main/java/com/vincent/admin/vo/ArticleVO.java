package com.vincent.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 12:57
 */

@Data
public class ArticleVO extends BaseVO<ArticleVO>{

    private Long categoryUid;

    private String title;

    private String link;

    private Integer lang;

    private String summary;

    private String content;

    private String contentMd;

    private Boolean enableComment;

    private Boolean isDraft;

    private Integer level;

    private List<TagVO> tagList;

    private Long coverUid;

    private Map<String,Boolean> sortMap;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
/*
    private String[] sortBy;

    private Boolean[] sortDesc;*/

}
