package com.im.controller;

import com.im.common.utils.ResultUtil;
import com.im.entity.ImUserGroup;
import com.im.service.ImUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 用户群组控制器
 * @data 2019/8/19
 * @Author: LiuBin
 * @Modified By:
 */
@Api(tags = {"用户群组控制器"}, value = "ImUserGroupController")
@RestController
@RequestMapping("/group")
public class ImUserGroupController {
    @Autowired
    private ImUserGroupService imUserGroupService;

    /**
     * 获取群组列表
     *
     * @param id 用户编号
     * @return
     */
    @ApiOperation("获取群组列表")
    @GetMapping("list")
    public ResultUtil list(Long id) {
        List<ImUserGroup> imUserGroups = this.imUserGroupService.getGroups(id);
        return ResultUtil.success(imUserGroups, imUserGroups.size(), "成功");
    }
}
