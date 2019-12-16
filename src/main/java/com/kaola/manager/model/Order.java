package com.kaola.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private int orderId;
    private Double orderMoney;
    private String orderDate;
    private int userId;
    private String orderStatus;
    private String orderType;
    private Integer sitId;
    private Integer storeId;
    private String roomId;
    //用于跨表查询店名
    private String storeName;
    private String name;
    private String tel;
    private int preservationId;
    //fix rareMoney to buy meal or preservation
    private String discount;

    //fix the meal
    private Integer mealId;

    public static enum OrderType {
        /**
         * 预约订单
         */
        PRESERVATION_TYPE("预约订单"),
        /**
         * 充值订单
         */
        TOPUP_TYPE("充值订单"),
        /**
         * 套餐订单
         */
        MEAL_TYPE("套餐订单");
        private String type;

        OrderType(String type) {
            this.type = type;
        }

        public String type() {
            return type;
        }
    }

}
