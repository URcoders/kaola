package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.dao.AdministractorMapper;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.memory.SharableMemory;
import com.kaola.manager.model.Administractor;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.DigestUtil;
import com.kaola.manager.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.kaola.manager.constances.Message.*;
import static com.kaola.manager.constances.Status.*;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdministractorMapper mapper;

    @Override
    public ResponseData login(Administractor administractor) {
        ResponseData<String> responseData = new ResponseData<>();
        //数据异常
        if (administractor == null) {
            responseData.setMsg(DATA_FORMAT_ERROR.getContent());
            responseData.setStatus(FAIL.getStatus());
        }
        //正常进行校验
        else {
            Administractor retAdmin;
            try {
                administractor.setPsw(DigestUtil.digest(administractor.getPsw(),2));
                retAdmin = mapper.verifyAccount(administractor);
                //登录成功
                if (retAdmin.getUsername() == null) {
                    //登录失败
                    responseData.setMsg(LOGIN_FAIL.getContent());
                    responseData.setStatus(FAIL.getStatus());
                } else {
                    responseData.setStatus(OK.getStatus());
                    responseData.setMsg(LOGIN_OK.getContent());
                    responseData.setData(doLogin(retAdmin.getUsername()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                //出现异常
                log.error("管理员登录出现异常，异常出现时间：{},管理员账号为：{}.异常信息为{}", DateUtil.getCurrentTime(), administractor.getUsername(), e.getMessage());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
                responseData.setStatus(FAIL.getStatus());
            }
        }
        return responseData;
    }

    /**
     * 将口令写入共享内存区，在分布式版本，这个共享内存区可交由REDIS代替
     */
    private String doLogin(String username) {
        String token = TokenUtil.createToken(username);
        SharableMemory.TOKENS_N_EXPIRE.put(token, DateUtil.currentTime() + TokenUtil.TIMEOUT);
        return token;
    }

    @Override
    public ResponseData logout(String token) {
        SharableMemory.TOKENS_N_EXPIRE.remove(token);
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(OK.getStatus());
        responseData.setMsg(EXIT_OK.getContent());
        log.warn("管理员口令:{}已经注销.", token);
        return responseData;
    }
}
