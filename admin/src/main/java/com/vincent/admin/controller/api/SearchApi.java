package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.annotation.record.VisitRecord;
import com.vincent.admin.entity.Article;
import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.service.ArticleService;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/5/14 14:19
 */
@RestController("FrontSearchApi")
@RequestMapping("/front/search")
@Slf4j
public class SearchApi {
    @Autowired
    private ArticleService articleService;

    @VisitRecord(value = "搜索", operation = UserOperation.SEARCH)
    @GetMapping("/searchArticle")
    public String searchArticle(@RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "pageSize", required = false,defaultValue = "10") Long pageSize,
                                @RequestParam(name = "currentPage", required = false,defaultValue = "1") Long currentPage) {
        if (StringUtils.isBlank(keyword)){
            return Result.failure("参数不能为空");
        }
        String result = articleService.getBlogByKeyword(keyword,currentPage,pageSize);
        return result;
    }
}
