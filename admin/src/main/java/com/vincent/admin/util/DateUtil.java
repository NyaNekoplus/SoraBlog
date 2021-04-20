package com.vincent.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 11:45
 */
public class DateUtil {
    public static Integer getYears() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.YEAR);

    }

    /**
     * 获取当前的月
     *
     * @return
     */
    public static Integer getMonth() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.MONTH) + 1;

    }

    /**
     * 获取当前天
     *
     * @return
     */
    public static Integer getDay() {
        Calendar calendar = new GregorianCalendar(TimeZone
                .getDefault());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getStartOfToDay() {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date date = new Date(System.currentTimeMillis());
        return formate.format(date);
    }

    public static String getEndOfToDay() {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date date = new Date(System.currentTimeMillis());
        return formate.format(date);
    }
}
