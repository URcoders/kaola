package com.kaola.manager.controller;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.AdministractorMapper;
import com.kaola.manager.dao.PreOpenDoor;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Administractor;
import com.kaola.manager.service.AdminService;
import com.kaola.manager.util.DigestUtil;
import com.kaola.manager.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/user")
@CrossOrigin(origins = {"*"})
public class PreController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private PreOpenDoor openDoor;
    @Autowired
    private AdministractorMapper administractorMapper;
    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody Administractor administractor) {
        return adminService.login(administractor);
    }

    @PostMapping("/exit")
    public ResponseData<String> logout(@RequestBody String token) {
        return adminService.logout(token);
    }

    /**
     * 添加管理员
     * @param requestData
     * @return
     */
    @PostMapping("/addAdmin")
    public ResponseData addAdmin(@RequestBody RequestData requestData){
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            Administractor administractor=new Administractor();
            administractor.setUsername(requestData.getUsername());
            administractor.setTel(requestData.getTel());
            administractor.setRealName(requestData.getRealName());
            administractor.setPrivo(0);
            administractor.setPsw(DigestUtil.digest(requestData.getPsw(),2));
            administractorMapper.addAdmin(administractor);
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
    /**
     * 添加管理员
     * @param requestData
     * @return
     */
    @PostMapping("/deleteAdmin")
    public ResponseData deleteAdmin(@RequestBody RequestData requestData){
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            Administractor administractor=new Administractor();
            administractor.setUsername(requestData.getUsername());
            administractorMapper.deleteAdmin(administractor);
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
    @PostMapping("/modifyAdmin")
    public ResponseData modifyAdmin(@RequestBody RequestData requestData){
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            Administractor administractor=new Administractor();
            administractor.setUsername(requestData.getUsername());
            administractor.setTel(requestData.getTel());
            administractor.setRealName(requestData.getRealName());
            administractor.setPsw(DigestUtil.digest(requestData.getPsw(),2));
            administractorMapper.modifyAdmin(administractor);
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
    @PostMapping("/queryAdmin")
    public ResponseData queryAdmin(@RequestBody RequestData requestData){
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            List<Administractor> administractors=administractorMapper.queryAllAdmin();
            responseData.setData(administractors);
            responseData.setMsg(Message.OP_OK.getContent());
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
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

    @GetMapping("/getPreTime")
    public ResponseData getOpenDoorTime() {
        ResponseData responseData = new ResponseData();
        responseData.setData(openDoor.queryCurTime());
        responseData.setStatus(Status.OK.getStatus());
        responseData.setMsg(Message.OP_OK.getContent());
        return responseData;
    }
}
