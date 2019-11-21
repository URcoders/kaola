package com.kaola.manager.util;


import java.util.UUID;

/**
 * @author linxu
 * @date 2019/11/13
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class TokenUtil {
    /**
     * 20 minutes
     */
    public static final long TIMEOUT = 1200000;

    /**
     * 使用身份证号码加UUID生成一个唯一的TOKEN
     * 使用Bloom校验全局的唯一性。
     *
     * @param idCard id card
     * @return digested id card
     */
    public static String createToken(String idCard) {
        if (idCard == null || idCard.length() == 0) {
            throw new IllegalArgumentException();
        }
        String digested;
        digested = DigestUtil.digest(idCard + UUID.randomUUID(), 5);
        return digested;
    }

    /**
     * 口令是否超时
     *
     * @param time 口令生成时间
     * @return 口令是否过期
     */
    public static boolean isTimeout(long time) {
        return time  < System.currentTimeMillis();
    }



}
