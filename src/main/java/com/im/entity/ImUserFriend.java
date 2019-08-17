package com.im.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户好友表(ImUserFriend)实体类
 *
 * @author liubin
 * @since 2019-08-14 09:49:36
 */
@Getter
@Setter
public class ImUserFriend implements Serializable {
    private static final long serialVersionUID = 606409979581010552L;
    @ApiParam("用户编号")
    private Long userId;
    @ApiParam("好友编号")
    private Long friendId;
    @ApiParam("好友备注")
    private String friendRemark;
    @ApiParam("状态 0-->未同意 1-->已同意")
    private Integer status;
    @ApiParam("添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @ApiParam("最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;
    @ApiParam("用户信息")
    private ImUser imUser;
}