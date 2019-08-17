package com.im.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.im.common.enums.LineStatusEnum;
import com.im.common.properties.WebSocketProperties;
import com.im.controller.WebSocketController;
import com.im.entity.ImMessage;
import com.im.entity.ImUserFriend;
import com.im.entity.ImUserGroup;
import com.im.entity.vo.MessageVO;
import com.im.service.ImMessageService;
import com.im.service.ImUserFriendService;
import com.im.service.ImUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Description 消息发送实体类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
public class SendMsgUtil {
    private static ImMessageService imMessageService;

    @Autowired
    public void setImMessageService(ImMessageService imMessageService) {
        SendMsgUtil.imMessageService = imMessageService;
    }

    private static ImUserFriendService imUserFriendService;

    @Autowired
    public void setImUserFriendService(ImUserFriendService imUserFriendService) {
        SendMsgUtil.imUserFriendService = imUserFriendService;
    }

    private static ImUserGroupService imUserGroupService;

    @Autowired
    public void setImUserGroupService(ImUserGroupService imUserGroupService) {
        SendMsgUtil.imUserGroupService = imUserGroupService;
    }

    /**
     * 单聊消息发送
     *
     * @param messageVO
     */
    public static void topicSendMsg(MessageVO messageVO) {
        //申明一个message变量  用于存放数据库
        ImMessage imMessage = new ImMessage();
        imMessage.setFromUserId(messageVO.getFromUserId());
        imMessage.setToUserId(messageVO.getToUserId());
        imMessage.setMsgType(messageVO.getMsgType());
        if (!messageVO.getMsgFile().isEmpty()) {
            imMessage.setMsgFile(messageVO.getMsgFile());
        }
        if (!messageVO.getMsgImage().isEmpty()) {
            imMessage.setMsgImage(messageVO.getMsgImage());
        }
        if (!messageVO.getMsgText().isEmpty()) {
            imMessage.setMsgText(messageVO.getMsgText());
        }
        //消息发送状态默认设为未发送 0-->未发送  1-->已发送 404-->发送失败
        imMessage.setStatus(0);
        imMessage.setCreated(messageVO.getCreated());
        //获取发送者编号
        Long fromUserId = messageVO.getFromUserId();
        //获取接收者编号
        Long toUserId = messageVO.getToUserId();
        //获取消息接收者在线状态
        if (WebSocketProperties.statusMap.containsKey(toUserId)) {
            //获取在线状态
            LineStatusEnum lineStatus = WebSocketProperties.statusMap.get(toUserId);
            //获取消息接收者sessionId
            String sessionId = MessageConvertUtil.getSessionIdFromMap(toUserId, lineStatus.getCode());
            String jsonString = JSONObject.toJSONString(messageVO);
            WebSocketController webSocketController = null;
            if (lineStatus.getCode() == 1) {
                //在线
                webSocketController = WebSocketProperties.onLineMap.get(sessionId).get(toUserId);
            } else if (lineStatus.getCode() == 2) {
                //离开
                webSocketController = WebSocketProperties.leaveMap.get(sessionId).get(toUserId);
            } else if (lineStatus.getCode() == 3) {
                //繁忙
                webSocketController = WebSocketProperties.busyMap.get(sessionId).get(toUserId);
            }
            //发送消息
            webSocketController.getSession().getAsyncRemote().sendText(jsonString);
            //消息发送状态设为已发送
            imMessage.setStatus(1);
            imMessage.setUpdated(new Date());
        }
        //消息存放数据库
        imMessageService.insert(imMessage);
    }

    /**
     * 群聊消息发送
     *
     * @param messageVO
     */
    public static void groupSendMsg(MessageVO messageVO) {
        //获取群编号
        Long toGroupId = messageVO.getToGroupId();
        //获取该群所有成员
        List<ImUserGroup> imUserGroups = imUserGroupService.queryById(toGroupId);
        //给群里每个人发消息
        for (ImUserGroup userGroup : imUserGroups) {
            messageVO.setToUserId(userGroup.getUserId());
            SendMsgUtil.topicSendMsg(messageVO);
        }
    }

    /**
     * 添加好友消息发送
     *
     * @param messageVO
     */
    public static void addFriendSendMsg(MessageVO messageVO) {
        //发送消息
        SendMsgUtil.topicSendMsg(messageVO);
        ImUserFriend imUserFriend = new ImUserFriend();
        imUserFriend.setUserId(messageVO.getFromUserId());
        imUserFriend.setFriendId(messageVO.getToUserId());
        if (!messageVO.getMsgText().isEmpty()) {
            imUserFriend.setFriendRemark(messageVO.getMsgText());
        }
        //默认状态设为未同意
        imUserFriend.setStatus(0);
        imUserFriendService.addFriend(imUserFriend);
    }
}
