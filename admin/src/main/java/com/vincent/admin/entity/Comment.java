package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long fromUid;

    private Long toUid;

    private Long parentId;

    private Long articleId;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    /**
    * 评论目标：0为文章，1为评论
    */
    private Boolean targetType;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private List<Comment> replyList;

    public Comment(){
        this.commentTime = new Date();
    }
}
