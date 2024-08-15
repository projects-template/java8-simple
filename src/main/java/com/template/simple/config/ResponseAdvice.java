package com.template.simple.config;

import com.template.simple.common.entity.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * 全局统一返回处理类
 */
@Slf4j
@RestControllerAdvice("com.template.simple.controller")
@SuppressWarnings("all")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要进行统一返回处理
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 方法级别注解校验
        if (returnType.hasMethodAnnotation(IgnoreResponseAdvice.class) || returnType.hasParameterAnnotation(
                IgnoreResponseAdvice.class)) {
            return false;
        }
        // 类级别注解校验
        Annotation[] annotations = returnType.getMember().getDeclaringClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(IgnoreResponseAdvice.class)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 进行统一返回处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return ResultData.success("ok", body);
    }

}
