package com.vincent.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vincent.admin.entity.Category;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 14:30
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<String> findCategoryList();

}
