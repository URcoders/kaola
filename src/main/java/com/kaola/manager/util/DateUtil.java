package com.kaola.manager.util;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author linxu
 * @version 1.0
 * date time tool...
 */
public class DateUtil {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE = "yyyy-MM-dd";

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(System.currentTimeMillis());
    }

    public static String getY_M_D() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static Long currentTime() {
        return System.currentTimeMillis();
    }

}
