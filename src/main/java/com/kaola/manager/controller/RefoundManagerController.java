package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.service.RefoundManagerService;
import com.kaola.manager.service.RefoundManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/12/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RequestMapping("/admin/refound")
@CrossOrigin(origins = {"*"})
@RestController
public class RefoundManagerController {
    @Autowired
    private RefoundManagerServiceImpl refoundManagerService;

    @GetMapping("/listAllRefound")
    public ResponseData listAllRefound(@RequestParam("tokens") String token, @RequestParam("date") String date) {
        return refoundManagerService.listAllRefoundRecordByDate(token, date);
    }

    @PostMapping("/deleteRefound")
    public ResponseData deleteRefound(@RequestBody RequestData requestData) {
        return refoundManagerService.deleteRefoundRecordById(requestData.getTokens(), requestData.getReId());
    }

    @PostMapping("/processRefound")
    public ResponseData processRefound(@RequestBody RequestData requestData){
        return refoundManagerService.updateRefoundRecordStatusById(requestData.getTokens(),requestData.getReId(),requestData.getStatus());
    }
}
