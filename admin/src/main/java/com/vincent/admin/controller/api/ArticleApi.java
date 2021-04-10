package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.*;
import com.vincent.admin.service.*;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/getBlogByTitle/{link}")
    //@Cacheable(key = "#p0")
    public String getBlogByLink(@PathVariable String link){
        if (StringUtils.isBlank(link)){
            return Result.failure("标题为空");
        }
        return articleService.getBlogByLink(link);
    }

    @GetMapping("/getTop")
    public String getTopBlog(){
        return articleService.getTopBlog();
    }
}
