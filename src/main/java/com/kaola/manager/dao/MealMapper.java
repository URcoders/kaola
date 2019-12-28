package com.kaola.manager.dao;

import com.kaola.manager.model.Meal;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Mapper
public interface MealMapper {
    @Insert("INSERT INTO meal (money, meal_type, meal_desc, meal_days, name,used_times,display_date,on_sell,rare_number,shijianduan,display) VALUES (#{meal.mealMoney},#{meal.mealType},#{meal.mealDesc},#{meal.mealDays},#{meal.mealName},#{meal.usedTime},#{meal.disPlayDate},#{meal.onSell},#{meal.rareNumber},#{meal.shijianduan},#{meal.display})")
    void addMeal(@Param("meal") Meal meal);

    @Select("SELECT * FROM meal")
    @Results(
            value = {
                    @Result(property = "mealId", column = "meal_id"),
                    @Result(property = "mealMoney", column = "money"),
                    @Result(property = "mealType", column = "meal_type"),
                    @Result(property = "mealDesc", column = "meal_desc"),
                    @Result(property = "mealDays", column = "meal_days"),
                    @Result(property = "mealName", column = "name"),
                    @Result(property = "usedTime", column = "used_times"),
                    @Result(property = "onSell", column = "on_sell"),
                    @Result(property = "disPlayDate", column = "display_date"),
                    @Result(property = "rareNumber", column = "rare_number"),
                    @Result(property = "shijianduan", column = "shijianduan"),
                    @Result(property = "display", column = "display")
            }
    )
    List<Meal> queryMeals();

    @Delete("DELETE FROM meal WHERE meal_id=#{id}")
    void deleteMeal(@Param("id") int id);

    @Update("UPDATE meal SET name=#{meal.mealName},money=#{meal.mealMoney},meal_type=#{meal.mealType},meal_days=#{meal.mealDays},meal_desc=#{meal.mealDesc},used_times=#{meal.usedTime},display_date=#{meal.disPlayDate},rare_number=#{meal.rareNumber},on_sell=#{meal.onSell},shijianduan=#{meal.shijianduan},display=#{meal.display} WHERE meal_id=#{meal.mealId}")
    void updateMealInfo(@Param("meal") Meal meal);

}
