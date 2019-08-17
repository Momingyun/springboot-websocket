package com.im.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 群聊信息表(ImGroup)实体类
 *
 * @author liubin
 * @since 2019-08-14 09:49:34
 */
@Getter
@Setter
public class ImGroup implements Serializable {
    private static final long serialVersionUID = -48658543792295017L;
    @ApiParam("群编号")
    private Long id;
    @ApiParam("群图片")
    private String groupImg;
    @ApiParam("群名称")
    private String groupName;
    @ApiParam("群聊最大人数")
    private Integer groupMaxNum;
    @ApiParam("群公告")
    private String groupNotice;
    @ApiParam("群状态 0-->冻结  1-->正常")
    private Integer status;
    @ApiParam("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @ApiParam("最后一次修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;

}