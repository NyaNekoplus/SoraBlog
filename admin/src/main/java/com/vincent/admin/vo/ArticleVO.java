package com.vincent.admin.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 12:57
 */

@Data
public class ArticleVO extends BaseVO<ArticleVO>{

    private String categoryUid;

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

}
