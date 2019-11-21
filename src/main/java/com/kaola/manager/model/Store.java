package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class Store {
    private String storeName;
    private int storeId;
    private String storeAddress;
    private int storeStatus;
}
