package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class Administractor {
    private String username;
    private String psw;
    // 1为超级管理  0为普通管理
    private int privo;
    private String realName;
    private String tel;
}
