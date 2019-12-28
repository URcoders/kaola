package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class Meal {
    private int mealId;
    private double mealMoney;
    private String mealType;
    private String mealDesc;
    private int mealDays;
    private String mealName;

    //fix the bug lacking of usedTime
    private int usedTime;
    //fix
    private String disPlayDate;
    //是否上架
    private int onSell;
    //剩余数量
    private int rareNumber;
    //可用时间段
    private String shijianduan;

    private int display;
}
