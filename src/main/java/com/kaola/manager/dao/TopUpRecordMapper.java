package com.kaola.manager.dao;

import com.kaola.manager.model.TopUpRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/26
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface TopUpRecordMapper {
    @Select("SELECT * FROM moneyRecord WHERE userId=#{userId}")
    List<TopUpRecord> queryRecordByUserId(@Param("userId")int userId);
}
