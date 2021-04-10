package com.vincent.admin.controller.api.admin;

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

    @GetMapping("/getConfig")
    @Cacheable
    public String getConfig(){
        return Result.success("Back-end： 获取系统配置成功",systemConfigService.getById(1));
    }

    @PostMapping("/updateConfig")
    public String updateConfig(@RequestBody SystemConfig systemConfig){
        if (systemConfig.getUid()==null){
            return Result.failure("Back-end: 缺少SystemConfig uid");
        }
        systemConfig.updateById();
        return Result.success("Back-end： 更新系统配置成功");
    }
}
