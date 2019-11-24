package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RequestMapping("/admin/user")
@RestController
public class UserController {
    @Autowired
    private UserManagerService userManagerService;

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


}
