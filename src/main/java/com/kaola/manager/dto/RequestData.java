package com.kaola.manager.dto;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class RequestData {
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
    /**
     * user业务
     */
    private String name;
    private String sex;
    private String tel;
    private Double rareMoney;
    private int userId;
    private int score;
    /**
     * 订单业务
     */
    private int orderId;
    private String orderDate;
    private String orderType;


}
