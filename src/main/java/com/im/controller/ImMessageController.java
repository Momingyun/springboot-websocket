package com.im.controller;

import com.im.common.enums.MessageTypeEnum;
import com.im.common.utils.ResultUtil;
import com.im.entity.ImMessage;
import com.im.entity.ImUserGroup;
import com.im.service.ImMessageService;
import com.im.service.ImUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 历史消息控制器
 * @data 2019/8/15
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/imMessage")
@Api(tags = {"历史消息控制器"}, value = "ImMessageController")
public class ImMessageController {
    @Resource
    private ImMessageService imMessageService;
    @Resource
    private ImUserGroupService imUserGroupService;

    /**
     * 获取好友历史未读消息
     *
     * @param userId
     * @return
     */
    @ApiOperation("获取好友历史未读消息")
    @GetMapping("/friend/list/{id}")
    public ResultUtil getFriendMessage(@PathVariable("id") Long userId) {
        ImMessage imMessage = new ImMessage();
        imMessage.setFromUserId(userId);
        imMessage.setMsgType(MessageTypeEnum.TOPIC.getType());
        imMessage.setStatus(0);
        List<ImMessage> messageList = this.imMessageService.queryAll(imMessage);
        if (messageList.size() < 1) {
            return ResultUtil.failed("无历史消息");
        }
        return ResultUtil.success(messageList, messageList.size(), "获取成功");
    }

    /**
     * 获取群聊历史未读消息
     *
     * @param userId
     * @return
     */
    @ApiOperation("获取群聊历史未读消息")
    @GetMapping("/group/list/{id}")
    public ResultUtil getGroupMessage(@PathVariable("id") Long userId) {
        List<ImMessage> imMessageList = new ArrayList<>();
        //获取用户群聊
        List<ImUserGroup> imUserGroups = this.imUserGroupService.queryById(userId);
        for (ImUserGroup imUserGroup : imUserGroups) {
            ImMessage imMessage = new ImMessage();
            imMessage.setToGroudId(imUserGroup.getGroupId());
            imMessage.setMsgType(MessageTypeEnum.GROUP.getType());
            imMessage.setStatus(0);
            List<ImMessage> messageList = this.imMessageService.queryAll(imMessage);
            for (ImMessage imMessage1 : messageList) {
                imMessageList.add(imMessage1);
            }
        }
        if (imMessageList.size() < 1) {
            return ResultUtil.failed("无历史消息");
        }
        return ResultUtil.success(imMessageList, imMessageList.size(), "获取成功");
    }

    /**
     * 获取添加好友申请消息
     *
     * @param userId
     * @return
     */
    @ApiOperation("获取添加好友申请消息")
    @GetMapping("/apply/friend/list/{id}")
    public ResultUtil getAddFriendMessage(@PathVariable("id") Long userId) {
        ImMessage imMessage = new ImMessage();
        imMessage.setToUserId(userId);
        imMessage.setMsgType(MessageTypeEnum.FRIEND.getType());
        imMessage.setStatus(0);
        List<ImMessage> imMessageList = this.imMessageService.queryAll(imMessage);
        if (imMessageList.size() < 1) {
            return ResultUtil.failed("无申请消息");
        }
        return ResultUtil.success(imMessageList, imMessageList.size(), "获取成功");
    }
}
