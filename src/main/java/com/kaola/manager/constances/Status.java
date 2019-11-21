package com.kaola.manager.constances;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public enum Status {
    /**
     * ok
     */
    OK(1),
    /**
     * fail
     */
    FAIL(2),
    /**
     * server error
     */
    SERVER_ERROR(3),;
    private int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
