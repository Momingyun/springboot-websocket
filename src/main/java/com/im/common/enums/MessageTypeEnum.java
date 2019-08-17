package com.im.common.enums;

/**
 * @Description 消息类型枚举类
 * @data 2019/8/13
 * @Author: LiuBin
 * @Modified By:
 */
public enum MessageTypeEnum {
    /**
     * 单聊
     */
    TOPIC("topic", "单聊"),
    /**
     * 群聊
     */
    GROUP("group", "群聊"),
    /**
     * 添加好友
     */
    FRIEND("friend", "添加好友"),
    /**
     * 申请入群
     */
    ENTER_GROUP("enter_group", "申请入群");
    private String type;
    private String msg;

    MessageTypeEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
