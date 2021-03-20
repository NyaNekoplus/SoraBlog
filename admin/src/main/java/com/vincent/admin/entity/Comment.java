package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/15 20:34
 */

@Data
@TableName(value = "s_comment")
public class Comment extends SuperEntity<Comment> {

    private static final long serialVersionUID = 1L;

    private Long userUid;

    private Long toUid;

    private Long toUserUid; //回复某个人的用户uid，冗余处理

    private Long rootUid;

    private Long blogUid;

    private String content;
    /**
    * 评论目标：0为文章，1为评论
    */
    private Integer targetType;

    private Integer status; // 存在 / 已删除

    private String source; // 评论来源： about、文章、留言板

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private List<Comment> replyList;

}
