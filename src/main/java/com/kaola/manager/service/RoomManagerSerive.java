package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface RoomManagerSerive {
    /**
     * 查询门店下的所有房间
     *
     * @param requestData
     * @return
     */
    ResponseData listRoomByStoreId(RequestData requestData);

    /**
     * 根据房间号码、房间类型、门店ID删除房间
     *
     * @param requestData
     * @return
     */
    ResponseData deleteRoomByStoreIdAndRoomIdAndRoomType(RequestData requestData);

    /**
     * 添加房间
     *
     * @param requestData
     * @return
     */
    ResponseData addRoom(RequestData requestData);
}
