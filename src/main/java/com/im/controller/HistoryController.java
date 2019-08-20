package com.im.controller;

import com.im.common.properties.WebSocketProperties;
import com.im.common.utils.ResultUtil;
import com.im.entity.ImUserFriend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 历史联系人控制器
 * @data 2019/8/20
 * @Author: LiuBin
 * @Modified By:
 */
@Api(tags = {"历史联系人控制器"}, value = "HistoryController")
@RestController
@RequestMapping("/history")
public class HistoryController {

    /**
     * 添加聊天历史记录
     *
     * @param imUserFriend
     * @param userId
     */
    @ApiOperation("添加聊天历史记录")
    @PostMapping("/add")
    public ResultUtil addHistory(ImUserFriend imUserFriend, Long userId) {
        if (WebSocketProperties.historyCahtMap.containsKey(userId)) {
            WebSocketProperties.historyCahtMap.get(userId).add(imUserFriend);
        } else {
            List<ImUserFriend> imUserFriendList = new ArrayList<>();
            imUserFriendList.add(imUserFriend);
            WebSocketProperties.historyCahtMap.put(userId, imUserFriendList);
        }
        return ResultUtil.success(null, 0, "成功");
    }

    /**
     * 删除聊天记录
     *
     * @param friendId
     * @param userId
     * @return
     */
    @ApiOperation("删除聊天记录")
    @PostMapping("/remove")
    public ResultUtil deleteHistory(Long friendId, Long userId) {
        List<ImUserFriend> imUserFriendList = WebSocketProperties.historyCahtMap.get(userId);
        for (ImUserFriend friend : imUserFriendList) {
            if (friend.getFriendId().equals(friendId)) {
                WebSocketProperties.historyCahtMap.get(userId).remove(friend);
                break;
            }
        }
        return ResultUtil.success(null, 0, "成功");
    }

    /**
     * 获取聊天记录
     *
     * @param userId
     * @return
     */
    @ApiOperation("获取聊天记录")
    @GetMapping("/list")
    public ResultUtil getHistory(Long userId) {
        List<ImUserFriend> imUserFriendList = WebSocketProperties.historyCahtMap.get(userId);
        return ResultUtil.success(imUserFriendList, imUserFriendList.size(), "成功");
    }

}
