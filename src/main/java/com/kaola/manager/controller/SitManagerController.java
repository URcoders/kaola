package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.SitManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/sit")
public class SitManagerController {
    @Autowired
    private SitManagerService sitManagerService;

    @PostMapping("/listSit")
    public ResponseData listSitByRoom(@RequestBody RequestData requestData) {
        return sitManagerService.listSitsBySpecifyRoom(requestData);
    }

    @PostMapping("/addSit")
    public ResponseData addSitByRoom(@RequestBody RequestData requestData) {
        return sitManagerService.addSitBySpecifyRoom(requestData);
    }

    @PostMapping("/deleteSit")
    public ResponseData deleteSit(@RequestBody RequestData requestData) {
        return sitManagerService.deleteSitBySpecifyRoom(requestData);
    }

    @PostMapping("/updateSit")
    public ResponseData updateSit(@RequestBody RequestData requestData) {
        return sitManagerService.modifySitBySpecifyRoom(requestData);
    }
}
