package com.echo.blog.config.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author XLecho
 * Date 2019/6/20 0020
 * Time 15:52
 */
@Getter
@ToString
@AllArgsConstructor
public enum StatusCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    ERROR(500, "服务器内部错误"),
    ERROR_(999, "自定义服务器内部错误编码"),
    FAIL(1000, "自定义异常");

    private int code;
    private String msg;

}
