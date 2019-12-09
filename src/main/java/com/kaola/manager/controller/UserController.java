package com.kaola.manager.controller;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.TopUpRecordMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.TopUpRecord;
import com.kaola.manager.service.UserManagerService;
import com.kaola.manager.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RequestMapping("/admin/user")
@CrossOrigin(origins = {"*"})
@RestController
public class UserController {
    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    private TopUpRecordMapper mapper;
    @GetMapping("/listAllUser")
    public ResponseData listAllUsers(@RequestParam("tokens") String tokens) {
        return userManagerService.listAllUserNotInBlackList(tokens);
    }

    @GetMapping("/querySpecifyUser")
    public ResponseData queryUser(@RequestParam("tokens") String tokens, @RequestParam("name") String name) {
        return userManagerService.queryUserByName(tokens, name);
    }

    @PostMapping("/modifyUser")
    public ResponseData modifyUserInfo(@RequestBody RequestData requestData) {
        return userManagerService.modifyUserInfo(requestData);
    }

    @PostMapping("/deleteUser")
    public ResponseData deleteUserByUserId(@RequestBody RequestData requestData) {
        return userManagerService.deleteUserById(requestData);
    }

    @PostMapping("/moveToBlackList")
    public ResponseData moveToBlackList(@RequestBody RequestData requestData) {
        return userManagerService.moveUserToBlackList(requestData);
    }

    @PostMapping("/addUser")
    public ResponseData addUser(@RequestBody RequestData requestData) {
        return userManagerService.addUser(requestData);
    }

    @GetMapping("/listBlackList")
    public ResponseData listBlackList(@RequestParam("tokens") String tokens) {
        return userManagerService.listAllUserInBlackList(tokens);
    }

    @PostMapping("/removeUserFromBlackList")
    public ResponseData removeUserFromBlackList(@RequestBody RequestData requestData) {
        return userManagerService.moveUserFromBlackList(requestData);
    }

    @GetMapping("/listUserMoneyById")
    public ResponseData queryUserRecord(@RequestParam("tokens") String tokens,@RequestParam("userId") int userId){
        ResponseData<List<TopUpRecord>> responseData=new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)){
            try {
                responseData.setData(mapper.queryRecordByUserId(userId));
                responseData.setMsg(Message.OP_OK.getContent());
                responseData.setStatus(Status.OK.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
                responseData.setMsg(Message.SERVER_ERROR.getContent());
                responseData.setStatus(Status.FAIL.getStatus());
            }
        }else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }


}
