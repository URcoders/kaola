package com.kaola.manager.service;

import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Store;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface StoreManagerService {
    /**
     * 带上口令查看商店
     *
     * @param tokens 口令
     * @return ...
     */
    ResponseData listStore(String tokens);

    ResponseData createStore(String tokens, String storeName, String storeAddress,String UID,String tel,String time,String verifyCode);


    ResponseData deleteStore(String tokens, int id);

    ResponseData modifyStore(Store s);

    ResponseData queryStoreByStoreId(int storeId);

}
