package com.vincent.admin.controller.api.admin;

import com.vincent.admin.service.CommentService;
import com.vincent.admin.service.RecordService;
import com.vincent.admin.service.UserService;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/18 23:02
 */
@RestController
@RequestMapping("/admin/summary")
@CacheConfig(cacheNames = "summary")
@Slf4j
public class SummaryApi {

    @Autowired
    private RecordService recordService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @GetMapping("/indexInit")
    String indexInit(){
        Map<String,Integer> map = new HashMap<>();
        Integer commentCount = commentService.count();
        Integer totalNumberOfVisitors = recordService.getTotalNumberOfVisitors();
        Integer VisitorCountOfToday = recordService.getVisitorCountOfToday();
        Integer userCount = userService.count();
        map.put("commentCount", commentCount);
        map.put("userCount", userCount);
        map.put("todayVisitor", VisitorCountOfToday);
        map.put("totalVisitor", totalNumberOfVisitors);
        return Result.success("获取统计数据成功",map);
    }
}
