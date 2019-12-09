package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Order;
import com.kaola.manager.service.OrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/order")
@CrossOrigin(origins = {"*"})
public class OrderController {
    @Autowired
    private OrderManagerService orderManagerService;

    @PostMapping("/deleteOrder")
    public ResponseData deleteOrder(@RequestBody RequestData requestData) {
        return orderManagerService.deleteOrderByOrderId(requestData);
    }

    @GetMapping("/listOrderByType")
    public ResponseData listOrder(@RequestParam("orderType") String orderType, @RequestParam("tokens") String tokens,@RequestParam("orderDate")String orderDate) {
        //如果类型为空，则默认查看预约订单
        if (orderType == null) {
            orderType = Order.OrderType.PRESERVATION_TYPE.type();
        }
        return orderManagerService.listOrderByType(tokens, orderType,orderDate);
    }

}
