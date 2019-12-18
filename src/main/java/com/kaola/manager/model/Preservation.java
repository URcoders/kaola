package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class Preservation {
    private int preservationId;
    private int userId;
    /**
     * store-room-sit信息
     */
    private int sitId;
    private String roomId;
    private String roomType;
    private String storeName;
    private int storeId;
    /**
     * preservation-time
     */
    private String preservationDate;
    /**
     * 用户信息
     */
    private String name;
    private String tel;

}
