package com.im.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户群组关联表(ImUserGroup)实体类
 *
 * @author liubin
 * @since 2019-08-14 09:49:36
 */
@Getter
@Setter
public class ImUserGroup implements Serializable {
    private static final long serialVersionUID = 699683296610851616L;
    @ApiParam("群编号")
    private Long groupId;
    @ApiParam("用户编号")
    private Long userId;
    @ApiParam("群图片")
    private String image;
    @ApiParam("群名称")
    private String name;
    @ApiParam("用户身份认证  0-->群员 1-->群主  2-->管理员")
    private Integer authUser;
    @ApiParam("入群时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @ApiParam("最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;
}