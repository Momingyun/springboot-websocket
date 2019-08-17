package com.im.common.enums;

/**
 * @Description 返回参数状态枚举类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(0),
    /**
     * 失败
     */
    FAILED(-1),
    /**
     * 请求错误
     */
    ERRO(404);
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
