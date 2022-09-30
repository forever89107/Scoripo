package com.my.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
public class DateTimeUtils {

    /**
     * long to Date
     */
    public static Date milsecondtoDate(long time) {
        return new Date(time);
    }

    /**
     * Date->String
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateString(Date date) {
        String strDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = simpleDateFormat.format(date);
        return strDate;
    }
    /**
     * 判斷當前時間是否在[startTime, endTime]區間
     *
     * @param nowTime 當前時間
     * @param startTime 開始時間
     * @param endTime 結束時間
     * @return boolean
     */
    public static boolean isValidTime(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * string(yyyy-MM-dd) to Date
     * @param format yyyy-MM-dd
     * @return Date
     * @throws ParseException Exception
     */
    public static Date getDate(String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        return dateFormat.parse(format);
    }
}
