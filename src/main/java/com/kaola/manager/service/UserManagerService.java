package com.kaola.manager.service;

import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public interface UserManagerService {
    ResponseData addUser(RequestData requestData);

    ResponseData listAllUserNotInBlackList(String tokens);

    ResponseData listAllUserInBlackList(String tokens);

    ResponseData queryUserByName(String tokens, String name);

    ResponseData deleteUserById(RequestData requestData);

    ResponseData modifyUserInfo(RequestData requestData);

    ResponseData moveUserToBlackList(RequestData requestData);

    ResponseData moveUserFromBlackList(RequestData requestData);


}
