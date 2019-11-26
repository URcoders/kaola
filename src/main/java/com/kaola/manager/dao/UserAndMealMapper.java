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
                    @Result(property = "name",column = "name"),
                    @Result(property = "tel",column = "tel"),
                    @Result(property = "usedTimes",column = "used_times"),
                    @Result(property = "rareDays",column = "rare_days"),
                    @Result(property = "status",column = "status"),
            })
    List<UserAndMeal> queryUserAndMealByMealId(@Param("id") int mealId);
}
