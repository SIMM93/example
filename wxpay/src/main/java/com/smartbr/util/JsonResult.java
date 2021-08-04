package com.smartbr.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.smartbr.enums.ResultCode;
import lombok.Data;

/**
 * @author suny
 * @version V1.0
 */

@Data
public class JsonResult<T> {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private T data;

    @JsonIgnore
    private String ok;    // 不使用

    public JsonResult() {
    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResult<T> ok() {
        return JsonResult.ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(ResultCode.OK.code, ResultCode.OK.message, data);
    }

    public static <T> JsonResult<T> error(ResultCode resultCode) {
        return new JsonResult<>(resultCode.code, resultCode.message, null);
    }
}
