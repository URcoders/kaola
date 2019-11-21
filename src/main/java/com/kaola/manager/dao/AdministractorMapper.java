package com.kaola.manager.dao;

import com.kaola.manager.model.Administractor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface AdministractorMapper {
    @Select("SELECT * FROM administrator WHERE username=#{admin.username} AND psw=#{admin.psw};")
    Administractor verifyAccount(@Param("admin") Administractor administractor);
}
