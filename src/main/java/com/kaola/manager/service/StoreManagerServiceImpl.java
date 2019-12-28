package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.constances.StoreStatus;
import com.kaola.manager.dao.StoreMapper;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Store;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kaola.manager.constances.Message.*;
import static com.kaola.manager.constances.Status.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class StoreManagerServiceImpl implements StoreManagerService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public ResponseData listStore(String tokens) {
        ResponseData<List<Store>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                List<Store> stores = storeMapper.queryAllStore();
                responseData.setMsg(OP_OK.getContent());
                responseData.setStatus(OK.getStatus());
                responseData.setData(stores);
            } catch (Exception e) {
                log.error("查询门店异常.时间：{}", DateUtil.getCurrentTime());
                responseData.setStatus(FAIL.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setMsg(HAVE_NO_RIGHT.getContent());
            responseData.setStatus(FAIL.getStatus());
        }
        return responseData;
    }


    @Override
    public ResponseData createStore(String tokens, String storeName, String storeAddress,String UID,String tel,String time,String verifyCode) {
        ResponseData<List<Store>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                //构造新的商店
                Store store = new Store();
                store.setStoreAddress(storeAddress);
                store.setStoreName(storeName);
                store.setUid(UID);
                store.setTel(tel);
                store.setStoreTime(time);
                store.setStoreStatus(StoreStatus.RUNNING.getStatus());
                store.setVerifyCode(verifyCode);
                //持久化
                storeMapper.addStore(store);
                responseData.setMsg(OP_OK.getContent());
                responseData.setStatus(OK.getStatus());
            } catch (Exception e) {
                log.error("添加门店出现异常.时间：{}", DateUtil.getCurrentTime());
                responseData.setStatus(FAIL.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setMsg(HAVE_NO_RIGHT.getContent());
            responseData.setStatus(FAIL.getStatus());
        }
        return responseData;
    }

    @Override
    public ResponseData deleteStore(String tokens, int id) {
        ResponseData<List<Store>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                //持久化
                storeMapper.deleteStore(id);
                responseData.setMsg(OP_OK.getContent());
                responseData.setStatus(OK.getStatus());
            } catch (Exception e) {
                log.error("删除门店出现异常.时间：{}", DateUtil.getCurrentTime());
                responseData.setStatus(FAIL.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setMsg(HAVE_NO_RIGHT.getContent());
            responseData.setStatus(FAIL.getStatus());
        }
        return responseData;
    }

    @Override
    public ResponseData modifyStore(Store store) {
        ResponseData<String> responseData = new ResponseData<>();
        if (verifyStoreInfo(store)) {
            try {
                storeMapper.modifyStore(store);
                responseData.setMsg(OP_OK.getContent());
                responseData.setStatus(OK.getStatus());
            } catch (Exception e) {
                log.error("门店基础信息修改出现异常.");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }

        } else {
            responseData.setMsg(DATA_FORMAT_ERROR.getContent());
            responseData.setStatus(FAIL.getStatus());
        }
        return responseData;
    }

    /**
     * 不允许门店的名称以及状态为空，地址描述可以为空
     *
     * @param store s
     * @return t or f
     */
    private boolean verifyStoreInfo(Store store) {
        if (store == null) {
            return false;
        } else {
            return store.getStoreName() != null && store.getStoreId() != 0;
        }
    }

    @Override
    public ResponseData queryStoreByStoreId(int storeId) {
        ResponseData<Store> responseData = new ResponseData<>();
        try {
            Store store = storeMapper.queryStoreById(storeId);
            responseData.setStatus(Status.OK.getStatus());
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setData(store);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus(Status.SERVER_ERROR.getStatus());
            responseData.setMsg(Message.SERVER_ERROR.getContent());
        }
        return responseData;
    }
}
