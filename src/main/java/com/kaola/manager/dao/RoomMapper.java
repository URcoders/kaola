package com.kaola.manager.dao;

import com.kaola.manager.model.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface RoomMapper {
    @Select("SELECT * FROM room WHERE store_id=#{storeId}")
    @Results(value =
            {
                    @Result(property = "roomId", column = "room_id"),
                    @Result(property = "roomType", column = "room_type"),
                    @Result(property = "storeId", column = "store_id")
            }

    )
    List<Room> queryRoomByStoreId(@Param("storeId") int storeId);

    @Insert("INSERT INTO room (room_id,room_type,store_id)VALUES(#{r.roomId},#{r.roomType},#{r.storeId})")
    void addRoom(@Param("r") Room room);

    @Delete("DELETE FROM room WHERE room_id=#{r.roomId} AND room_type=#{r.roomType} AND store_id=#{r.storeId}")
    void deleteRoom(@Param("r") Room room);

    @Update("UPDATE  room SET room_id=#{r.roomId} AND room_type=#{r.roomType} WHERE store_id=#{r.storeId}")
    void updateRoom(@Param("r") Room room);

    @Select("SELECT * FROM room WHERE room_id=#{r.roomId} AND room_type=#{r.roomType} AND store_id=#{r.storeId}")
    @Results(value =
            {
                    @Result(property = "roomId", column = "room_id"),
                    @Result(property = "roomType", column = "room_type"),
                    @Result(property = "storeId", column = "store_id")
            }

    )
    Room queryRoom(@Param("r") Room room);
}
