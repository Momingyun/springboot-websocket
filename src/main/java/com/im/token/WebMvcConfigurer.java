package com.im.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description 拦截器配置类
 * @data 2019/8/16
 * @Author: LiuBin
 * @Modified By:
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * Description:重写增加自定义拦截器的注册，某一个拦截器需要先注册进来，才能工作
     * return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/user/query")
                .addPathPatterns("/user/login/token")
                .addPathPatterns("/imMessage/**")
                .addPathPatterns("/friend/**");
        super.addInterceptors(registry);
    }
}