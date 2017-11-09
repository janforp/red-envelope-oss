package com.envolope.oss.util.date;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jan on 16/9/29.
 * 关于时间,日期的工具类
 */
public class DateUtil {

    public static String FMT_yyyy_MM_dd = "yyyy-MM-dd";
    public static String FMT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String FMT_HH_mm_ss_SSS = "HH:mm:ss.SSS";

    /**
     * 获得从今天算起的n天前的日期
     * @param n         n天前
     * @param
     * @return
     */
    public static String getDayOfNDaysAgoFromNow(long n){

        Long now = System.currentTimeMillis();
        Long nDaysAgo = now - n*24*60*60*1000;

        return new SimpleDateFormat(DateUtil.FMT_yyyy_MM_dd).format(new Date(nDaysAgo));
    }
}
