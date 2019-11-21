package com.kaola.manager.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 这个系统中，不采用redis充当内存区；
 * 也不采用session来验证，而是采用共享区Token.
 */
public class SharableMemory {
    /**
     * 口令与过期时间的映射
     */
    public static final Map<String, Long> TOKENS_N_EXPIRE = new ConcurrentHashMap<>();
    //TODO 共享内存区的口令采用lazy移除机制加定时清理：调用的时候判断是否过期；每天半夜3点定时清理共享区。
}
