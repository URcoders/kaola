package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface OrderManagerService {

    ResponseData deleteOrderByOrderId(RequestData requestData);

    ResponseData listOrderByType(String tokens, String orderType,String orderDate);
}
