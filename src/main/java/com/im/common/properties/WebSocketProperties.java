package com.im.common.properties;

import com.im.common.enums.LineStatusEnum;
import com.im.controller.WebSocketController;
import com.im.entity.ImUserFriend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description websocket参数配置类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
public class WebSocketProperties implements Serializable {
    /**
     * 在线用户存储
     * 参数说明：String：sessionId
     * Long：用户编号
     * WebSocketController 连接对象
     */
    public static Map<String, Map<Long, WebSocketController>> onLineMap = new HashMap<>();
    /**
     * 离开用户存储  键：用户sessionId 值：用户Id
     * 参数说明：String：sessionId
     * Long：用户编号
     * WebSocketController 连接对象
     */
    public static Map<String, Map<Long, WebSocketController>> leaveMap = new HashMap<>();
    /**
     * 繁忙用户存储  键：用户sessionId 值：用户Id
     * 参数说明：String：sessionId
     * Long：用户编号
     * WebSocketController 连接对象
     */
    public static Map<String, Map<Long, WebSocketController>> busyMap = new HashMap<>();
    /**
     * 存放所有未下线用户
     * 参数说明:Long:用户编号
     * LineStatusEnum:用户在线状态
     */
    public static Map<Long, LineStatusEnum> statusMap = new HashMap<>();

    /**
     * 历史聊天记录
     * 参数说明：Long 用户编号
     */
    public static Map<Long, List<String>> historyCahtMap = new HashMap<>();
    /**
     * 在线人数
     */
    public int onlineNumber;
    /**
     * 离开人数
     */
    public int leaveNumber;
    /**
     * 繁忙人数
     */
    public int busyNumber;

    public static int getOnlineNumber() {
        return onLineMap.size();
    }

    public static int getLeaveNumber() {
        return leaveMap.size();
    }

    public static int getBusyNumber() {
        return busyMap.size();
    }
}
