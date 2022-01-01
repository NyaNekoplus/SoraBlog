package com.vincent.admin.controller.api;

import com.vincent.admin.annotation.record.VisitRecord;
import com.vincent.admin.enums.UserOperation;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Tsai
 * @date 2021/7/21 13:45
 */

@RestController("FrontTimelineApi")
@CacheConfig(cacheNames = "timeline")
@RequestMapping("/front/timeline")
@Slf4j
public class TimelineApi {

    @GetMapping("/getTimeLine")
    @VisitRecord(value = "时光轴",operation = UserOperation.VISIT_TIME_LINE)
    public String getTimeLine(){
        return Result.success(); // TODO
    }
}
