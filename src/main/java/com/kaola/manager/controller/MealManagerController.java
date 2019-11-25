package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.MealManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/meal")
public class MealManagerController {
    @Autowired
    private MealManagerService mealManagerService;


    @PostMapping("/addMeal")
    ResponseData addMeal(@RequestBody RequestData requestData) {
        return mealManagerService.addMeal(requestData);
    }

    @PostMapping("/deleteMeal")
    ResponseData deleteMeal(@RequestBody RequestData requestData) {
        return mealManagerService.deleteMeal(requestData);
    }

    @PostMapping("/listAllMeal")
    ResponseData listAllMeal(@RequestBody RequestData requestData) {
        return mealManagerService.listAllMeal(requestData);
    }

    @PostMapping("/modifyMeal")
    ResponseData modifyMeal(@RequestBody RequestData requestData) {
        return mealManagerService.modifyMealInfo(requestData);
    }
}
