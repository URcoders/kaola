package com.kaola.manager.dao;

import com.kaola.manager.model.Refound;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/12/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface RefoundMapper {
    @SelectProvider(value = Provider.class, method = "selectAllRefound")
    @Results(
            value = {
                    @Result(property = "reId", column = "re_id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "tel", column = "tel"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "date", column = "refund_date"),
                    @Result(property = "money", column = "money"),
                    @Result(property = "orderId", column = "order_id"),
                    @Result(property = "preservationId", column = "preservation_id"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "reType", column = "order_type")
            }
    )
    List<Refound> queryAllRefound(@Param("fdate") String date);

    @Update("UPDATE refund SET status=#{status} WHERE re_id=#{rid}")
    void updateRefound(@Param("status") String status, @Param("rid") int rid);

    @Delete("DELETE FROM refund WHERE re_id=#{rid}")
    void deleteRefound(@Param("rid") int rid);


    @Select("SELECT *FROM refund WHERE re_id=#{rid}")
    @Results(
            value = {
                    @Result(property = "reId", column = "re_id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "tel", column = "tel"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "date", column = "refund_date"),
                    @Result(property = "money", column = "money"),
                    @Result(property = "orderId", column = "order_id"),
                    @Result(property = "preservationId", column = "preservation_id"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "reType", column = "order_type")
            }
    )
    Refound queryRefound(@Param("rid") int rid);
}
