package com.my.common.utils;

import java.util.Calendar;
import java.util.Date;

public class DateDiff {
    public static long seconds(Date fromDate, Date endDate) {
        long to = endDate.getTime();
        long from = fromDate.getTime();
        return  Math.abs(to - from) / 1000;
    }

    public static long days(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long start = calendar.getTimeInMillis();
        Date now = new Date();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long end = calendar.getTimeInMillis();
        return Math.abs(end - start) / 86400000;
    }

    public static long weeks(Date date) {
        int sunday = Calendar.SUNDAY;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, sunday);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long start = calendar.getTimeInMillis();
        Date now = new Date();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_WEEK, sunday);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long end = calendar.getTimeInMillis();
        return Math.abs(end - start) / (7 * 86400000);
    }

    public static long months(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int startMonth = calendar.get(Calendar.MONTH);
        int startYear = calendar.get(Calendar.YEAR);
        Date now = new Date();
        calendar.setTime(now);
        int endMonth = calendar.get(Calendar.MONTH);
        int endYear = calendar.get(Calendar.YEAR);
        return Math.abs((endYear - startYear) * 12 + (endMonth - startMonth));
    }


}
