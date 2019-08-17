package com.im.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.im.common.utils.ResultUtil;
import com.im.entity.ImLog;
import com.im.service.ImLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 日志表(ImLog)表控制层
 *
 * @author liubin
 * @since 2019-08-16 15:02:33
 */
@Controller
@RequestMapping("/imLog")
public class ImLogController {
    /**
     * 服务对象
     */
    @Resource
    private ImLogService imLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return
     */
    @ApiOperation(value = "通过ID查询单条数据")
    @GetMapping("/query")
    @ResponseBody
    public ResultUtil info(Long id) {
        ImLog imLog = this.imLogService.queryById(id);
        return ResultUtil.success(imLog, 1, "成功");
    }

    /**
     * 分页获取数据
     *
     * @param imLog 实例对象
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页获取数据")
    @GetMapping("/list")
    @ResponseBody
    public ResultUtil list(ImLog imLog, Integer page, Integer limit) {
        if (page == 0 || page == null) {
            page = 1;
        }
        if (limit == 0 || limit == null) {
            limit = 10;
        }
        PageHelper.startPage(page, limit);
        Page<ImLog> imLogPage = this.imLogService.queryAll(imLog);
        return ResultUtil.success(imLogPage.getResult(), (int) imLogPage.getTotal(), "成功");
    }

    /**
     * 添加数据
     *
     * @param imLog 实例对象
     * @return
     */
    @ApiOperation(value = "添加数据")
    @PostMapping("/add")
    @ResponseBody
    public ResultUtil add(ImLog imLog) {
        int insert = this.imLogService.insert(imLog);
        if (insert > 0) {
            return ResultUtil.success(null, insert, "成功");
        }
        return ResultUtil.failed("添加失败");
    }

    /**
     * 修改数据
     *
     * @param imLog 实例对象
     * @return
     */
    @ApiOperation(value = "修改数据")
    @PostMapping("/edit")
    @ResponseBody
    public ResultUtil edit(ImLog imLog) {
        int update = this.imLogService.update(imLog);
        if (update > 0) {
            return ResultUtil.success(null, 1, "修改成功");
        }
        return ResultUtil.failed("修改失败");
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return
     */
    @ApiOperation(value = "删除数据")
    @GetMapping("/remove")
    @ResponseBody
    public ResultUtil remove(Long id) {
        boolean b = this.imLogService.deleteById(id);
        if (b) {
            return ResultUtil.success(null, 1, "删除成功");
        }
        return ResultUtil.failed("删除失败");
    }
}