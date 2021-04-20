package com.vincent.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.Record;

import java.util.List;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 23:19
 */
public interface RecordService extends IService<Record> {

    Integer getTotalNumberOfVisitors();

    Integer getVisitorCountOfToday();

    List<Map<String, Object>> getWeeklyViews();

    List<Map<String, Object>> getYearlyViews();

    List<Map<String, Object>> getNumberOfVisitorWeekly();

    List<Map<String, Object>> getNumberOfVisitorYearly();

}
