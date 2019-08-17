package com.im.controller;

import com.im.common.enums.RedisBaseEnum;
import com.im.common.utils.RedisUtil;
import com.im.common.utils.ResultUtil;
import com.im.entity.ImUser;
import com.im.service.ImUserService;
import com.im.token.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户控制器
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = {"用户控制器"}, value = "UserController")
public class ImUserController {
    @Resource
    private ImUserService imUserService;

    /**
     * token验证登录
     *
     * @param request
     * @return
     */
    @ApiOperation("token验证登录")
    @PostMapping("/login/token")
    public ResultUtil tokenLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        ImUser imUser = RedisUtil.get(RedisBaseEnum.TOKEN_BASE.getBase(), token);
        return ResultUtil.success(imUser, 1, "token登录成功");
    }

    /**
     * 用户登录
     *
     * @param account
     * @param password
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResultUtil login(HttpServletResponse response, String account, String password) {
        ImUser imUser = this.imUserService.login(account, password);
        if (imUser == null) {
            return ResultUtil.failed("账号密码不匹配");
        }
        try {
            String token = TokenUtils.getToken(imUser.getId().toString());
            imUser.setToken(token);
            response.setHeader("token", token);
            RedisUtil.set(RedisBaseEnum.TOKEN_BASE.getBase(), token, imUser, 2*60*60);
            ImUser user = RedisUtil.get(RedisBaseEnum.TOKEN_BASE.getBase(), token);
        } catch (Exception e) {
            log.error("token获取失败");
        }
        RedisUtil.set(0, imUser.getId().toString(), imUser);
        return ResultUtil.success(imUser, 1, "登录成功");
    }

    /**
     * 用户注册
     *
     * @param imUser
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResultUtil register(ImUser imUser) {
        int insert = this.imUserService.insert(imUser);
        if (insert == 1) {
            return ResultUtil.success(null, 1, "注册成功");
        }
        if (insert == -1) {
            return ResultUtil.failed("用户名已被占用");
        }
        return ResultUtil.failed("注册失败");
    }

    /**
     * 根据用户编号获取用户信息
     *
     * @param id 用户编号
     * @return
     */
    @ApiOperation("根据用户编号获取用户信息")
    @GetMapping("/query")
    public ResultUtil getUserInfoById(Long id) {
        ImUser imUser = this.imUserService.queryById(id);
        if (imUser != null) {
            return ResultUtil.success(imUser, 1, "获取成功");
        }
        return ResultUtil.failed("用户不存在");
    }

}
