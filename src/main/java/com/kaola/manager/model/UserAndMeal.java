package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 指定套餐的用户办理情况
 */
@Data
public class UserAndMeal {
    private int userId;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户电话号码
     */
    private String tel;
    /**
     * 使用次数
     */
    private int usedTimes;
    /**
     * 剩余天数
     */
    private int rareDays;
    /**
     *
     */
    private String status;
}
