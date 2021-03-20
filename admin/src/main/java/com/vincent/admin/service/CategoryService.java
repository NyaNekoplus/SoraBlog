package com.vincent.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.Category;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 18:45
 */
public interface CategoryService extends IService<Category> {

    List<String> findCategoryList();
}
