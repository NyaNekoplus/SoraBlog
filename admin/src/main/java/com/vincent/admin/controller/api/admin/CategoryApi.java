package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.vincent.admin.entity.Category;
import com.vincent.admin.service.CategoryService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 19:30
 */

@RestController
@RequestMapping("/admin/category")
@CacheConfig(cacheNames = "category")
@Slf4j
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable
    public String list() {
        List<Category> list = categoryService.list();
        /*
        List<String> names = new ArrayList<>();
        list.forEach(category -> {
            names.add(category.getName());
        });*/
        String msg = "Back-end: 查询分类列表成功";
        log.info(msg);
        return Result.success(msg, list);
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody CategoryVO categoryVO) {
        Category category = new Category();
        category.setName(categoryVO.getName());
        String msg;
        if (categoryService.save(category)){
            msg = "新增 "+ category.getName() +" 分类成功";
            log.info(msg);
            return Result.success("Back-end: "+msg);
        }else{
            msg = "新增 "+ category.getName() +" 分类失败";
            log.error(msg);
            return Result.failure("Back-end: "+msg);
        }
    }
}
