package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.Article;
import com.vincent.admin.entity.Category;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
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
@CacheConfig(cacheNames = "article")
@Slf4j
public class ArticleApi {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileService fileService;
    @Autowired
    private SystemConfigService systemConfigService;


    @PostMapping("/list")
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.categoryUid")
    public String list(@RequestBody ArticleVO articleVO){
        log.debug("Article list from database");
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        boolean isIndex = articleVO.getCategoryUid().equals("0");
        if (!isIndex){
            queryWrapper.in("category_uid", articleVO.getCategoryUid());
        }
        queryWrapper.orderByDesc("create_time");
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> pageList = articleService.page(page, queryWrapper);
        List<Article> articleList = new ArrayList<>(pageList.getRecords());
        
        Collection<Long> coverImageUidList = new ArrayList<>();
        Collection<Long> categoryUidList = new ArrayList<>();
        articleList.forEach(article -> {
            if (article.getCoverUid()!=null){
                coverImageUidList.add(article.getCoverUid());
            }
            if (article.getCategoryUid()!=null){
                categoryUidList.add(article.getCategoryUid());
            }
        });

        if (coverImageUidList.size()>0){
            List<File> coverList = fileService.listByIds(coverImageUidList);
            HashMap<Long,String> fileMap = new HashMap<>();
            coverList.forEach(cover -> {
                fileMap.put(cover.getUid(),cover.getUrl());
            });
            SystemConfig systemConfig = systemConfigService.getById(1);
            articleList.forEach(article -> {
                if (article.getCoverUid()!=null){
                    article.setCoverUrl(systemConfig.getLocalImageBaseUrl()+fileMap.get(article.getCoverUid()));
                }else {
                    article.setCoverUrl("https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/cover.png");
                }
            });
        }else {
            articleList.forEach(article -> {
                article.setCoverUrl("https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/cover.png");
            });
        }
        if (categoryUidList.size()>0){
            List<Category> categoryList = categoryService.listByIds(categoryUidList);
            HashMap<Long,String> categoryMap = new HashMap<>();
            categoryList.forEach(category -> {
                categoryMap.put(category.getUid(),category.getName());
            });
            articleList.forEach(article -> {
                if (article.getCategoryUid()!=null){
                    article.setCategory(categoryMap.get(article.getCategoryUid()));
                }else {
                    article.setCategory("奇怪的分类");
                }
            });
        }


        pageList.setRecords(articleList);

        String msg;
        if (isIndex) {
            msg = "获取主页全文章列表成功，";
        }else {
            msg = "获取部分文章列表成功，参数:" + articleVO.getCategoryUid();
        }

        log.debug(msg);
        return Result.success(msg, pageList);
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
