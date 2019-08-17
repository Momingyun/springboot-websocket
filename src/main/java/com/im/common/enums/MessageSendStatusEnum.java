package com.im.common.enums;

/**
 * @Description 消息发送状态枚举类
 * @data 2019/8/13
 * @Author: LiuBin
 * @Modified By:
 */
public enum MessageSendStatusEnum {
    /**
     * 未发送
     */
    UNSENT(0, "未发送"),
    /**
     * 已发送
     */
    SENT(1, "已发送"),
    /**
     * 发送失败
     */
    SENT_FAIL(404, "发送失败");

    private Integer code;
    private String msg;

    MessageSendStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
