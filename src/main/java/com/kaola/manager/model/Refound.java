package com.kaola.manager.model;

import lombok.Data;

/**
 * @author linxu
 * @date 2019/12/1
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 退款
 */
@Data
public class Refound {
    private Integer reId;
    private Integer userId;
    private String tel;
    private String name;
    private String date;
    private Double money;
    private Integer orderId;
    private Integer preservationId;
    private String status;
    //退款类型
    private String reType;

    public enum Status {
        /**
         * 拒绝
         */
        REJECTED("拒绝"),
        ACCEPTED("同意"),
        NEVER_PROCESS("未处理");
        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public String accept() {
            return ACCEPTED.status;
        }

        public String noneProcess() {
            return NEVER_PROCESS.status;
        }

        public String reject() {
            return REJECTED.status;
        }
    }
}
