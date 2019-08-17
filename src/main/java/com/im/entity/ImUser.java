package com.im.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(ImUser)实体类
 *
 * @author liubin
 * @since 2019-08-14 09:49:36
 */
@Getter
@Setter
public class ImUser implements Serializable {
    private static final long serialVersionUID = 644436460314402598L;
    @ApiParam("用户编号")
    private Long id;
    @ApiParam("用户名")
    private String account;
    @ApiParam("密码")
    private String password;
    @ApiParam("头像")
    private String image;
    @ApiParam("性别")
    private String sex;
    @ApiParam("年龄")
    private Integer age;
    @ApiParam("个性签名")
    private String signature;
    @ApiParam("最后一次登录ip")
    private String loginIp;
    @ApiParam("用户状态 0-->冻结  1-->正常")
    private Integer status;
    @ApiParam("用户在线状态 0-->离线  1-->在线 2-->离开 3-->繁忙")
    private Integer lineStatus;
    @ApiParam("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @ApiParam("最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;

    @ApiParam("token")
    private String token;

}