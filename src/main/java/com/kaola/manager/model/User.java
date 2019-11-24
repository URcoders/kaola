package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class User {
    private int userId;
    private String name;
    private String sex;
    private String tel;
    private String url;
    private Double rareMoney;
    private int score;
}
