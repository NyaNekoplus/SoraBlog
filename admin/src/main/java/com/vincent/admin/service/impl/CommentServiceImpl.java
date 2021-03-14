package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Comment;
import com.vincent.admin.mapper.CommentMapper;
import com.vincent.admin.service.CommentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/15 20:49
 */

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Override
    public List<Comment> findAllComment() {
        return baseMapper.selectList(new QueryWrapper<Comment>());
    }

    @Override
    public IPage<Comment> findCommentByPage(Page<Comment> page, QueryWrapper<Comment> wrapper) {

        return null;
    }

    @Override
    @CacheEvict(cacheNames = "comment", allEntries = true)
    public void clearCache() {

    }
}
