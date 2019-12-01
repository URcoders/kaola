package com.kaola.manager.service;

import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Order;

/**
 * @author linxu
 * @date 2019/12/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface RefoundManagerService {
    ResponseData listAllRefoundRecordByDate(String tokens, String date);

    ResponseData deleteRefoundRecordById(String tokens, int rid);

    ResponseData updateRefoundRecordStatusById(String tokens, int rid,String status);

    void processPreservation(Order order) throws Exception;
    void processMeal(Order order) throws Exception;
    void processTopup(int uid, double topUpMoney, Order order) throws Exception;
}
