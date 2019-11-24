package com.kaola.manager.dao;

import com.kaola.manager.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface OrderMapper {
    @Delete("DELETE FROM orders WHERE order_id=#{id}")
    void deleteOrderById(@Param("id") int orderId);

    //@Select("SELECT * FROM orders LEFT JOIN store ON orders.store_id= store.store_id  WHERE order_type=#{orderType} AND order_date LIKE #{orderDate}")
    @Results(value =
            {
                    @Result(property = "orderId", column = "order_id"),
                    @Result(property = "orderMoney", column = "order_money"),
                    @Result(property = "orderDate", column = "order_date"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "orderStatus", column = "order_status"),
                    @Result(property = "orderType", column = "order_type"),
                    @Result(property = "sitId", column = "sit_id"),
                    @Result(property = "roomId", column = "room_id"),
                    @Result(property = "storeId", column = "store_id"),
                    @Result(property = "storeName", column = "store_name")
            })
    @SelectProvider(value = Provider.class,method = "selectOrder")
    List<Order> queryOrderByType(@Param("orderType") String orderType, @Param("orderDate") String orderDate);

/*    @Select("SELECT * FROM orders WHERE order_type=#{orderType} AND order_date LIKE #{orderDate}")
    @Results(value =
            {
                    @Result(property = "orderId", column = "order_id"),
                    @Result(property = "orderMoney", column = "order_money"),
                    @Result(property = "orderDate", column = "order_date"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "orderStatus", column = "order_status"),
                    @Result(property = "orderType", column = "order_type"),
                    @Result(property = "sitId", column = "sit_id"),
                    @Result(property = "roomId", column = "room_id"),
                    @Result(property = "storeId", column = "store_id"), @Result(property = "storeName", column = "store_name")

            })
    List<Order> queryOrderByTypeAndDate(@Param("orderType") String orderType, @Param("orderDate") String orderDate);*/
}
