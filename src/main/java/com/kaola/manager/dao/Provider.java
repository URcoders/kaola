package com.kaola.manager.dao;

import com.kaola.manager.model.Store;
import org.apache.ibatis.annotations.Param;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class Provider {
    public String selectOrder(@Param("orderType") String orderType, @Param("orderDate") String orderDate){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT * FROM orders LEFT JOIN store ON orders.store_id= store.store_id  WHERE order_type=#{orderType}");
        if (orderDate!=null&&!orderDate.equals("")){
            stringBuilder.append("AND order_date LIKE #{orderDate} \"%\"");
        }
        return stringBuilder.toString();
    }
}
