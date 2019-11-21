package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.RoomMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Room;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/20
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class RoomManagerServiceImpl implements RoomManagerSerive {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public ResponseData listRoomByStoreId(RequestData requestData) {
        ResponseData<List<Room>> responseData = new ResponseData<>();
        //拥有权限
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                //正常查询
                List<Room> roomList = roomMapper.queryRoomByStoreId(requestData.getStoreId());
                responseData.setData(roomList);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("查询门店出现异常，门店的ID是：{}", requestData.getStoreId());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData deleteRoomByStoreIdAndRoomIdAndRoomType(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            Room room = new Room(requestData.getRoomId(), requestData.getRoomType(), requestData.getStoreId());
            try {
                roomMapper.deleteRoom(room);
                responseData.setMsg(Message.OP_OK.getContent());
                responseData.setStatus(Status.OK.getStatus());
            } catch (Exception e) {
                log.error("删除门店的房间出现异常，门店号为{},房间号为{},房间类型为{}", room.getStoreId(), room.getRoomId(), room.getRoomType());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData addRoom(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            Room room = new Room(requestData.getRoomId(), requestData.getRoomType(), requestData.getStoreId());
            //add
            try {
                Room isExist = roomMapper.queryRoom(room);
                //不存在
                if (isExist == null) {
                    roomMapper.addRoom(room);
                    responseData.setMsg(Message.OP_OK.getContent());
                    responseData.setStatus(Status.OK.getStatus());
                }//存在
                else {
                    responseData.setMsg("该门店下已经存在相同的房间，不要重复创建.");
                    responseData.setStatus(Status.OK.getStatus());
                }

            } catch (Exception e) {
                log.error("创建门店的房间出现异常，门店号为{},房间号为{},房间类型为{}", room.getStoreId(), room.getRoomId(), room.getRoomType());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
}
