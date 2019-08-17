package com.im.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.im.entity.ImUser;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Description 返回/接收消息实体类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Setter
@Getter
public class MessageVO {
    @ApiParam("消息编号")
    private Long id;
    @ApiParam("消息发送者")
    private ImUser fromUser;
    @ApiParam("消息发送者编号")
    private Long fromUserId;
    @ApiParam("消息接收者")
    private ImUser toUser;
    @ApiParam("消息接收者编号")
    private Long toUserId;
    @ApiParam("群发分组用户")
    private List<ImUser> toGroud;
    @ApiParam("群id")
    private Long toGroupId;
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
