package com.kaola.manager.constances;

/**
 * @author linxu
 * @date 2019/11/18
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public enum Message {
    LOGIN_OK("登录成功"),
    LOGIN_FAIL("登录失败"),
    EXIT_OK("退出登录成功"),
    EXIT_FAIL("退出登录失败"),
    SERVER_ERROR("服务器出现异常"),
    DATA_FORMAT_ERROR("数据格式异常"),
    HAVE_NO_RIGHT("没有操作权限"),
    OP_OK("操作成功");
    private String content;

    public String getContent() {
        return content;
    }

    Message(String content) {
        this.content = content;
    }
}
