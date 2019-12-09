package com.kaola.manager.pool;


import com.kaola.manager.support.OldMetrics;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author linxu
 * @date 2019/8/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
@Slf4j
public class RmdThreadPool extends ThreadPoolExecutor {
    private final long startUpTime;
    private final String poolName;

    /**
     * @param poolName        poolName
     * @param corePoolSize    coreSize
     * @param maximumPoolSize maxSize
     * @param keepAliveTime   alive time of thread which are out of pool
     * @param unit            unit
     * @param workQueue       blocking queue
     */
    public RmdThreadPool(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        //TODO dateUtil
        this.startUpTime = System.currentTimeMillis();
        this.poolName = poolName;
        OldMetrics metrics = new OldMetrics();
        metrics.setStartUpTime(startUpTime);
        PoolManager.initMetrics(this, metrics);
        printTips(workQueue);
    }

    public RmdThreadPool(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        //TODO dateUtil
        this.startUpTime = System.currentTimeMillis();
        this.poolName = poolName;
        OldMetrics metrics = new OldMetrics();
        metrics.setStartUpTime(startUpTime);
        PoolManager.initMetrics(this, metrics);
        printTips(workQueue);
    }

    public RmdThreadPool(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.startUpTime = System.currentTimeMillis();
        this.poolName = poolName;
        OldMetrics metrics = new OldMetrics();
        metrics.setStartUpTime(startUpTime);
        PoolManager.initMetrics(this, metrics);
        printTips(workQueue);
    }

    public RmdThreadPool(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        this.startUpTime = System.currentTimeMillis();
        this.poolName = poolName;
        OldMetrics metrics = new OldMetrics();
        metrics.setStartUpTime(startUpTime);
        PoolManager.initMetrics(this, metrics);
        printTips(workQueue);
    }

    /**
     * before shutdown.
     */
    @Override
    protected void terminated() {
        //remove pool
        PoolManager.removePool(this);
    }

    /**
     * print tips.
     *
     * @param workQueue queue
     */
    private void printTips(BlockingQueue<Runnable> workQueue) {
        if (workQueue instanceof LinkedBlockingQueue) {
            log.warn("LinkedBlockingQueue'range  is not limited ,so only will use the corePoolSize thread to work.");
        }
        if (workQueue instanceof SynchronousQueue) {
            log.warn("SynchronousQueue will occurred rejected ,so maxSize should be set bigger.");
        }
    }


}
