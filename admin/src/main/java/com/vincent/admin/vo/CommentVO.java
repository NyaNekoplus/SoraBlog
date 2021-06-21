package com.vincent.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Vincent Tsai
 * @date 2021/1/24 14:37
 */

@Data
public class CommentVO extends BaseVO<CommentVO>{

    private Long userUid;

    private Long toUserUid;

    private String username;

    private Long toUid;

    private Long rootUid;

    private Long blogUid;

    private String content;

    private String source; // 留言板、文章、关于
    /**
     * 评论目标：0为文章，1为评论
     */
    //private Integer targetType;
}
