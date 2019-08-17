package com.im.token;

import com.alibaba.fastjson.JSONObject;
import com.im.common.enums.RedisBaseEnum;
import com.im.common.utils.RedisUtil;
import com.im.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Description 拦截器
 * @data 2019/8/16
 * @Author: LiuBin
 * @Modified By:
 */
public class Interceptor implements HandlerInterceptor {
    private String token=null;
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        ResultUtil resultUtil = null;
        token = httpServletRequest.getHeader("token");
        String checkToken = null;
        //验证token是否失效
        if (token != null) {
            checkToken = TokenUtils.checkToken(token);
            if (checkToken.equals("F")) {
                resultUtil = ResultUtil.failed("非法登录");
            } else if (checkToken.equals("G")) {
                resultUtil = ResultUtil.failed("登录过期");
            } else if (checkToken.equals("S")) {
                ResultUtil.failed("登录失效");
            } else {
                return true;
            }
        }
        resultUtil = ResultUtil.failed("未登录");
        returnErrorResponse(httpServletResponse, resultUtil);
        return false;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        RedisUtil.expire(RedisBaseEnum.TOKEN_BASE.getBase(),token,2*60*60);
    }

    public void returnErrorResponse(HttpServletResponse response, ResultUtil result) throws IOException, UnsupportedEncodingException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JSONObject.toJSONString(result).getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
