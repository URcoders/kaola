package com.kaola.manager.dao;

import com.kaola.manager.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface UserMapper {

    @Select("SELECT *FROM user WHERE user_id NOT IN (SELECT id FROM blacklist)")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex,", column = "sex,"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "rareMoney", column = "rare_money"),
            @Result(property = "score", column = "score"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "job", column = "job"),
            @Result(property = "target", column = "target"),
            @Result(property = "realName", column = "real_name")
    })
    List<User> queryAllUserNotInBlackList();

    @Select("SELECT *FROM user WHERE tel=#{tel}")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex,", column = "sex,"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "rareMoney", column = "rare_money"),
            @Result(property = "score", column = "score"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "job", column = "job"),
            @Result(property = "target", column = "target"),
            @Result(property = "realName", column = "real_name")
    })
    User queryUserByTel(@Param("tel")String tel);

    @Insert("INSERT INTO user (user_id,name,sex,tel,rare_money,score,real_name) VALUES (#{user.userId},#{user.name},#{user.sex},#{user.tel},#{user.rareMoney},#{user.score},#{user.realName})")
    void addUser(@Param("user") User user);

    @Delete("DELETE FROM user WHERE user_id=#{id}")
    void deleteUserById(@Param("id") int userId);

    @Select("SELECT * FROM user WHERE name =#{name}")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex,", column = "sex,"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "rareMoney", column = "rare_money"),
            @Result(property = "score", column = "score"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "job", column = "job"),
            @Result(property = "target", column = "target"),
            @Result(property = "realName", column = "real_name")
    })
    List<User> queryUserByName(@Param("name") String name);

    @Update("UPDATE user SET name=#{user.name},tel=#{user.tel},sex=#{user.sex},score=#{user.score},rare_money=#{user.rareMoney},real_name=#{user.realName} WHERE user_id=#{user.userId}")
    void updateUserInfo(@Param("user") User user);

    /***********黑名单操作********************/
    @Insert("INSERT INTO blacklist (id,tel)VALUES(#{userId},#{tel})")
    void addBlackList(@Param("userId") int userId, @Param("tel") String tel);

    @Select("SELECT *FROM user WHERE user_id  IN (SELECT id FROM blacklist)")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex,", column = "sex,"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "rareMoney", column = "rare_money"),
            @Result(property = "score", column = "score"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "job", column = "job"),
            @Result(property = "target", column = "target"),
            @Result(property = "realName", column = "real_name")

    })
    List<User> queryAllUserInBlackList();
    @Select("SELECT *FROM user WHERE user_id =#{uid}")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex,", column = "sex,"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "rareMoney", column = "rare_money"),
            @Result(property = "score", column = "score"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "job", column = "job"),
            @Result(property = "target", column = "target"),
            @Result(property = "realName", column = "real_name")
    })
    User queryUserById(@Param("uid")int uid);

    @Insert("DELETE FROM blacklist WHERE id=#{userId}")
    void removeFromBlackList(@Param("userId") int userId);
}
