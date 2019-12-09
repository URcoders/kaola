package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.RoomManagerSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/room")
@CrossOrigin(origins = {"*"})
public class RoomManagerController {
    @Autowired
    private RoomManagerSerive roomManagerSerive;


    @GetMapping("/listRoom")
    public ResponseData listRoom(@RequestParam("tokens") String tokens, @RequestParam("storeId") int storeId) {
        RequestData requestData = new RequestData();
        requestData.setStoreId(storeId);
        requestData.setTokens(tokens);
        return roomManagerSerive.listRoomByStoreId(requestData);
    }

    @PostMapping("/deleteRoom")
    public ResponseData deleteRoom(@RequestBody RequestData requestData) {
        return roomManagerSerive.deleteRoomByStoreIdAndRoomIdAndRoomType(requestData);
    }

    @PostMapping("/addRoom")
    public ResponseData createRoom(@RequestBody RequestData requestData) {
        return roomManagerSerive.addRoom(requestData);
    }

}
