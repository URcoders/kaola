package com.kaola.manager.controller;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Preservation;
import com.kaola.manager.service.PreservationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/preserve")
@CrossOrigin(origins = {"*"})
public class PreservationController {
    @Autowired
    private PreservationManagerService preservationManagerService;

    @GetMapping("/listRecord")
    public ResponseData listRecord(@RequestParam("tokens") String tokens, @RequestParam("date") String date) {
        return preservationManagerService.listPreservationRecords(tokens, date);
    }

    @PostMapping("/addPreservation")
    public ResponseData addPreservation(@RequestBody RequestData requestData){
        return preservationManagerService.addPreservation(requestData);
    }

    @PostMapping("/deleteRecord")
    public ResponseData listRecord(@RequestParam("tokens") String tokens, @RequestParam("pid") int pid) {
        return preservationManagerService.deletePreservationRecords(tokens, pid);
    }

    @GetMapping("/queryPreservationByUserId")
    public ResponseData listUserRecord(@RequestParam("tokens") String tokens, @RequestParam("userId") int uid) {
        return preservationManagerService.listPreservationRecordsByUid(tokens, uid);
    }

}
