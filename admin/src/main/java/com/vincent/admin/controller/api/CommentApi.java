package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.benmanes.caffeine.cache.Cache;
import com.vincent.admin.entity.Article;
import com.vincent.admin.entity.Comment;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Comment Api
 *
 * @author Vincent Tsai
 * @date 2021/1/15 16:19
 */

@RestController
@RequestMapping("/front/comment")
@CacheConfig(cacheNames = "comment")
@Slf4j
public class CommentApi {

    //Cache<String, Object> caffeine;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/list")
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.articleId")
    public String list(@RequestBody CommentVO commentVO){
        //if (caffeine.ge)
        log.debug("Comment list from database");
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotNull(commentVO.getArticleId())){
            Article article = articleService.getById(commentVO.getArticleId());
            if(ObjectUtils.isNull(article) || !article.getEnableComment()){
                return Result.failure("该文章没有开放评论");
            }
            queryWrapper.like("articleId",commentVO.getArticleId());
        }

        Page<Comment> page = new Page<>();
        page.setSize(commentVO.getPageSize());
        page.setCurrent(commentVO.getCurrentPage());
        queryWrapper.isNull("toUid");
        queryWrapper.eq("targetType",0); // 所有一级评论
        queryWrapper.orderByDesc("createTime");

        IPage<Comment> pageList = commentService.page(page,queryWrapper);
        /*--------------------------------------------------------------------*/
        List<Comment> commentList = pageList.getRecords();
        List<Comment> primaryCommentList = new ArrayList<>(commentList);
        //BeanUtils.copyProperties(primaryCommentList, commentList); // copy
        //System.out.println("最高级评论列表1：\n"+primaryCommentList);

        List<Long> commentIdList = new ArrayList<>();
        commentList.forEach(comment -> {
            commentIdList.add(comment.getUid());
        });
        if(commentIdList.size() > 0){
            //  根据 rootId 查找所有子评论
            QueryWrapper<Comment> subCommentQueryWrapper = new QueryWrapper<>();
            subCommentQueryWrapper.in("rootId",commentIdList);
            List<Comment> subCommentList = commentService.list(subCommentQueryWrapper);
            if(subCommentList.size() > 0){
                commentList.addAll(subCommentList);
            }
        }

        HashMap<Long, List<Comment>> replyMap = new HashMap<>();
        for (Comment parent : commentList) {
            List<Comment> replyList = new ArrayList<>();
            for (Comment child : commentList) {
                if (parent.getUid().equals(child.getToUid())) {
                    replyList.add(child);
                }
            }
            replyMap.put(parent.getUid(), replyList);
        }
        //System.out.println("最高级评论列表2：\n"+primaryCommentList);
        processReply(primaryCommentList, replyMap);
        pageList.setRecords(primaryCommentList);
        return Result.success("查找评论列表成功！",pageList);
    }
    private void processReply(List<Comment> commentList, HashMap<Long, List<Comment>> replyMap){
        if(commentList == null || commentList.size()==0){ return; }
        else{
            commentList.forEach(comment -> {
                comment.setReplyList(replyMap.get(comment.getUid()));
                processReply(replyMap.get(comment.getUid()), replyMap);
            });
            //return commentList;
        }
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestBody Comment comment){
        return Result.success();
    }


    @PostMapping("/add")
    public String addComment(@RequestBody CommentVO commentVO){

        Comment comment = new Comment();
        comment.setBlogUid(commentVO.getArticleId());
        comment.setContent(commentVO.getContent());
        //comment.setCommentTime(commentVO.ge);
        comment.setUserUid(commentVO.getFromUid());
        //comment.setRootId(commentVO.getParentId());
        comment.setToUid(commentVO.getToUid());
        comment.setTargetType(commentVO.getTargetType());

        //comment.setUserName(commentVO.getUserName());
        // 非一级评论，设置根评论id
        if (ObjectUtils.isNotNull(commentVO.getToUid())) {
            Comment toComment = commentService.getById(commentVO.getToUid());
            if (toComment!=null && ObjectUtils.isNull(toComment.getToUid())) {
                comment.setRootUid(toComment.getUid());
            }else {
                comment.setRootUid(toComment.getRootUid());
            }
        }

        boolean isSaved = commentService.save(comment);
        if (isSaved){
            return Result.success("yattaze!");
        }
        else {
            return Result.failure("nope");
        }
    }



    @GetMapping("/test")
    public String commentTest(){
        return "test";
    }

}