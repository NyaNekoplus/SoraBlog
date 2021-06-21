package com.vincent.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/20 16:29
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Article getBlogByLink(@Param("link") String link);

    List<Article> getTopBlogWithCover();

    IPage<Article> getBlogByPageWithCover(Page<Article> page, @Param("categoryUid") Long categoryUid, @Param("isDraft") Boolean isDraft);

    IPage<Article> getBlogByPageWithoutTags(Page<Article> page, @Param("categoryUid") Long categoryUid, @Param("isDraft")Boolean isDraft);

    IPage<Article> searchArticle(Page<Article> page, @Param("keyword") String keyword);
}
