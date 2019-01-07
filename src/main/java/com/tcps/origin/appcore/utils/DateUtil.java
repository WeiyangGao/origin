package com.tcps.origin.appcore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String YMD_HMs = "yyyy-MM-dd HH:mm:ss";


    public static String timeStamp2DateStr(long seconds, String format) {
        if (format == null || format.isEmpty()) {
            format = YMD_HMs;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static long dateStr2TimeStamp(String dateString, String format) {
        try {
            if (format == null || format.isEmpty()) {
                format = YMD_HM;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.parse(dateString).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getTimeStamp() {
        return System.currentTimeMillis() + "";
    }
}
