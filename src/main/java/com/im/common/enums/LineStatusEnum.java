package com.im.common.enums;

/**
 * @Description 用户在线状态枚举类
 * @data 2019/8/13
 * @Author: LiuBin
 * @Modified By:
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum LineStatusEnum {
    /**
     * 离线
     */
    OFFLINE(0, "离线"),
    /**
     * 在线
     */
    ONLINE(1, "在线"),
    /**
     * 离开
     */
    LEAVE(2, "离开"),
    /**
     * 繁忙
     */
    BUSY(3, "繁忙");

    private Integer code;
    private String msg;

    private LineStatusEnum(Integer code, String msg) {
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
