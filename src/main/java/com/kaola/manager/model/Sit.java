package com.kaola.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sit {
    /**
     * 时间段表
     */
    public static final List<String> SIT_DATE_TABLE = new ArrayList<>(24);

    //初始化段表
    static {
        /*SIT_DATE_TABLE.add("00:00-01:00");
        SIT_DATE_TABLE.add("01:00-02:00");
        SIT_DATE_TABLE.add("02:00-03:00");
        SIT_DATE_TABLE.add("03:00-04:00");
        SIT_DATE_TABLE.add("04:00-05:00");
        SIT_DATE_TABLE.add("05:00-06:00");
        SIT_DATE_TABLE.add("06:00-07:00");
        SIT_DATE_TABLE.add("07:00-08:00");*/
        SIT_DATE_TABLE.add("08:00-09:00");
        SIT_DATE_TABLE.add("09:00-10:00");
        SIT_DATE_TABLE.add("10:00-11:00");
        SIT_DATE_TABLE.add("11:00-12:00");
        SIT_DATE_TABLE.add("13:00-14:00");
        SIT_DATE_TABLE.add("14:00-15:00");
        SIT_DATE_TABLE.add("15:00-16:00");
        SIT_DATE_TABLE.add("16:00-17:00");
        SIT_DATE_TABLE.add("17:00-18:00");
        SIT_DATE_TABLE.add("18:00-19:00");
        SIT_DATE_TABLE.add("19:00-20:00");
        SIT_DATE_TABLE.add("20:00-21:00");
        SIT_DATE_TABLE.add("21:00-22:00");
        SIT_DATE_TABLE.add("22:00-23:00");
        /*SIT_DATE_TABLE.add("23:00-24:00");*/
    }

    /**
     * 用于表示
     */
    private int sitId;
    private String roomId;
    private int storeId;
    private String roomType;

    private int preserved;

    /**
     * 座位的完全时间段02:00 - 03:00
     */
    private String sitDate;
    /**
     * 这个座位一个时间段的单价
     */
    private Double money;
    /**
     * 2019-11-22
     */
    private String curDate;

}
