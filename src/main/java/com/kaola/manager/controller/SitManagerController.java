package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.SitManagerService;
import com.kaola.manager.service.SitManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/sit")
@CrossOrigin(origins = {"*"})
public class SitManagerController {
    @Autowired
    private SitManagerServiceImpl sitManagerService;

    @PostMapping("/listSit")
    public ResponseData listSitByRoom(@RequestBody RequestData requestData) {
        return sitManagerService.listSitsBySpecifyRoom(requestData);
    }

    @PostMapping("/addSit")
    public ResponseData addSitByRoom(@RequestBody RequestData requestData) {
        return sitManagerService.addSitBySpecifyRoom(requestData);
    }

    @PostMapping("/addBatchSit")
    public ResponseData addBatchSitByRoom(@RequestBody RequestData requestData) {
        return sitManagerService.addBatchSitBySpecifyRoom(requestData);
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
