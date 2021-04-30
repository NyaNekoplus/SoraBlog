package com.vincent.admin.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vincent.admin.entity.Category;
import com.vincent.admin.service.CategoryService;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Tsai
 * @date 2021/4/27 11:15
 */
@RestController("FrontCategoryApi")
@RequestMapping("/front/category")
@Slf4j
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryByName/{name}")
    public String getCategoryByName(@PathVariable String name){
        Category category = categoryService.getOne(new QueryWrapper<Category>().eq("name",name));
        return category==null?Result.failure("无此分类"):Result.success("获取"+category.getName()+"分类成功",category);
    }
}
