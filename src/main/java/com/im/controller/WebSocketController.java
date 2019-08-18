package com.im.controller;

import com.im.common.config.MessageDecoder;
import com.im.common.config.MessageEncoder;
import com.im.common.enums.LineStatusEnum;
import com.im.common.enums.MessageTypeEnum;
import com.im.common.properties.WebSocketProperties;
import com.im.common.utils.MessageConvertUtil;
import com.im.common.utils.SendMsgUtil;
import com.im.entity.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description websocket控制器
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
@Controller
@ServerEndpoint(value = "/socket/{id}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class WebSocketController {
    /**
     * 当前连接
     */
//    private Map<Long, WebSocketController> client = new HashMap<>();
    /**
     * 会话
     */
    private Session session;

    public Session getSession() {
        return session;
    }

    /**
     * 建立连接
     *
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(("id")) Long userId, Session session) {
        this.session = session;
        //将上线用户信息添加到全局map集合中
        Map<Long, WebSocketController> controllerMap = new HashMap<>();
        controllerMap.put(userId, this);
        int onlienStatus = 0;
        //判断该用户是否已经在线
        for (Map<Long, WebSocketController> map : WebSocketProperties.onLineMap.values()) {
            if (map.containsKey(userId)) {
                onlienStatus = 1;
            }
        }
        for (Map<Long, WebSocketController> map : WebSocketProperties.leaveMap.values()) {
            if (map.containsKey(userId)) {
                onlienStatus = 2;
            }
        }
        for (Map<Long, WebSocketController> map : WebSocketProperties.busyMap.values()) {
            if (map.containsKey(userId)) {
                onlienStatus = 3;
            }
        }
        //移除已登录账号
        if (onlienStatus != 0) {
            String sessionid = MessageConvertUtil.getSessionIdFromMap(userId, onlienStatus);
            if (onlienStatus == 1) {
                WebSocketProperties.onLineMap.remove(sessionid);
            }
            if (onlienStatus == 2) {
                WebSocketProperties.leaveMap.remove(sessionid);
            }
            if (onlienStatus == 3) {
                WebSocketProperties.busyMap.remove(sessionid);
            }
            WebSocketProperties.statusMap.remove(userId);
        }
        WebSocketProperties.onLineMap.put(session.getId(), controllerMap);
        WebSocketProperties.statusMap.put(userId, LineStatusEnum.ONLINE);
        log.info("用户：" + userId + "登录成功！sessionId=" + session.getId());
        log.info("当前在线人数：" + WebSocketProperties.getOnlineNumber());
        log.info("当前离开人数：" + WebSocketProperties.getLeaveNumber());
        log.info("当前繁忙人数：" + WebSocketProperties.getBusyNumber());
    }

    /**
     * 发送消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMassage(String message, Session session) {
        MessageVO messageVO = MessageConvertUtil.sendMsgToAcceptMsg(message);
        if (messageVO.getMsgType().equals(MessageTypeEnum.TOPIC.getType())) {
            //单聊
            SendMsgUtil.topicSendMsg(messageVO);
        } else if (messageVO.getMsgType().equals(MessageTypeEnum.FRIEND.getType())) {
            //添加好友
            SendMsgUtil.addFriendSendMsg(messageVO);
        }
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        Set<Long> userIds = null;
        Long userId = 0L;
        try {
            if (WebSocketProperties.onLineMap.containsKey(session.getId())) {
                Map<Long, WebSocketController> controllerMap = WebSocketProperties.onLineMap.get(session.getId());
                userIds = controllerMap.keySet();
                WebSocketProperties.onLineMap.remove(session.getId());
            } else if (WebSocketProperties.leaveMap.containsKey(session.getId())) {
                Map<Long, WebSocketController> controllerMap = WebSocketProperties.leaveMap.get(session.getId());
                userIds = controllerMap.keySet();
                WebSocketProperties.leaveMap.remove(session.getId());
            } else if (WebSocketProperties.busyMap.containsKey(session.getId())) {
                Map<Long, WebSocketController> controllerMap = WebSocketProperties.busyMap.get(session.getId());
                userIds = controllerMap.keySet();
                WebSocketProperties.busyMap.remove(session.getId());
            }
            for (Long id : userIds) {
                userId = id;
                break;
            }
            WebSocketProperties.statusMap.remove(userId);
        } catch (Exception e) {
            log.info("用户:" + userId + "手动关闭连接");
        } finally {
            log.info("用户:" + userId + "下线");
        }
        log.info("当前在线人数：" + WebSocketProperties.getOnlineNumber());
        log.info("当前离开人数：" + WebSocketProperties.getLeaveNumber());
        log.info("当前繁忙人数：" + WebSocketProperties.getBusyNumber());
    }
}
