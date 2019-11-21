package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/11/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class Room {
    private String roomId;
    private String roomType;
    private int storeId;

    public Room() {
    }

    public Room(String roomId, String roomType, int storeId) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.storeId = storeId;
    }
}
