package com.im.entity;

import java.util.Date;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;

/**
 * 日志表(ImLog)实体类
 *
 * @author liubin
 * @since 2019-08-16 18:25:07
 */
public class ImLog implements Serializable {
    private static final long serialVersionUID = -94407996274452393L;
    @ApiParam("主键编号")
    private Long id;
    @ApiParam("请求地址")
    private String url;
    @ApiParam("请求方法")
    private String method;
    @ApiParam("请求ip")
    private String ip;
    @ApiParam("操作用户名")
    private String account;
    @ApiParam("操作名称")
    private String tool;
    @ApiParam("浏览器")
    private String browser;
    @ApiParam("操作系统")
    private String system;
    @ApiParam("创建时间")
    private Date created;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }
    
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}