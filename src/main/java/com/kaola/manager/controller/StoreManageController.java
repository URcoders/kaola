package com.kaola.manager.controller;


import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Store;
import com.kaola.manager.service.StoreManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RequestMapping("/admin/store")
@CrossOrigin(origins = {"*"})
public class StoreManageController {
    @Autowired
    private StoreManagerService storeManagerService;

    @GetMapping("/listStore")
    public ResponseData listStore(@RequestParam("tokens") String tokens) {
        return storeManagerService.listStore(tokens);
    }

    @PostMapping("/deleteStore")
    public ResponseData deleteStore(@RequestBody RequestData requestData) {
        return storeManagerService.deleteStore(requestData.getTokens(), requestData.getStoreId());
    }

    @PostMapping("/addStore")
    public ResponseData addStore(@RequestBody RequestData requestData) {
        return storeManagerService.createStore(requestData.getTokens(), requestData.getStoreName(), requestData.getStoreAddress());
    }

    @PostMapping("/modifyStore")
    public ResponseData modifyStore(@RequestBody Store store) {
        return storeManagerService.modifyStore(store);
    }

    @GetMapping("/queryStoreByStoreId")
    public ResponseData queryStoreByStoreId(@RequestParam("tokens") String tokens, @RequestParam("storeId") int storeId) {
        return storeManagerService.queryStoreByStoreId(storeId);
    }
}
