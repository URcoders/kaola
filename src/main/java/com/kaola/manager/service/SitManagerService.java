package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface SitManagerService {
    ResponseData listSitsBySpecifyRoom(RequestData requestData);

    ResponseData addSitBySpecifyRoom(RequestData requestData);

    ResponseData addBatchSitBySpecifyRoom(RequestData requestData);

    ResponseData deleteSitBySpecifyRoom(RequestData requestData);

    ResponseData modifySitBySpecifyRoom(RequestData requestData);
}
