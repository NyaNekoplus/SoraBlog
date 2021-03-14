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
    public String list(@RequestBody ArticleVO articleVO){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(articleVO.getCategory())){
            queryWrapper.in("category", articleVO.getCategory());
        }
        queryWrapper.orderByDesc("createTime");
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> blogList = articleService.page(page, queryWrapper);
        String msg = "获取文章列表成功，参数：" + articleVO.getCategory();
        log.debug(msg);
        return Result.success(msg, blogList);
    }

    @PostMapping("/get")
    @Cacheable(key = "#articleVO.id")
    public String getBlogByTitle(@RequestBody ArticleVO articleVO){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(articleVO.getCategory())){
            queryWrapper.in("category", articleVO.getCategory());
        }
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> blogList = articleService.page(page, queryWrapper);
        String msg = "获取文章列表成功，参数：" + articleVO.getCategory();
        return Result.success(msg, blogList);
    }
}
