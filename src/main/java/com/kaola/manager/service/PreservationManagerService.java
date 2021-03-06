package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface PreservationManagerService {
    ResponseData listPreservationRecords(String tokens, String date);
    ResponseData listPreservationRecordsByUid(String tokens, int uid);

    ResponseData deletePreservationRecords(String tokens,int pid);

    ResponseData addPreservation(RequestData requestData);
}
