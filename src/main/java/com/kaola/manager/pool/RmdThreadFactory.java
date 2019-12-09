package com.kaola.manager.pool;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linxu
 * @date 2019/8/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class RmdThreadFactory implements ThreadFactory {
    /**
     * int type can support the thread numbers.
     */
    private AtomicInteger threadNo = new AtomicInteger(1);
    private final String nameStart;
    private final boolean isDaemon;

    /**
     * @param poolName poolName
     * @param isDaemon is daemon thread factory
     *                 produce the thread Name is [poolName-id]
     */
    public RmdThreadFactory(String poolName, boolean isDaemon) {
        this.isDaemon = isDaemon;
        this.nameStart = "[" + poolName + "-";
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
        //nameEnd set local can save a little memory.
        String nameEnd = "]";
        String threadName = this.nameStart + this.threadNo.getAndIncrement() + nameEnd;
        Thread newThread = new Thread(r, threadName);
        //keep Priority
        if (newThread.getPriority() != 5) {
            newThread.setPriority(5);
        }
        //set  daemon
        newThread.setDaemon(isDaemon);
        return newThread;
    }
}
