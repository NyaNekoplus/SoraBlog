package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.annotation.record.VisitRecord;
import com.vincent.admin.service.*;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vincent Tsai
 * @date 2021/3/11 14:40
 */

@RestController("FrontArticleApi")
@RequestMapping("/front/blog")
@Slf4j
public class ArticleApi {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FileService fileService;
    @Autowired
    private SystemConfigService systemConfigService;


    @PostMapping("/list")
    public String list(@RequestBody ArticleVO articleVO){

        return articleService.getArticleListByPage(articleVO);
    }

    @VisitRecord(value = "阅读文章", operation = UserOperation.VISIT_PAGE)
    @GetMapping("/getBlogByTitle/{link}")
    public String getBlogByLink(@PathVariable String link){
        if (StringUtils.isBlank(link)){
            return Result.failure("标题不能为空");
        }
        return articleService.getBlogByLink(link);
    }

    @GetMapping("/getTop")
    public String getTopBlog(){
        return articleService.getTopBlog();
    }

    @GetMapping("/getRecommend")
    public String getRecommend(){
        return articleService.getRecommend();
    }

    @VisitRecord(value = "访问主页", operation = UserOperation.VISIT_PAGE)
    @GetMapping("/visitRecord")
    public String visitRecord() {
        return Result.success();
    }

    @VisitRecord(value = "点赞", operation = UserOperation.LIKE)
    @GetMapping("/likedIncrement")
    public String likedIncrement(@RequestParam(name = "uid", required = false) String uid) {
        if (StringUtils.isBlank(uid)){
            return Result.failure("点赞失败,uid为空");
        }
        return articleService.likeBlog(Long.parseLong(uid));
    }
}
