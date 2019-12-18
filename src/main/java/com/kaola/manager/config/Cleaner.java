package com.kaola.manager.config;

import com.kaola.manager.dao.SitMapper;
import com.kaola.manager.memory.SharableMemory;
import com.kaola.manager.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author linxu
 * @date 2019/12/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@EnableScheduling
@Slf4j
@Component
public class Cleaner {
    @Autowired
    private SitMapper sitMapper;

    /**
     * 每天晚上0.40  清理过期座位数据以及过期TOKENS
     */
    @Scheduled(cron = "0  40  00  *  *  ?")
    public void cleanTheExpiredSits(){
       int logNumber= sitMapper.deleteSits(DateUtil.getY_M_D());
       log.warn("删除过期座位数量"+logNumber);
        SharableMemory.TOKENS_N_EXPIRE.clear();
    }

}
