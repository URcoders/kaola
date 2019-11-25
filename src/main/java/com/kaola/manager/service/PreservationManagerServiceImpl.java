package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.PreservationMapper;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Preservation;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class PreservationManagerServiceImpl implements PreservationManagerService {
    @Autowired
    private PreservationMapper preservationMapper;

    @Override
    public ResponseData listPreservationRecords(String tokens, String date) {
        ResponseData<List<Preservation>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(preservationMapper.queryPreservationRecords(date));
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("查询预约记录出现异常！");
                responseData.setMsg("查询预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData deletePreservationRecords(String tokens,int pid) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(tokens)) {
            try {
               preservationMapper.deletePreservationRecord(pid);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("删除预约记录出现异常！");
                responseData.setMsg("删除预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
}
