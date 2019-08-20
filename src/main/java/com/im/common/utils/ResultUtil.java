package com.im.common.utils;

import com.im.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @Description 公共返回参数类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
public class ResultUtil implements Serializable {
    private Integer code;
    private Object data;
    private Integer count;
    private String msg;

    public  ResultUtil(Integer code, Object data, Integer count, String msg) {
        this.code = code;
        this.data = data;
        this.count = count;
        this.msg = msg;
    }

    /**
     * 请求成功
     *
     * @param data
     * @param count
     * @param msg
     * @return
     */
    public static ResultUtil success(Object data, Integer count, String msg) {
        return new ResultUtil(ResultCode.SUCCESS.getCode(), data, count, msg);
    }

    /**
     * 请求失败
     *
     * @param msg
     * @return
     */
    public static ResultUtil failed(String msg) {
        return new ResultUtil(ResultCode.FAILED.getCode(), null, 0, msg);
    }

    /**
     * 登录失效
     *
     * @param msg
     * @return
     */
    public static ResultUtil login_fail(String msg) {
        return new ResultUtil(404, null, 0, msg);
    }

    /**
     * 请求错误
     *
     * @param msg
     * @return
     */
    public static ResultUtil erro(String msg) {
        return new ResultUtil(ResultCode.ERRO.getCode(), null, 0, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
