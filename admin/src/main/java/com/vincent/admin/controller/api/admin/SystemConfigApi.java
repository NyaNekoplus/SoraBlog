package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vincent.admin.annotation.proxy.ProxyAuth;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.service.SystemConfigService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.SystemConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vincent Tsai
 * @date 2021/4/5 20:43
 */
@RestController
@RequestMapping("/admin/system")
@CacheConfig(cacheNames = "system")
@Slf4j
public class SystemConfigApi {
    @Autowired
    private SystemConfigService systemConfigService;

    @ProxyAuth
    @PostMapping("/setDefaultCover") // TODO 改成post传图片url
    public String setDefaultCover(@RequestBody File image){
        if (image == null || image.getUrl()==null) return Result.failure("Back-end： 图片URL不能为空");
        log.info("Set default cover: "+image.getUrl());
        UpdateWrapper<SystemConfig> wrapper = new UpdateWrapper<>();
        wrapper.set("default_cover_url",image.getUrl());
        boolean result = systemConfigService.update(wrapper);
        return result?Result.success("Back-end： 更新默认封面成功"):Result.failure("Back-end： 更新默认封面失败");
    }

    @ProxyAuth
    @GetMapping("/getConfig")
    @Cacheable
    public String getConfig(){
        return Result.success("Back-end： 获取系统配置成功",systemConfigService.getConfig((long) 1));
    }

    @ProxyAuth
    @PostMapping("/updateConfig")
    public String updateConfig(@RequestBody SystemConfig systemConfig){
        if (systemConfig.getUid()==null){
            return Result.failure("Back-end: 缺少SystemConfig uid");
        }
        systemConfig.updateById();
        return Result.success("Back-end： 更新系统配置成功");
    }
}
