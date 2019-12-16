package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.OrderMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Order;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class OrderManagerServiceImpl implements OrderManagerService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseData deleteOrderByOrderId(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                orderMapper.deleteOrderById(requestData.getOrderId());
                responseData.setMsg(Message.OP_OK.getContent());
                responseData.setStatus(Status.OK.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("删除订单失败，订单编号为{}", requestData.getOrderId());
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
    public ResponseData listOrderByType(String tokens, String orderType,String orderDate) {
        ResponseData<List<Order>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(orderMapper.queryOrderByType(orderType.trim(),orderDate));
                //reverse
                Collections.reverse(responseData.getData());
                responseData.setMsg(Message.OP_OK.getContent());
                responseData.setStatus(Status.OK.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("查询{}订单失败，失败时间：{}", orderType, DateUtil.getCurrentTime());
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
