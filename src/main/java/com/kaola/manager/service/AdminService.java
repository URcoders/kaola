package com.kaola.manager.service;

import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Administractor;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface AdminService {
    /**
     * 管理员登录
     */
    ResponseData login(Administractor administractor);

    /**
     * 管理员注销
     */
    ResponseData logout(String token);
}
