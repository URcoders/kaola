package com.kaola.manager.service;


import com.kaola.manager.config.BackGroundPoolConfiguration;
import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.SitMapper;
import com.kaola.manager.dto.RequestData;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Sit;
import com.kaola.manager.util.DateUtil;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linxu
 * @date 2019/11/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class SitManagerServiceImpl implements SitManagerService {
    @Autowired
    private SitMapper sitMapper;

    @Autowired
    private BackGroundPoolConfiguration backGroundPoolConfiguration;

    @Override
    public ResponseData listSitsBySpecifyRoom(RequestData requestData) {
        ResponseData<List<Sit>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            try {
                List<Sit> sitList = sitMapper.querySitsByRoom(requestData.getRoomId(), requestData.getStoreId(), requestData.getRoomType());
                responseData.setData(sitList);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("查询指定门店指定房间下的座位出错.");
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }

        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData addSitBySpecifyRoom(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            //校验是否存在
            Sit existSit = sitMapper.exist(requestData.getSitId(), requestData.getRoomId(), requestData.getStoreId(), requestData.getRoomType());
            if (existSit != null) {
                responseData.setStatus(Status.FAIL.getStatus());
                responseData.setMsg("该门店的这个房间中已经存在这个座位，请不要重复添加!");
                return responseData;
            }
            Runnable runnable = () -> {
                //正常处理
                for (int i = 0; i < Sit.SIT_DATE_TABLE.size(); i++) {
                    for (String date : DateUtil.getY_M_DList()
                            ) {
                        Sit sit = new Sit();
                        sit.setMoney(requestData.getMoney());
                        sit.setRoomId(requestData.getRoomId());
                        sit.setSitDate(Sit.SIT_DATE_TABLE.get(i));
                        sit.setRoomType(requestData.getRoomType());
                        sit.setCurDate(date);
                        sit.setPreserved(0);
                        sit.setStoreId(requestData.getStoreId());
                        sit.setSitId(requestData.getSitId());
                        sitMapper.addSit(sit);
                    }
                }
            };
            //commit the async task.
            backGroundPoolConfiguration.runTask(runnable);
            responseData.setStatus(Status.OK.getStatus());
            responseData.setMsg(Message.OP_OK.getContent());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData addBatchSitBySpecifyRoom(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            //校验是否存在
            int startId = requestData.getSitStartId();
            int endId = requestData.getSitEndId();
            int[] sitsId = new int[endId - startId + 1];
            //init sit ID
            for (int i = 0; i < sitsId.length; i++) {
                sitsId[i] = startId + i;
            }
            //check exists;
            for (int i = 0; i <sitsId.length ; i++) {
                Sit existSit = sitMapper.exist(sitsId[i], requestData.getRoomId(), requestData.getStoreId(), requestData.getRoomType());
                if (existSit!=null){
                    responseData.setStatus(Status.FAIL.getStatus());
                    responseData.setMsg("该门店下存在重复的座位了，请查看批量添加的起始座位号与结束座位号！");
                    return responseData;
                }
            }
            //构造座位列表
            List<Sit> sitList=new ArrayList<>();
            for (int i = 0; i <sitsId.length ; i++) {
                for (int j = 0; j < Sit.SIT_DATE_TABLE.size(); j++) {
                    for (String date : DateUtil.getY_M_DList()
                            ) {
                        Sit sit = new Sit();
                        sit.setMoney(requestData.getMoney());
                        sit.setRoomId(requestData.getRoomId());
                        sit.setSitDate(Sit.SIT_DATE_TABLE.get(j));
                        sit.setRoomType(requestData.getRoomType());
                        sit.setCurDate(date);
                        sit.setPreserved(0);
                        sit.setStoreId(requestData.getStoreId());
                        sit.setSitId(sitsId[i]);
                        sitList.add(sit);
                    }
                }
            }

            //正式添加
            Runnable runnable = () -> {
                //正常处理
          /*  int number=  */  /*sitMapper.insertCollectList(sitList);*/
                for (Sit s:sitList
                     ) {
                    sitMapper.addSit(s);
                }
            //log.info("添加了一共{}条座位数据。",number);
            };
            //commit the async task.
            backGroundPoolConfiguration.runTask(runnable);
            responseData.setStatus(Status.OK.getStatus());
            responseData.setMsg(Message.OP_OK.getContent());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData deleteSitBySpecifyRoom(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {

            Sit sit = new Sit();
            sit.setRoomId(requestData.getRoomId());
            sit.setRoomType(requestData.getRoomType());
            //全部删除
            sit.setStoreId(requestData.getStoreId());
            sit.setSitId(requestData.getSitId());
            sitMapper.deleteSit(sit.getSitId(), sit.getRoomId(), sit.getStoreId(), sit.getRoomType());
            responseData.setStatus(Status.OK.getStatus());
            responseData.setMsg(Message.OP_OK.getContent());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Override
    public ResponseData modifySitBySpecifyRoom(RequestData requestData) {
        ResponseData responseData = new ResponseData();
        if (VerifyUtil.haveRight(requestData.getTokens())) {
            //为每个座位修改单价
            sitMapper.updateSit(requestData.getMoney(), requestData.getSitId(), requestData.getRoomId(), requestData.getStoreId(), requestData.getRoomType());
            responseData.setMsg("该座位价格修改成功!");
            responseData.setStatus(Status.OK.getStatus());
        } else {
            //莫得权限
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }
}
