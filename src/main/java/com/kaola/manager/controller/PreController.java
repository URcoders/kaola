package com.kaola.manager.controller;

import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Administractor;
import com.kaola.manager.service.AdminService;
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

    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody Administractor administractor) {
        return adminService.login(administractor);
    }

    @PostMapping("/exit")
    public ResponseData<String> logout(@RequestBody String token) {
        return adminService.logout(token);
    }
}
