package com.kaola.manager.dao;

import com.kaola.manager.model.Preservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface PreservationMapper {
    /**
     * 查询预约记录
     */
    @SelectProvider(value = Provider.class, method = "selectPreservation")
    @Results(
            value = {
                    @Result(property = "sitId", column = "sit_id"),
                    @Result(property = "roomId", column = "room_id"),
                    @Result(property = "roomType", column = "room_type"),
                    @Result(property = "tel", column = "tel"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "storeName", column = "store_name"),
                    @Result(property = "preservationId", column = "preservation_id"),
                    @Result(property = "preservationDate", column = "preservation_date")

            }
    )
    List<Preservation> queryPreservationRecords(@Param("preservationTime") String date);

    @Delete("DELETE FROM preservation WHERE preservation_id=#{pid}")
    void deletePreservationRecord(@Param("pid") int pid);
}
