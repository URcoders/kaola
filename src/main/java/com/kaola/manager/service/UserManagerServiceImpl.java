package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.UserMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.dto.WrapperFactory;
import com.kaola.manager.model.User;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linxu
 * @date 2019/11/24
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class UserManagerServiceImpl implements UserManagerService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseData addUser(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            User user = WrapperFactory.buildUser(requestData);
            try {
                userMapper.addUser(user);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("添加用户失败，用户信息为：{}", user.toString());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg("添加用户失败！");
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData listAllUserNotInBlackList(String tokens) {
        ResponseData<List<User>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(userMapper.queryAllUserNotInBlackList());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("查询不在黑名单中的用户出现异常,时间：{}", DateUtil.getCurrentTime());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());

            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData listAllUserInBlackList(String tokens) {
        ResponseData<List<User>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(userMapper.queryAllUserInBlackList());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("查询在黑名单中的用户出现异常,时间：{}", DateUtil.getCurrentTime());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData queryUserByName(String tokens, String name) {
        ResponseData<List<User>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(userMapper.queryUserByName(name));
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("查询用户名字为{}，出现异常！", name);
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData deleteUserById(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                userMapper.deleteUserById(requestData.getUserId());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("删除用户出现异常！用户ID为{}", requestData.getUserId());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData modifyUserInfo(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            User user = WrapperFactory.buildUser(requestData);
            try {
                userMapper.updateUserInfo(user);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("更新信息失败，用户的新信息为{}", user.toString());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData moveUserToBlackList(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                userMapper.addBlackList(requestData.getUserId(), requestData.getTel());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("将用户添加入黑名单失败，用户ID为:{},用户电话{}", requestData.getUserId(), requestData.getTel());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData moveUserFromBlackList(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                userMapper.removeFromBlackList(requestData.getUserId());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                log.error("移出黑名单失败，用户ID为:{}", requestData.getUserId());
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
}
