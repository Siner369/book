package com.siner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = Thread.currentThread().getContextClassLoader().getResource("") + "upload/";
        registry.addResourceHandler("/bookImg/**").addResourceLocations(path);
    }
}
