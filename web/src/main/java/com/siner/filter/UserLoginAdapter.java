package com.siner.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@SpringBootApplication
@EnableCaching
@Configuration
public class UserLoginAdapter implements WebMvcConfigurer {
    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加对用户是否登录的拦截器，并添加过滤项、排除项
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/static/**")//排除样式、脚本、图片等资源文件
                .excludePathPatterns("/admin/login")//排除登录验证
                .excludePathPatterns("/login");//排除登录页面
    }
}
