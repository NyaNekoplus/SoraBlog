package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Category;
import com.vincent.admin.mapper.CategoryMapper;
import com.vincent.admin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 18:45
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<String> findCategoryList() {
        //List<String> list = baseMapper.selectObjs(new QueryWrapper<Category>().select("name"));
        return null;
    }
}
