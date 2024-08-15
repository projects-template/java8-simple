package com.template.simple.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常基类
 * <p>
 * 其他自定义类继承此基类
 *
 * @author wii
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
