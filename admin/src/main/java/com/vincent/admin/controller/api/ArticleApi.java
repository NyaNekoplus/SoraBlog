package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.Article;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vincent Tsai
 * @date 2021/3/11 14:40
 */

@RestController("FrontArticleApi")
@RequestMapping("/front/blog")
@CacheConfig(cacheNames = "article")
@Slf4j
public class ArticleApi {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/list")
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.categoryUid")
    public String list(@RequestBody ArticleVO articleVO){
        log.debug("Article list from database");
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (!articleVO.getCategoryUid().equals("0")){
            queryWrapper.in("category_uid", articleVO.getCategoryUid());
        }
        queryWrapper.orderByDesc("create_time");
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> blogList = articleService.page(page, queryWrapper);
        String msg;
        if (articleVO.getCategoryUid() == null) {
            msg = "获取文章列表成功，无参数";
        }else {
            msg = "获取文章列表成功，参数:" + articleVO.getCategoryUid();
        }

        log.debug(msg);
        return Result.success(msg, blogList);
    }

    @GetMapping("/getBlogByTitle")
    @Cacheable(key = "#p0")
    public String getBlogByTitle(@RequestParam String title){
        if (StringUtils.isBlank(title)){
            return Result.failure("标题为空");
        }
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        Article article = articleService.getOne(queryWrapper);
        if (article == null){
            return Result.failure("文章不存在");
        }
        String msg = title + "查询成功";
        return Result.success(msg, article);
    }
}
