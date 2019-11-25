package com.kaola.manager.dto;

import com.kaola.manager.model.Meal;
import com.kaola.manager.model.User;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class WrapperFactory {
    /**
     * 生产用户
     *
     * @param requestData 根据请求数据生产对象
     */
    public static User buildUser(RequestData requestData) {
        User user = new User();
        user.setName(requestData.getName());
        user.setRareMoney(requestData.getRareMoney());
        user.setScore(requestData.getScore());
        user.setTel(requestData.getTel());
        user.setSex(requestData.getSex());
        user.setUserId(requestData.getUserId());
        return user;
    }

    public static Meal buildMeal(RequestData requestData) {
        Meal meal = new Meal();
        meal.setMealDays(requestData.getMealDays());
        meal.setMealDesc(requestData.getMealDesc());
        meal.setMealId(requestData.getMealId());
        meal.setMealName(requestData.getMealName());
        meal.setMealType(requestData.getMealType());
        meal.setMealMoney(requestData.getMealMoney());
        return meal;
    }
}
