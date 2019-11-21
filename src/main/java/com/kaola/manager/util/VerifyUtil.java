package com.kaola.manager.util;

import com.kaola.manager.memory.SharableMemory;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class VerifyUtil {
    /**
     * 验证管理员是否有权限
     *
     * @param token 口令
     * @return true or f
     */
    public static boolean haveRight(String token) {
        if (token != null) {
            Long expire = SharableMemory.TOKENS_N_EXPIRE.get(token);
            if (expire == null) {
                return false;
            } else {
                //删除过期口令
                if (TokenUtil.isTimeout(expire)){
                    //惰性删除，清理空间
                    SharableMemory.TOKENS_N_EXPIRE.remove(token);
                }
                return !TokenUtil.isTimeout(expire);
            }
        }
        return false;
    }
}
