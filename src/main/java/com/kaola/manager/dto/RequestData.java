package com.kaola.manager.dto;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class RequestData {

    private String username;
    private String psw;
    /**
     * 基础业务
     */
    private String tokens;
    private int storeId;
    /**
     * store业务
     */
    private String storeName;
    private String storeAddress;
    private String uid;
    /**********
     *room业务
     */
    private String roomType;
    private String roomId;

    /**
     * 座位业务
     */
    private int sitId;
    private Double money;

    private int sitStartId;
    private int sitEndId;
    /**
     * user业务
     */
    private String name;
    private String sex;
    private String tel;
    private Double rareMoney;
    private int userId;
    private int score;
    private String realName;
    /**
     * 订单业务
     */
    private int orderId;
    private String orderDate;
    private String orderType;
    /**
     * 开门
     */
    private int preTime;
    /**
     * 套餐模块
     */
    private int mealId;
    private double mealMoney;
    private String mealType;
    private String mealDesc;
    private int mealDays;
    private String mealName;
    private int usedTime;
    /**
     * 退款
     */
    private int reId;
    private String status;
    //fix
    private String storeTime;



}
