package com.kaola.manager.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author linxu
 * @date 2019/8/10
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class RmdRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //unsupported now.
        throw new UnsupportedOperationException("unsupported!");
    }
}
