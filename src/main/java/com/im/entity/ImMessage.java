package com.im.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息参数表(ImMessage)实体类
 *
 * @author liubin
 * @since 2019-08-14 09:49:36
 */
@Getter
@Setter
public class ImMessage implements Serializable {
    private static final long serialVersionUID = 420006335957034747L;
    @ApiParam("消息编号")
    private Long id;
    @ApiParam("消息发送者编号")
    private Long fromUserId;
    @ApiParam("消息接收者编号")
    private Long toUserId;
    @ApiParam("群发分组编号")
    private Long toGroudId;
    @ApiParam("消息类型 topic-->单聊 group-->群聊")
    private String msgType;
    @ApiParam("图片消息")
    private String msgImage;
    @ApiParam("文件消息")
    private String msgFile;
    @ApiParam("文字消息")
    private String msgText;
    @ApiParam("消息发送状态 0-->未发送  1-->已发送 404-->发送失败")
    private Integer status;
    @ApiParam("消息创建时间 ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @ApiParam("消息发送成功时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;

}