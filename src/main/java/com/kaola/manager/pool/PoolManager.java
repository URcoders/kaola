package com.kaola.manager.pool;

import com.kaola.manager.support.OldMetrics;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author linxu
 * @date 2019/8/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 1、this is the pool manager which can
 * collect the pool with the metrics past some time.
 * 2、can auto flush the metrics.
 * 3、
 */
@Slf4j
public class PoolManager {
    /**
     * pool with metrics
     */
    private final static Map<ThreadPoolExecutor, OldMetrics> poolWithMetrics = new ConcurrentHashMap<>();
    /**
     * control the scheduled tasks.
     */
    private static boolean scheduledStarted = false;

    /**
     * remove pool
     *
     * @param pool pool
     */
    public static void removePool(ThreadPoolExecutor pool) {
        log.info("manager flushing Metrics now !");
        poolWithMetrics.remove(pool);
    }

    /**
     * flush metrics
     *
     * @param pool    pl
     * @param metrics metrics
     */
    private static void flushMetrics(ThreadPoolExecutor pool, OldMetrics metrics) {
        log.info("manager flushing Metrics now !");
        poolWithMetrics.put(pool, metrics);
    }

    public static void initMetrics(ThreadPoolExecutor pool, OldMetrics metrics) {
        poolWithMetrics.put(pool, metrics);
    }

    /**
     * get
     */
    public static Map<ThreadPoolExecutor, OldMetrics> getPoolWithMetrics() {
        return poolWithMetrics;
    }

    /**
     * this method should be call one time.
     * avoid multipart threads call this at the some time.
     */
    public static synchronized void scheduledCollect(int delay, int windowTime, TimeUnit unit) {
        if (!(windowTime > 0)||unit==null) {
            throw new IllegalArgumentException("args error.");
        }
        if (!scheduledStarted) {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                for (Map.Entry<ThreadPoolExecutor, OldMetrics> e : poolWithMetrics.entrySet()) {
                    //GET LAST COUNT
                    long lastCompletedCount = e.getValue().getTaskCompleted();
                    OldMetrics newMetrics = new OldMetrics();
                    newMetrics.setStartUpTime(e.getValue().getStartUpTime());
                    newMetrics.setActiveThreads(e.getKey().getActiveCount());
                    //SET RECENTLY TASK COMPLETED
                    newMetrics.setLatestCompleted(e.getKey().getCompletedTaskCount() - lastCompletedCount);
                    //NEW GENERATION
                    newMetrics.setTaskCompleted(e.getKey().getCompletedTaskCount());
                    newMetrics.setRunningTime((System.currentTimeMillis() - newMetrics.getStartUpTime()) / 1000);
                    newMetrics.setCoreSize(e.getKey().getCorePoolSize());
                    newMetrics.setLargestPoolSize(e.getKey().getLargestPoolSize());
                    newMetrics.setMaxSize(e.getKey().getMaximumPoolSize());
                    newMetrics.setEverFull(e.getKey().getLargestPoolSize() == e.getKey().getMaximumPoolSize());
                    flushMetrics(e.getKey(), newMetrics);
                    System.out.println(newMetrics);
                }
            }, delay, windowTime, unit);

            scheduledStarted = true;
        }
    }
}
