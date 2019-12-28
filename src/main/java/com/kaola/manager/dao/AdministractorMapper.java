package com.kaola.manager.dao;

import com.kaola.manager.model.Administractor;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface AdministractorMapper {
    @Select("SELECT * FROM administrator WHERE username=#{admin.username} AND psw=#{admin.psw};")
    @Results(value =
            {
                    @Result(property = "username",column = "username"),
                    @Result(property = "privo",column = "privo"),
                    @Result(property = "realName",column = "real_name"),
                    @Result(property = "tel",column = "tel"),
            })
    Administractor verifyAccount(@Param("admin") Administractor administractor);

    @Select("SELECT * FROM administrator;")
    @Results(value =
            {
                    @Result(property = "username",column = "username"),
                    @Result(property = "privo",column = "privo"),
                    @Result(property = "realName",column = "real_name"),
                    @Result(property = "tel",column = "tel"),
            })
    List<Administractor> queryAllAdmin();

    @Update("UPDATE administrator SET username=#{admin.username},psw=#{admin.psw},tel=#{admin.tel},real_name=#{admin.realName} WHERE username=#{admin.username};")
    void modifyAdmin(@Param("admin") Administractor administractor);

    @Delete("DELETE FROM administrator WHERE username=#{admin.username}")
    void deleteAdmin(@Param("admin") Administractor administractor);

    @Insert("INSERT INTO administrator (username,psw,tel,real_name,privo)VALUES(#{admin.username},#{admin.psw},#{admin.tel},#{admin.realName},#{admin.privo})")
    void addAdmin(@Param("admin") Administractor administractor);
}
