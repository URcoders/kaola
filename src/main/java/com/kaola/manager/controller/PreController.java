package com.kaola.manager.controller;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.PreOpenDoor;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Administractor;
import com.kaola.manager.service.AdminService;
import com.kaola.manager.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/user")
public class PreController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private PreOpenDoor openDoor;

    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody Administractor administractor) {
        return adminService.login(administractor);
    }

    @PostMapping("/exit")
    public ResponseData<String> logout(@RequestBody String token) {
        return adminService.logout(token);
    }

    @PostMapping("/updatePreTime")
    public ResponseData updateOpenDoorTime(@RequestBody RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            openDoor.updatePreOpenDoorTime(requestData.getPreTime());
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
}
