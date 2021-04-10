package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.*;
import com.vincent.admin.mapper.ArticleMapper;
import com.vincent.admin.service.*;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 14:31
 */
@Service
@CacheConfig(cacheNames = "article")
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
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

    @Override
    public String getBlogByLink(String link) {
        Article article = baseMapper.getBlogByLink(link);
        if (article == null){
            return Result.failure("文章不存在");
        }
        SystemConfig config = systemConfigService.getConfig((long) 1);
        article.setCoverUrl(config.getLocalImageBaseUrl()+article.getCoverUrl());
        return Result.success("获取文章成功，链接："+link,article);
    }

    @Override
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.categoryUid")
    public String getArticleListByPage(ArticleVO articleVO) {

        log.debug("Article list from database");
        /*
        articleVO.setIsDraft(false);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_draft", false);
        boolean isIndex = articleVO.getCategoryUid()==0;
        if (!isIndex){
            queryWrapper.in("category_uid", articleVO.getCategoryUid());
        }
        queryWrapper.orderByDesc("create_time");
        */
        log.info("articleVO.getCategoryUid(): "+articleVO.getCategoryUid());
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> pageList = baseMapper.getBlogByPageWithCover(page,
                (long) 0,
                false);
        List<Article> articleList = new ArrayList<>(pageList.getRecords());
        SystemConfig config = systemConfigService.getConfig((long) 1);
        Collection<Long> categoryUidList = new ArrayList<>();
        articleList.forEach(article -> {
            article.setCoverUrl(config.getLocalImageBaseUrl()+article.getCoverUrl());
            if (article.getCategoryUid()!=null){
                categoryUidList.add(article.getCategoryUid());
            }
        });
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
                if (article.getTagUid()!=null){
                    String[] tagUidArray = article.getTagUid().split(";");
                    Collection<Long> tagUidList = new ArrayList<>();
                    for (String s : tagUidArray) {
                        tagUidList.add(Long.parseLong(s));
                    }
                    log.info("Tag uid list: "+ tagUidList);
                    List<Tag> tagList = tagService.listByIds(tagUidList);
                    log.info("Tag List: "+ tagList);
                    article.setTagList(tagList);
                }
            });
        }
        /*
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
                if (article.getTagUid()!=null){
                    String[] tagUidArray = article.getTagUid().split(";");
                    Collection<Long> tagUidList = new ArrayList<>();
                    for (String s : tagUidArray) {
                        tagUidList.add(Long.parseLong(s));
                    }
                    log.info("Tag uid list: "+ tagUidList);
                    List<Tag> tagList = tagService.listByIds(tagUidList);
                    log.info("Tag List: "+ tagList);
                    article.setTagList(tagList);
                }
            });
        }
        */

        pageList.setRecords(articleList);
        String msg = "获取文章列表成功";
        return Result.success(msg, pageList);
    }

    @Override
    @Cacheable
    public String getTopBlog() {
        List<Article> topList = baseMapper.getTopBlogWithCover();//new ArrayList<>(3);
        SystemConfig config = systemConfigService.getConfig((long) 1);
        topList.forEach(top ->{
            top.setCoverUrl(config.getLocalImageBaseUrl()+top.getCoverUrl());
        });
        log.info("Top list: "+topList);
        return Result.success("查找置顶文章成功",topList);
    }

}
