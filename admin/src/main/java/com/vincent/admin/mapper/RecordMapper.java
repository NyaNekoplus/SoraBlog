package com.vincent.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vincent.admin.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/4/16 23:17
 */
public interface RecordMapper extends BaseMapper<Record> {

    // 获取浏览次数
    @Select("select count(ip) from (select ip from s_visit_record where create_time>#{startTime} and create_time<#{endTime}) t")
    Integer getViews(@Param("startTime") String start, @Param("endTime") String end);

    // 获取访客数量
    @Select("select count(ip) from (select ip from s_visit_record where create_time > #{startTime} and create_time < #{endTime} group by ip) t;")
    Integer getNumberOfVisitor(@Param("startTime") String startTime, @Param("endTime") String endTime);


    List<Map<String, Object>> getViewsInPeriod(@Param("startTime") String start, @Param("endTime") String end);

    @Select("SELECT DATE, COUNT(ip) COUNT FROM \n" +
            "(SELECT DISTINCT DATE_FORMAT(create_time, '%Y-%m-%d') DATE, ip FROM s_visit_record WHERE create_time>=#{startTime} AND create_time<=#{endTime} GROUP BY DATE_FORMAT(create_time, '%Y-%m-%d'),ip) AS tmp \n" +
            "GROUP BY DATE ")
    List<Map<String, Object>> getNumberOfVisitorInPeriod(@Param("startTime") String start, @Param("endTime") String end);
}
