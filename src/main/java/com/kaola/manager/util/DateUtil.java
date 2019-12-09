package com.kaola.manager.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author linxu
 * @version 1.0
 * date time tool...
 */
public class DateUtil {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE = "yyyy-MM-dd";
    private static final long ONE_DAY_MILLIONS = 86400;

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(System.currentTimeMillis());
    }

    public static String getY_M_D() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getY_M_D(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE);
        return simpleDateFormat.format(time);
    }

    public static List<String> getY_M_DList() {
        List<String> dates = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Date date=new Date();
            Calendar calendar=new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,i);
            date=calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE);
            dates.add(simpleDateFormat.format(date));
        }
        return dates;
    }

    public static Long currentTime() {
        return System.currentTimeMillis();
    }

  /*  public static void main(String[] args) {

        System.err.println(DateUtil.getY_M_D(System.currentTimeMillis() + ONE_DAY_MILLIONS));
        for (String d : DateUtil.getY_M_DList()
                ) {
            System.out.println(d);
        }
    }*/

}
