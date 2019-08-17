package com.im.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.im.common.enums.RedisBaseEnum;
import com.im.common.properties.WebSocketProperties;
import com.im.controller.WebSocketController;
import com.im.entity.ImUser;
import com.im.entity.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * @Description 消息转换工具类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
public class MessageConvertUtil {
    /**
     * 将前端传来的消息json串转发送给前端的消息对象
     *
     * @param msg
     * @return
     */
    public static MessageVO sendMsgToAcceptMsg(String msg) {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        MessageVO messageVO = new MessageVO();
        String msgType = jsonObject.getString("msgType");
        //消息内心
        messageVO.setMsgType(msgType);
        if (msgType.equals("topic")) {
            //单聊
            Long toUserId = jsonObject.getLong("toUserId");
            //消息接收者编号
            messageVO.setToUserId(toUserId);
        } else if (msgType.equals("group")) {
            //群聊
            Long toGroupId = jsonObject.getLong("toGroupId");
            //群编号
            messageVO.setToGroupId(toGroupId);
        }
        Long fromUserId = jsonObject.getLong("fromUserId");
        //获取消息发送者信息
        ImUser fromUser = RedisUtil.get(RedisBaseEnum.SOCKET_BASE.getBase(), fromUserId.toString());
        //消息发送者编号
        messageVO.setFromUserId(fromUserId);
        //消息发送者信息
        messageVO.setFromUser(fromUser);
        //消息默认状态为未发送
        messageVO.setStatus(0);
        //消息发送时间
        messageVO.setCreated(new Date());
        //消息内容
        if (jsonObject.containsKey("msgImage")) {
            String msgImage = jsonObject.getString("msgImage");
            messageVO.setMsgImage(msgImage);
        }
        if (jsonObject.containsKey("msgFile")) {
            String msgFile = jsonObject.getString("msgFile");
            messageVO.setMsgFile(msgFile);
        }
        if (jsonObject.containsKey("msgText")) {
            String msgText = jsonObject.getString("msgText");
            messageVO.setMsgText(msgText);
        }
        return messageVO;
    }

    /**
     * 从未下线用户集合中获取指定用户的sessionId
     *
     * @param status 用户在线状态
     * @param id     用户编号
     * @return
     */
    public static String getSessionIdFromMap(Long id, Integer status) {
        Map<String, Map<Long, WebSocketController>> map = null;
        if (status == 1) {
            map = WebSocketProperties.onLineMap;
        } else if (status == 2) {
            map = WebSocketProperties.leaveMap;
        } else if (status == 3) {
            map = WebSocketProperties.busyMap;
        }
        for (String str : map.keySet()) {
            Map<Long, WebSocketController> controllerMap = map.get(str);
            if (controllerMap.containsKey(id)) {
                return str;
            }
        }
        return null;
    }

}
