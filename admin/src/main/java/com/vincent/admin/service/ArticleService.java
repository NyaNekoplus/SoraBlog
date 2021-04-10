package com.vincent.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.Article;
import com.vincent.admin.vo.ArticleVO;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 14:30
 */
public interface ArticleService extends IService<Article> {

    String getBlogByLink(String link);

    String getArticleListByPage(ArticleVO articleVO);

    String getTopBlog();
}
