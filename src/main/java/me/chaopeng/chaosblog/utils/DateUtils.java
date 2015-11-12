package me.chaopeng.chaosblog.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chao
 */
public class DateUtils {
    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateStr);
        } catch (Exception e) {
        }
        return date;
    }

    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-mm-dd");
    }
}
