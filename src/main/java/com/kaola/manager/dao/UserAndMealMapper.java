package com.kaola.manager.dao;

import com.kaola.manager.model.UserAndMeal;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface UserAndMealMapper {
    //TODO 排除掉那些状态为失效的关联信息
    @Select("SELECT user_meal.*,user.name,user.tel  FROM user_meal   LEFT JOIN user ON user_meal.user_id=user.user_id  WHERE meal_id=#{id} ")
    @Results(value =
            {
                    @Result(property = "name", column = "name"),
                    @Result(property = "tel", column = "tel"),
                    @Result(property = "usedTimes", column = "used_times"),
                    @Result(property = "rareDays", column = "rare_days"),
                    @Result(property = "status", column = "status"),
            })
    List<UserAndMeal> queryUserAndMealByMealId(@Param("id") int mealId);

    @Delete("DELETE FROM user_meal WHERE order_id=#{orderId}")
    void deleteUserAndMeal(@Param("orderId") int orderId);

    /**
     * 退还套餐购买预约的次数
     * @param times
     * @param mealId
     * @param userId
     */
    @Update("UPDATE user_meal SET used_times=(used_times+#{times}) WHERE meal_id=#{mealId} AND user_id=#{userId} ")
    void backMealPreservationTimes(@Param("times") int times, @Param("mealId") int mealId, @Param("userId") int userId);
}
