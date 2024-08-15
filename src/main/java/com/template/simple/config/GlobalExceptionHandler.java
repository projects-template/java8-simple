package com.template.simple.config;

import com.template.simple.common.entity.ResultData;
import com.template.simple.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 方法参数校验
     *
     * @param ex 方法参数
     * @return 结果内容
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultData<List<String>> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        Collections.sort(list);

        return ResultData.builder(false, 400, "参数校验异常", list);
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return 统一错误结果返回
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResultData<?> bizExceptionHandler(BaseException e) {
        log.error("BaseException异常，请检查", e);
        return ResultData.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e 异常
     * @return 统一错误结果返回
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData<?> exceptionHandler(Exception e) {
        log.error("运行时异常，请检查", e);
        return ResultData.fail(500, e.getMessage());
    }

}
