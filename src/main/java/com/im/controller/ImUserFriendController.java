package com.im.controller;

import com.im.common.enums.LineStatusEnum;
import com.im.common.properties.WebSocketProperties;
import com.im.common.utils.ResultUtil;
import com.im.entity.ImUserFriend;
import com.im.service.ImUserFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 好友列表控制器
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/friend")
@Api(tags = {"好友列表控制器"}, value = "UserFriendController")
public class ImUserFriendController {
    @Resource
    private ImUserFriendService imUserFriendService;

    /**
     * 获取好友列表
     *
     * @param id
     * @return
     */
    @ApiOperation("获取好友列表")
    @GetMapping("/list")
    public ResultUtil getFirentList(long id) {
        List<ImUserFriend> friendList = this.imUserFriendService.getFriendList(id);
        for (ImUserFriend friend : friendList) {
            //获取用户在线状态
            if (WebSocketProperties.statusMap.get(friend.getFriendId()) == null) {
                friend.getImUser().setLineStatus(LineStatusEnum.OFFLINE.getCode());
            } else {
                friend.getImUser().setLineStatus(WebSocketProperties.statusMap.get(friend.getFriendId()).getCode());
            }
        }
        if (friendList.size() == 0) {
            return ResultUtil.success(null, 0, "还没有好友");
        }
        return ResultUtil.success(friendList, friendList.size(), "获取成功");
    }

    /**
     * 申请添加好友
     *
     * @param imUserFriend
     * @return
     */
    @ApiOperation("申请添加好友")
    @PostMapping("/add")
    public ResultUtil addFriend(ImUserFriend imUserFriend) {
        int add = this.imUserFriendService.addFriend(imUserFriend);
        if (add == -1) {
            return ResultUtil.failed("好友已存在");
        }
        return ResultUtil.success(null, 1, "申请消息已发送");
    }

    /**
     * 删除好友
     *
     * @param imUserFriend
     * @return
     */
    @ApiOperation("删除好友")
    @PostMapping("/del")
    public ResultUtil deleteFriend(ImUserFriend imUserFriend) {
        int del = this.imUserFriendService.deleteFriend(imUserFriend);
        if (del > 0) {
            return ResultUtil.success(null, del, "删除成功");
        }
        return ResultUtil.failed("删除失败");
    }

    /**
     * 同意添加好友申请
     *
     * @param userId    本人编号
     * @param addUserId 申请人编号
     * @return
     */
    @ApiOperation("同意添加好友申请")
    @PostMapping("/edit/agree")
    public ResultUtil agreeAddFriend(Long userId, Long addUserId) {
        ImUserFriend imUserFriend = new ImUserFriend();
        imUserFriend.setStatus(1);
        imUserFriend.setFriendId(addUserId);
        imUserFriend.setUserId(userId);
        int edit = this.imUserFriendService.update(imUserFriend);
        imUserFriend.setUserId(addUserId);
        imUserFriend.setFriendId(userId);
        this.imUserFriendService.addFriend(imUserFriend);
        if (edit > 0) {
            return ResultUtil.success(null, edit, "成功");
        }
        return ResultUtil.failed("失败");
    }
}
