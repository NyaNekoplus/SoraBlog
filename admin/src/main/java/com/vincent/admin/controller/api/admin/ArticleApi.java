package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.Article;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Tsai
 * @date 2021/2/7 13:52
 */

@RestController
@RequestMapping("/admin/article")
public class ArticleApi {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/list")
    String list(@RequestBody ArticleVO articleVO){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("releaseTime");
        //queryWrapper.isNull("updateTime");

        Page<Article> page = new Page<>();
        page.setCurrent(articleVO.getCurrentPage());
        page.setSize(articleVO.getPageSize());

        IPage<Article> pageList = articleService.page(page,queryWrapper); // 需要加
        // SELECT summary,content,contentMd FROM article ORDER BY releaseTime DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        //SQLSERVER2012 用 “FETCH NEXT ” 关键字分页时候 ORDER BY 是必须写的，
        // 于是在 “OFFSET” 前加上 "ORDER BY" 排序条件，不报错了。
        //OFFSET 需要加 ORDER BY 否则会报错
        //System.out.println(pageList);
        return Result.success("查询文章列表成功",pageList);
    }

    @PostMapping("/add")
    String addArticle(@RequestBody ArticleVO articleVO){
        Article article = new Article();
        article.setCategory(articleVO.getCategory());
        article.setTitle(articleVO.getTitle());
        article.setLink(articleVO.getLink());
        article.setLanguage(articleVO.getLanguage());
        article.setSummary(articleVO.getContent().substring(0,35)); //
        article.setContent(articleVO.getContent());
        article.setContentMd(articleVO.getContentMd());
        article.setEnableComment(articleVO.getEnableComment());
        article.setIsDraft(articleVO.getIsDraft());
        article.setIsTop(articleVO.getIsTop());
        //article.setViewCount();  // 0 by default

        Boolean isSaved = articleService.save(article);
        if (isSaved){
            return Result.success("发布文章成功");
        }else{
            return Result.failure("发布文章失败");
        }
    }

}
