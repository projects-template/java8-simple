package com.template.simple.common.entity;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * 统一返回
 *
 * @param <T> data 数据类型
 * @author wii
 */
@Data
public class ResultData<T> {

    private int status;
    private String message;
    private T data;
    private long timestamp;
    private boolean success;


    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 结果类型自适应
     *
     * @param code    响应码
     * @param message 消息
     * @param data    结果数据
     * @param <T>     结果类型
     * @return 结果集
     */
    public static <T> ResultData<T> result(int code, String message, T data) {
        if (code != HttpStatus.HTTP_OK) {
            return fail(code, message);
        }
        return success(message, data);
    }

    /**
     * 成功结果集
     *
     * @param message 消息
     * @param data    结果数据
     * @param <T>     结果类型
     * @return 结果集
     */
    public static <T> ResultData<T> success(String message, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(HttpStatus.HTTP_OK);
        resultData.setMessage(message);
        resultData.setData(data);
        resultData.setSuccess(true);
        return resultData;
    }

    /**
     * 失败结果集
     *
     * @param code    响应码
     * @param message 消息
     * @param <T>     结果类型
     * @return 结果集
     */
    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        resultData.setSuccess(false);
        return resultData;
    }

    /**
     * @param success 成功与否
     * @param code    错误吗
     * @param message 结果消息
     * @param date    数据
     * @param <T>     模版类型
     * @return 结果内容
     */
    public static <T> ResultData<T> builder(Boolean success, int code, String message, T date) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setSuccess(success);
        resultData.setMessage(message);
        resultData.setData(date);

        return resultData;
    }
}
