package com.kaola.manager.constances;

/**
 * @author linxu
 * @date 2019/11/19
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public enum StoreStatus {
    /**
     * 营业
     */
    RUNNING(1),
    /**
     * 停业
     */
    STOP(0);
    private int status;

    public int getStatus() {
        return status;
    }

    StoreStatus(int status) {
        this.status = status;
    }
}
