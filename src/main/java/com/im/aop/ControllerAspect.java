package com.im.aop;

import com.im.common.enums.RedisBaseEnum;
import com.im.common.utils.RedisUtil;
import com.im.entity.ImLog;
import com.im.entity.ImUser;
import com.im.service.ImLogService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @data 2019/8/16
 * @Author: LiuBin
 * @Modified By:
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {
    @Autowired
    private ImLogService imLogService;
    @Resource
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Pointcut("execution(public * com.im.controller.Im*.*(..))")
    public void aspect() {
    }

    @After("aspect()")
    public void doAfter(JoinPoint joinPoint) {
        ImLog imLog = new ImLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        imLog.setUrl(request.getRequestURI());
        imLog.setIp(ip);
        imLog.setMethod(request.getMethod());
        String tool = request.getRequestURI();
        if (tool.indexOf("query") != -1 || tool.indexOf("list") != -1) {
            imLog.setTool("查询");
        } else if (tool.indexOf("add") != -1) {
            imLog.setTool("添加");
        } else if (tool.indexOf("del") != -1) {
            imLog.setTool("删除");
        } else if (tool.indexOf("edit") != -1) {
            imLog.setTool("修改");
        } else if (tool.indexOf("login") != -1) {
            imLog.setTool("登录");
        } else if (tool.indexOf("register") != -1) {
            imLog.setTool("注册");
        } else if (tool.indexOf("token") != -1) {
            imLog.setTool("token验证登录");
        }
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        Version browserVersion = userAgent.getBrowserVersion();
        imLog.setBrowser(browser.getName().split(" ")[0] + " " + browserVersion.getVersion());
        imLog.setSystem(operatingSystem.getName());
        //获取用户信息
        String token = null;
        token = response.getHeader("token");
        if (token == null) {
            token = httpServletRequest.getHeader("token");
        }
        if (token != null) {
            ImUser user = RedisUtil.get(RedisBaseEnum.TOKEN_BASE.getBase(), token);
            imLog.setAccount(user.getAccount());
        }
        imLogService.insert(imLog);

    }

}
