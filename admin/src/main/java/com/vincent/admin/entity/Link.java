package com.vincent.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 14:33
 */

@Data
@TableName(value = "s_link")
public class Link extends SuperEntity<Link>{

    private static final long serialVersionUID = 1L;

    private Long userUid; //友链拥有者

    private Long imageUid; //站点图片

    private Integer clickCount;

    private Integer status; // 0处理中 / 1上线 / 2已删除

    private String url; // 链接

    private String email; // 站长邮箱

    private String introduction; // 友链简介

    private Integer sort;

    private String source; // 评论来源： about、文章、留言板

}
