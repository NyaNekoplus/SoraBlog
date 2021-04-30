package com.vincent.admin.controller.api.admin;

import com.vincent.admin.annotation.proxy.ProxyAuth;
import com.vincent.admin.entity.Category;
import com.vincent.admin.entity.Tag;
import com.vincent.admin.service.TagService;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/20 16:22
 */
@RestController
@RequestMapping("/admin/tag")
@CacheConfig(cacheNames = "tag")
@Slf4j
public class TagApi {

    @Autowired
    private TagService tagService;

    @ProxyAuth
    @GetMapping("/list")
    @Cacheable
    public String list(){
        List<Tag> list = tagService.list();
        String msg = "Back-end: 查询标签列表成功";
        log.info(msg);
        return Result.success(msg, list);
    }
}
