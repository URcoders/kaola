package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface MealManagerService {
    ResponseData addMeal(RequestData requestData);

    ResponseData deleteMeal(RequestData requestData);

    ResponseData listAllMeal(RequestData requestData);

    ResponseData modifyMealInfo(RequestData requestData);

    ResponseData listUserAndMealCase(String tokens, int mealId);
}
