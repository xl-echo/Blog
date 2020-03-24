package com.echo.blog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程安全的时间工具类
 *
 * @author XLecho
 * Date 2019/11/23 0023
 * Time 12:19
 */
public class ThreadLocalDateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    private static DateFormat getDateFormat() {
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    public static Date parseStringDate(String strDate) {
        try {
            return getDateFormat().parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException("解析String类型Date数据出错!");
        }
    }

    private static void remove() {
        threadLocal.remove();
    }

}
