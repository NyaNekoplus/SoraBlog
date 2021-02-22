package com.vincent.admin.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 12:57
 */

@Data
public class ArticleVO extends BaseVO<ArticleVO>{

    private String title;

    private String link;

    private Integer language;

    private String summary;

    private String content;

    private String contentMd;

    private Boolean enableComment;

    private Boolean isDraft;

    private Boolean isTop;

}
