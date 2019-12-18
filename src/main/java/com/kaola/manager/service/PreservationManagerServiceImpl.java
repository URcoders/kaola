package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.PreservationMapper;
import com.kaola.manager.dao.SitMapper;
import com.kaola.manager.dao.UserMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Preservation;
import com.kaola.manager.model.Sit;
import com.kaola.manager.model.User;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/11/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class PreservationManagerServiceImpl implements PreservationManagerService {
    @Autowired
    private PreservationMapper preservationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SitMapper sitMapper;

    @Override
    public ResponseData listPreservationRecords(String tokens, String date) {
        ResponseData<List<Preservation>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(preservationMapper.queryPreservationRecords(date));
                //reverse
                Collections.reverse(responseData.getData());
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("查询预约记录出现异常！");
                responseData.setMsg("查询预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData deletePreservationRecords(String tokens, int pid) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                preservationMapper.deletePreservationRecord(pid);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("删除预约记录出现异常！");
                responseData.setMsg("删除预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData listPreservationRecordsByUid(String tokens, int uid) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(preservationMapper.queryPreservationByUserId(uid));
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("删除预约记录出现异常！");
                responseData.setMsg("删除预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResponseData addPreservation(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (!VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                //verify tel
                User user = userMapper.queryUserByTel(requestData.getTel());
                if (user == null) {
                    responseData.setMsg("该手机号码不存在，无法添加预约记录！");
                    responseData.setStatus(Status.FAIL.getStatus());
                    return responseData;
                }
                //verify sits.
                String startDate = requestData.getStartDate();
                String endDate = requestData.getEndDate();
                List<Sit> sitList = new LinkedList<>();
                //构造座位连续时间段
                List<String> sitTimes = buildSitTime(requestData.getLockTime());
                List<String> curDates;
                //如果是一天的。
                curDates = DateUtil.get_Start_End(startDate, endDate);
                for (String date : curDates
                        ) {
                    for (String time : sitTimes) {
                        Sit sit = new Sit();
                        sit.setRoomId(requestData.getRoomId());
                        sit.setRoomType(requestData.getRoomType());
                        sit.setStoreId(requestData.getStoreId());
                        sit.setCurDate(date);
                        sit.setSitDate(time);
                        sit.setSitId(requestData.getSitId());
                        sitList.add(sit);
                        System.out.println(sit);
                    }
                }
                List<Preservation> preservationList;
                //verify sit
                if (verifySitFree(sitList)) {
                    try {
                        //修改座位为不空闲
                        sitMapper.updateSits(sitList);
                        //TODO 添加预约记录
                        preservationList = buildPreservationList(curDates, sitTimes, requestData, user);
                        preservationMapper.addPreservation(preservationList);
                    } catch (Exception e) {
                        log.error("事务失败！");
                        e.printStackTrace();
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        responseData.setMsg("添加预约记录失败,原因可能是服务器网络波动或者数据库出现问题。");
                        responseData.setStatus(Status.FAIL.getStatus());
                        return responseData;
                    }
                } else {
                    responseData.setMsg("添加预约记录失败，该座位在此时间段存在被占用的情况，请换个座位！");
                    responseData.setStatus(Status.FAIL.getStatus());
                    return responseData;
                }
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                responseData.setMsg("添加预约记录出现异常！");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
            }
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    /**
     * 0 为座位都空闲，1为有的座位不空闲。
     */
    private boolean verifySitFree(List<Sit> sitList) {
        int preserved = 0;
        for (Sit s : sitList) {
            preserved = sitMapper.verifySit(s.getSitId(), s.getRoomId(), s.getStoreId(), s.getSitDate(), s.getCurDate()).getPreserved();
        }
        return preserved == 0;
    }

    private List<String> buildSitTime(String lockTime) {
        String[] lockTimes = lockTime.split("-");
        String startTime = lockTimes[0];
        String endTime = lockTimes[1];
        List<String> list = new ArrayList<>();
        int startIdx = Integer.parseInt(startTime.split(":")[0]);
        int endIdx = Integer.parseInt(endTime.split(":")[0]);
        int length = endIdx - startIdx;
        for (int i = 0; i < length; i++) {
            list.add(Sit.SIT_DATE_TABLE.get(i));
            System.err.println(Sit.SIT_DATE_TABLE.get(i));
        }
        return list;
    }

    private List<Preservation> buildPreservationList(List<String> dates, List<String> sitTimes, RequestData requestData, User user) {
        List<Preservation> preservationList = new ArrayList<>(dates.size());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sitTimes.size() - 1; i++) {
            stringBuilder.append(sitTimes.get(i) + ",");
        }
        stringBuilder.append(sitTimes.get(sitTimes.size() - 1));
        String time = stringBuilder.toString();
        for (String date : dates) {
            Preservation preservation = new Preservation();
            preservation.setRoomId(requestData.getRoomId());
            preservation.setPreservationDate(date + " " + time);
            preservation.setSitId(requestData.getSitId());
            preservation.setRoomType(requestData.getRoomType());
            preservation.setStoreId(requestData.getStoreId());
            preservation.setUserId(user.getUserId());
            preservationList.add(preservation);
        }
        return preservationList;
    }


}
