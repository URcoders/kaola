package com.kaola.manager.config;

import com.kaola.manager.pool.RmdThreadFactory;
import com.kaola.manager.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Lx
 * @date 12月
 * @package com.kaola.manager.config
 */
@Configuration
@Slf4j
public class BackGroundPoolConfiguration {
    private ThreadPoolExecutor threadPoolExecutor;
    private volatile boolean hasStarted = false;

    @PostConstruct
    public void init() {
        log.info("THREAD POOL INIT.");
        this.threadPoolExecutor = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new RmdThreadFactory("BACK-GROUND-T", false));
        // do pre start.
        threadPoolExecutor.prestartAllCoreThreads();
        hasStarted=true;
    }

    @PreDestroy
    public void destroy() {
        log.warn("THREAD POOL GC.");
        if (!threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdown();
        }
    }

    /**
     * 调用这个接口执行背后任务
     *
     * @param task
     */
    public void runTask(Runnable task) {
        //TODO 未能够启动的任务  应该存放下来。
        if (!hasStarted){
            log.error("thread Pool has not started. abort the task.");
        }
        try {
            ((ExecutorService) threadPoolExecutor).execute(task);
        } catch (Exception e) {
            log.error("Run TASK occurred error,DATE is {}", DateUtil.getCurrentTime());
            e.printStackTrace();
        }
    }

}
