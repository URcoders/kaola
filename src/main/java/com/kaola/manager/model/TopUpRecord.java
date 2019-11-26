package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/26
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class TopUpRecord {
    private int id;
    private int userId;
    private String changedDate;
    private Double money;
    private String reason;
}
