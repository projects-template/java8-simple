package com.template.simple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * webmvc 处理
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 用于解决统一返回处理 String 的转换异常
     *
     * @param converters 转换器
     */
     @Override
     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
         converters.add(0, new MappingJackson2HttpMessageConverter());
     }

    /**
     * 跨域处理
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许请求方法
                .maxAge(168000)    // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);    // 是否发送cookie
    }
}
