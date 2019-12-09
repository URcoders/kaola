package com.kaola.manager.support;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/8/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Data
public class OldMetrics {
    /**
     * shutdown time
     */
    private long shutDownTime;
    /**
     * start up time
     */
    private long startUpTime;
    /**
     * poolRunning time
     */
    private long runningTime;
    /**
     * reckon up the numbers of task completed & done by pool thread
     */
    private long taskCompleted;
    /**
     * 上一次完成
     */
    private long latestCompleted;

    /**
     * reckon up the numbers of task rejected
     */
    //private long taskRejected;
    /**
     * active threads
     */
    private int activeThreads;
    /**
     * pool is everFull or not
     */
    private boolean everFull;
    /**
     * largestPoolSize
     */
    private int largestPoolSize;
    /**
     * coreSize
     */
    private int coreSize;
    /**
     * maxSize
     */
    private int maxSize;

}
