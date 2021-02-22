package com.vincent.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.Comment;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/15 20:50
 */
public interface CommentService extends IService<Comment> {

    List<Comment> findAllComment();

    IPage<Comment> findCommentByPage(Page<Comment> page, QueryWrapper<Comment> wrapper);
}
