package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.benmanes.caffeine.cache.Cache;
import com.vincent.admin.entity.Article;
import com.vincent.admin.entity.Comment;
import com.vincent.admin.entity.User;
import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.record.VisitRecord;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @PostMapping("/list")
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.source + #p0.blogUid")
    public String list(@RequestBody CommentVO commentVO){
        //if (caffeine.ge)
        log.debug("Comment list from database");
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if(0 != commentVO.getBlogUid()){
            Article article = articleService.getById(commentVO.getBlogUid());
            if(ObjectUtils.isNull(article) || !article.getEnableComment()){
                return Result.failure("该文章没有开放评论");
            }
            queryWrapper.like("blog_uid",commentVO.getBlogUid());
        }

        Page<Comment> page = new Page<>();
        page.setSize(commentVO.getPageSize());
        page.setCurrent(commentVO.getCurrentPage());
        queryWrapper.isNull("to_uid");
        queryWrapper.eq("target_type",0); // 所有一级评论
        queryWrapper.orderByDesc("create_time");

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
            subCommentQueryWrapper.in("root_uid",commentIdList);
            List<Comment> subCommentList = commentService.list(subCommentQueryWrapper);
            if(subCommentList.size() > 0){
                commentList.addAll(subCommentList);
            }
        }

        // 从这里开始commentList为该文章下的所有评论
        Collection<Long> userUidList = new ArrayList<>();
        commentList.forEach(comment -> {
            if (comment.getUserUid()!=null)
                userUidList.add(comment.getUserUid());
        });

        List<User> userList = new ArrayList<>();
        if (userUidList.size() > 0){
            userList = userService.listByIds(userUidList);
        }

        List<User> processedUserList = new ArrayList<>();
        userList.forEach(user -> {
            User tUser = new User();
            //tUser.setAvatar();
            tUser.setUid(user.getUid());
            tUser.setUserProxy(user.getUserProxy());
            tUser.setUsername(user.getUsername());
            tUser.setOs(user.getOs());
            tUser.setBrowser(user.getBrowser());
            //tUser.setIpSource(user.getIpSource());
            processedUserList.add(tUser);
        });

        HashMap<Long, User> userMap = new HashMap<>();
        processedUserList.forEach(user -> {
            if (user.getUid()!=null){
                userMap.put(user.getUid(), user);
            }
        }); // 哈希表，方便定位用户
        commentList.forEach(comment -> {
            comment.setUser(userMap.get(comment.getUserUid()));
        }); // 为所有评论设置用户信息

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
        //System.out.println();
        processReply(primaryCommentList, replyMap);
        log.info("处理完毕的评论列表：\n"+primaryCommentList);
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

    @PostMapping("/update")
    @CachePut(key = "#p0.currentPage + #p0.pageSize + #p0.source + #p0.blogUid")
    public String updateCommentList(@RequestBody CommentVO commentVO){
        return list(commentVO);
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestBody Comment comment){
        return Result.success();
    }

    @VisitRecord(value = "评论", operation = UserOperation.PUBLISH_COMMENT)
    @PostMapping("/add")
    public String addComment(@RequestBody CommentVO commentVO){
        if (commentVO.getUserUid()==null){
            return Result.failure("Back-end: 请先登录");
        }

        Comment comment = new Comment();
        comment.setBlogUid(commentVO.getBlogUid());
        Article article = articleService.getById(comment.getBlogUid());
        article.setCommentCount(article.getCommentCount()+1);
        article.updateById();
        comment.setContent(commentVO.getContent());
        //comment.setCommentTime(commentVO.ge);
        comment.setUserUid(commentVO.getUserUid());
        comment.setToUserUid(commentVO.getToUserUid());
        //comment.setRootId(commentVO.getParentId());
        comment.setToUid(commentVO.getToUid());
        comment.setSource(commentVO.getSource());
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
        log.info("新增评论："+comment);
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