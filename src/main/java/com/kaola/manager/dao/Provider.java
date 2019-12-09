package com.kaola.manager.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Provider {
    public String selectOrder(@Param("orderType") String orderType, @Param("orderDate") String orderDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM orders  JOIN user ON orders.user_id=user.user_id LEFT JOIN store ON orders.store_id= store.store_id      WHERE order_type=#{orderType}");
        if (orderDate != null && !orderDate.equals("")) {
            stringBuilder.append("AND order_date LIKE #{orderDate} \"%\"");
        }
        return stringBuilder.toString();
    }

    public String selectPreservation(@Param("preservationTime") String date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT preservation_id,room_id,sit_id,room_type,preservation_date,name,tel,store_name ");
        stringBuilder.append("FROM ((SELECT p.*,u.name,u.tel FROM preservation AS p  JOIN user AS u ON p.user_id=u.user_id) AS p1  JOIN store ON p1.store_id=store.store_id)");
        if (date != null && !date.trim().equals("")) {
            stringBuilder.append("WHERE preservation_date LIKE #{preservationTime} \"%\"");
        }
        return stringBuilder.toString();
    }

    public String selectAllRefound(@Param("fdate") String date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT *FROM refund");
        if (date != null && !date.equals("")) {
            stringBuilder.append(" WHERE refund_date  LIKE  #{fdate} \"%\"");
        }
        return stringBuilder.toString();
    }
}
