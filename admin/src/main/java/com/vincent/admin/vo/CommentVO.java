package com.vincent.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/1/24 14:37
 */

@Data
public class CommentVO extends BaseVO<CommentVO>{

    private Long userId;

    private String userName;

    private Long fromUid;

    private Long toUid;

    private Long parentId;

    private Long articleId;

    private String content;

    /**
     * 评论目标：0为文章，1为评论
     */
    private Boolean targetType;
}
