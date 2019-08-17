package com.im.common.enums;

/**
 * @Description reids存储空间枚举类
 * @data 2019/8/17
 * @Author: LiuBin
 * @Modified By:
 */
public enum RedisBaseEnum {
    /**
     * socket存储空间
     */
    SOCKET_BASE(0),
    /**
     * token存储空间
     */
    TOKEN_BASE(1);
    private Integer base;

    RedisBaseEnum(Integer base) {
        this.base = base;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }
}
