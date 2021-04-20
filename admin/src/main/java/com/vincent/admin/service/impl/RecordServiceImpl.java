package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Record;
import com.vincent.admin.mapper.RecordMapper;
import com.vincent.admin.service.RecordService;
import com.vincent.admin.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 23:20
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Override
    public Integer getTotalNumberOfVisitors() {
        return baseMapper.getNumberOfVisitor("1970:01:01 00:00:00","2036:12:31 00:00:00");
    }
    @Override
    public Integer getVisitorCountOfToday() {
        String startTime = DateUtil.getStartOfToDay();
        String endTime = DateUtil.getEndOfToDay();
        System.out.println("start of today: "+DateUtil.getStartOfToDay());
        return baseMapper.getNumberOfVisitor(startTime,endTime);
    }


    @Override
    public List<Map<String, Object>> getWeeklyViews() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getYearlyViews() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getNumberOfVisitorWeekly() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getNumberOfVisitorYearly() {
        return null;
    }
}
