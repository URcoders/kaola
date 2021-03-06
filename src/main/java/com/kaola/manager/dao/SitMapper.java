package com.kaola.manager.dao;

import com.kaola.manager.model.Sit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface SitMapper {

    @Delete("DELETE FROM sits WHERE cur_date<#{curDate}")
    int deleteSits(@Param("curDate")String curDate);



    @Select("SELECT DISTINCT sit_id,room_id,store_id,room_type,money FROM sits WHERE room_id=#{roomId} AND store_id=#{storeId} AND room_type=#{roomType}")
    @Results(value = {
            @Result(property = "sitId", column = "sit_id"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "storeId", column = "store_id"),
            @Result(property = "roomType", column = "room_type"),
            @Result(property = "money", column = "money")
    })
    List<Sit> querySitsByRoom(@Param("roomId") String roomId, @Param("storeId") int storeId, @Param("roomType") String roomType);

    /**
     * 用于校验是否存在
     *
     * @param sitId
     * @param roomId
     * @param storeId
     * @param roomType
     * @return
     */
    @Select("SELECT DISTINCT sit_id,room_id,store_id,room_type,money FROM sits WHERE sit_id=#{sitId} AND room_id=#{roomId} AND store_id=#{storeId} AND room_type=#{roomType}")
    @Results(value = {
            @Result(property = "sitId", column = "sit_id"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "storeId", column = "store_id"),
            @Result(property = "roomType", column = "room_type"),
            @Result(property = "money", column = "money")
    })
    Sit exist(@Param("sitId") int sitId, @Param("roomId") String roomId, @Param("storeId") int storeId, @Param("roomType") String roomType);

    @Delete("DELETE FROM sits WHERE sit_id=#{sitId} AND room_id=#{roomId} AND store_id=#{storeId} AND room_type=#{roomType}")
    void deleteSit(@Param("sitId") int sitId, @Param("roomId") String roomId, @Param("storeId") int storeId, @Param("roomType") String roomType);

    /**
     * 更新每个座位的价格
     *
     * @param sitId
     * @param roomId
     * @param storeId
     * @param roomType
     */
    @Update("UPDATE sits SET money=#{money} WHERE sit_id=#{sitId} AND room_id=#{roomId} AND store_id=#{storeId} AND room_type=#{roomType} ")
    void updateSit(@Param("money") Double money, @Param("sitId") int sitId, @Param("roomId") String roomId, @Param("storeId") int storeId, @Param("roomType") String roomType);

    @Insert("INSERT INTO sits (sit_id,room_id,store_id,sit_date,money,preserved,room_type,cur_date) VALUES(#{s.sitId},#{s.roomId},#{s.storeId},#{s.sitDate},#{s.money},#{s.preserved},#{s.roomType},#{s.curDate})")
    void addSit(@Param("s") Sit sit);

    @Insert({
            "<script>",
            "insert into  sits(sit_id,room_id,store_id,sit_date,money,preserved,room_type,cur_date) values ",
            "<foreach collection='sitLists' item='s'  separator=','>",
            "(#{s.sitId},#{s.roomId},#{s.storeId},#{s.sitDate},#{s.money},#{s.preserved},#{s.roomType},#{s.curDate})",
            "</foreach>",
            "</script>"
    })
    void insertCollectList(@Param(value = "sitLists") List<Sit> testLists);

    @Update({"<script>" ,
            " <foreach collection='sitLists' item='s'  separator=';'>" ,
            " UPDATE" + "  sits   " ,
            "   set " ,
            "preserved=1 " ,
            "WHERE room_id=#{s.roomId} AND sit_id=#{s.sitId} AND store_id=#{s.storeId} AND sit_date=#{s.sitDate} AND cur_date=#{s.curDate} " ,
            "</foreach>" ,
            "</script>"})
    void updateSits(@Param(value = "sitLists") List<Sit> testLists);

    @Update("UPDATE sits SET preserved = 0 WHERE room_id=#{roomId} AND sit_id=#{sitId} AND store_id=#{storeId} AND sit_date=#{sitDate} AND cur_date=#{curDate}")
    void updateSitStatus(@Param("sitId") int sitId, @Param("roomId") String roomId, @Param("storeId") int storeId, @Param("sitDate") String sitDate, @Param("curDate") String curDate);

    @Results(value = {
            @Result(property = "sitId", column = "sit_id"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "storeId", column = "store_id"),
            @Result(property = "roomType", column = "room_type"),
            @Result(property = "money", column = "money"),
            @Result(property = "preserved", column = "preserved")
    })
    @Select("SELECT * FROM sits WHERE  room_id=#{roomId} AND sit_id=#{sitId} AND store_id=#{storeId} AND sit_date=#{sitDate} AND cur_date=#{curDate}")
    Sit verifySit(@Param("sitId") int sitId, @Param("roomId") String roomId, @Param("storeId") int storeId, @Param("sitDate") String sitDate, @Param("curDate") String curDate);
}
