package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.Comment;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.introspector.PropertyUtils;

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
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @PostMapping("/list")
    public String list(@RequestBody CommentVO commentVO){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotNull(commentVO.getArticleId())){
            queryWrapper.like("articleId",commentVO.getArticleId());
        }

        Page<Comment> page = new Page<>();
        page.setSize(commentVO.getPageSize());
        page.setCurrent(commentVO.getCurrentPage());
        queryWrapper.isNull("toUid");
        queryWrapper.eq("targetType",0);
        queryWrapper.orderByDesc("commentTime");

        IPage<Comment> pageList = commentService.page(page,queryWrapper);
        /*--------------------------------------------------------------------*/
        List<Comment> commentList = pageList.getRecords();
        List<Comment> primaryCommentList = new ArrayList<>(commentList);
        //BeanUtils.copyProperties(primaryCommentList, commentList); // copy
        //System.out.println("最高级评论列表1：\n"+primaryCommentList);

        List<Long> commentIdList = new ArrayList<>();
        commentList.forEach(comment -> {
            commentIdList.add(comment.getId());
        });
        if(commentIdList.size() > 0){
            //  根据 parentId 查找所有子评论
            QueryWrapper<Comment> subCommentQueryWrapper = new QueryWrapper<>();
            subCommentQueryWrapper.in("parentId",commentIdList);
            List<Comment> subCommentList = commentService.list(subCommentQueryWrapper);
            if(subCommentList.size() > 0){
                commentList.addAll(subCommentList);
            }
        }

        HashMap<Long, List<Comment>> replyMap = new HashMap<>();
        for (Comment parent : commentList) {
            List<Comment> replyList = new ArrayList<>();
            for (Comment child : commentList) {
                if (parent.getId().equals(child.getToUid())) {
                    replyList.add(child);
                }
            }
            replyMap.put(parent.getId(), replyList);
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
                comment.setReplyList(replyMap.get(comment.getId()));
                processReply(replyMap.get(comment.getId()), replyMap);
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
        comment.setArticleId(commentVO.getArticleId());
        comment.setContent(commentVO.getContent());
        //comment.setCommentTime(commentVO.ge);
        comment.setFromUid(commentVO.getFromUid());
        comment.setParentId(commentVO.getParentId());
        comment.setToUid(commentVO.getToUid());
        comment.setTargetType(commentVO.getTargetType());

        //comment.setUserName(commentVO.getUserName());

        Boolean isSaved = commentService.save(comment);
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