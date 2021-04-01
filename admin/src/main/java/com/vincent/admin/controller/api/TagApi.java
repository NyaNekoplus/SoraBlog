package com.vincent.admin.controller.api;

import com.vincent.admin.service.TagService;
import com.vincent.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Tsai
 * @date 2021/3/31 21:11
 */
@RestController("FrontTagApi")
@RequestMapping("/front/tag")
@CacheConfig(cacheNames = "tag")
public class TagApi {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @Cacheable
    public String list(){
        return Result.success("查找标签列表成功",tagService.list());
    }
}
