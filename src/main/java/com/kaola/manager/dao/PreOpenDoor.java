package com.kaola.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface PreOpenDoor {
    @Update("UPDATE pre_open_door SET pre_time =#{time}")
    void updatePreOpenDoorTime(@Param("time")int time);
}
