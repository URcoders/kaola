package com.kaola.manager.service;

import com.kaola.manager.constances.Message;
import com.kaola.manager.constances.Status;
import com.kaola.manager.dao.*;
import com.kaola.manager.dto.ResponseData;
import com.kaola.manager.model.Order;
import com.kaola.manager.model.Preservation;
import com.kaola.manager.model.Refound;
import com.kaola.manager.model.User;
import com.kaola.manager.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author linxu
 * @date 2019/12/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Service
@Slf4j
public class RefoundManagerServiceImpl implements RefoundManagerService {
    @Autowired
    private RefoundMapper refoundMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PreservationMapper preservationMapper;
    @Autowired
    private SitMapper sitMapper;
    @Autowired
    private UserAndMealMapper userAndMealMapper;

    @Autowired
    private RefoundManagerService refoundManagerService;

    @Override
    public ResponseData listAllRefoundRecordByDate(String tokens, String date) {
        ResponseData<List<Refound>> responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                responseData.setData(refoundMapper.queryAllRefound(date));
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
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
    public ResponseData deleteRefoundRecordById(String tokens, int rid) {
        ResponseData responseData = new ResponseData<>();
        if (VerifyUtil.haveRight(tokens)) {
            try {
                refoundMapper.deleteRefound(rid);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } catch (Exception e) {
                e.printStackTrace();
                responseData.setStatus(Status.SERVER_ERROR.getStatus());
                responseData.setMsg(Message.SERVER_ERROR.getContent());
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public ResponseData updateRefoundRecordStatusById(String tokens, int rid, String status) {
        ResponseData responseData = new ResponseData();
        //solve special case
        if (status == null) {
            responseData.setMsg(Message.DATA_FORMAT_ERROR.getContent());
            responseData.setStatus(Status.FAIL.getStatus());
            return responseData;
        }

        if (VerifyUtil.haveRight(tokens)) {
            //判断是怎么处理退款，拒绝
            if (Refound.Status.REJECTED.reject().equals(status.trim())) {
                refoundMapper.updateRefound(status, rid);
                responseData.setStatus(Status.OK.getStatus());
                responseData.setMsg(Message.OP_OK.getContent());
            } else if (Refound.Status.ACCEPTED.accept().equals(status.trim())) {
                // 同意请求：分类：充值订单、预约订单、套餐订单进行对应的处理
                log.info("查询退款信息：" + rid);
                Refound refound = refoundMapper.queryRefound(rid);
                log.info("查询退款-订单号：" + refound.getOrderId());
                //查询订单
                Order order = orderMapper.queryOrderByOrderId(refound.getOrderId());
                //处理分类
                if (order.getOrderType().trim().equals(Order.OrderType.TOPUP_TYPE.type())) {
                    //充值订单处理
                    try {
                        //必须由bean去调用
                        refoundManagerService.processTopup(order.getUserId(), order.getOrderMoney(), order);
                        //更改退款状态
                        refoundMapper.updateRefound(Refound.Status.ACCEPTED.accept(),rid);
                        responseData.setStatus(Status.OK.getStatus());
                        responseData.setMsg(Message.OP_OK.getContent());
                    } catch (Exception e) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        responseData.setMsg("处理充值订单退款出现异常，原因可能是用户余额不足回扣.");
                        responseData.setStatus(Status.FAIL.getStatus());
                    }
                } else if (order.getOrderType().trim().equals(Order.OrderType.MEAL_TYPE.type())) {
                    //处理套餐购买订单
                    try {
                        refoundManagerService.processMeal(order); //更改退款状态
                        refoundMapper.updateRefound(Refound.Status.ACCEPTED.accept(),rid);
                        responseData.setStatus(Status.OK.getStatus());
                        responseData.setMsg(Message.OP_OK.getContent());
                    } catch (Exception e) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        log.error("处理套餐订单退款出现异常，事务回滚.");
                        responseData.setMsg("处理套餐退款信息出现异常.");
                        responseData.setStatus(Status.FAIL.getStatus());
                    }
                } else if (order.getOrderType().trim().equals(Order.OrderType.PRESERVATION_TYPE.type())) {
                    //处理预约订单
                    try {
                        refoundManagerService.processPreservation(order);
                        //更改退款状态
                        refoundMapper.updateRefound(Refound.Status.ACCEPTED.accept(),rid);
                        //TODO 删除预约记录
                        preservationMapper.deletePreservationRecord(order.getPreservationId());
                        responseData.setStatus(Status.OK.getStatus());
                        responseData.setMsg(Message.OP_OK.getContent());
                    } catch (Exception e) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        log.error("处理预约订单退款出现异常，事务回滚.");
                        responseData.setMsg("处理预约订单退款出现异常.");
                        responseData.setStatus(Status.FAIL.getStatus());
                    }
                }
            }
        } else {
            responseData.setStatus(Status.FAIL.getStatus());
            responseData.setMsg(Message.HAVE_NO_RIGHT.getContent());
        }
        return responseData;
    }

    /**
     * 共用一个事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void processTopup(int uid, double topUpMoney, Order order) throws Exception {
        User user = userMapper.queryUserById(uid);
        if (user != null) {
            Double rareMoney = user.getRareMoney();
            //执行余额更新
            if (rareMoney - topUpMoney >= 0.001d) {
                user.setRareMoney(rareMoney - topUpMoney);
                userMapper.updateUserInfo(user);
            }else {
                throw new Exception("余额不足回扣.");
            }
            //TODO 订单状态修改
            order.setOrderStatus("同意退款");
            orderMapper.updateOrderStatus(order);
        }
    }

    /**
     * 共用一个事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void processMeal(Order order) throws Exception {
        try {
            //删除套餐记录
            userAndMealMapper.deleteUserAndMeal(order.getOrderId());
            //TODO 订单状态修改
            order.setOrderStatus("同意退款");
            orderMapper.updateOrderStatus(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new Exception("处理套餐信息出现异常.");
        }
    }

    /**
     * 共用一个事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void processPreservation(Order order) throws Exception {
        try {
            //查询预约记录
            Preservation preservation = preservationMapper.queryPreservationById(order.getPreservationId());
            sitMapper.updateSitStatus(preservation.getSitId(), preservation.getRoomId(), order.getStoreId(), preservation.getPreservationDate().split(" ")[1], preservation.getPreservationDate().split(" ")[0]);
            //修改订单状态
            //TODO 订单状态修改
            order.setOrderStatus("同意退款");
            orderMapper.updateOrderStatus(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("处理预约退款信息出现异常.");
        }
    }
}
