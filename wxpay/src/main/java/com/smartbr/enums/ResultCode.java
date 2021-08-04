package com.smartbr.enums;

import lombok.Getter;

/**
 * @author suny
 * @version 1.0
 * @summary 错误代码
 */


@Getter
public enum ResultCode {
    OK(200, "OK"),
    INVALID_ARGUMENT(400, "客户端传入了无效参数"),
    FAILED_PRECONDITION(400, "请求无法在当前系统状态下执行"),
    UNAUTHENTICATED(401, "由于 Token 丢失、无效或过期，请求未通过身份验证"),
    PERMISSION_DENIED(403, "客户端权限不足或者 API 尚未启用"),
    NOT_FOUND(404, "未找到指定的资源"),
    ALREADY_EXISTS(409, "客户端尝试创建的资源已存在"),
    INTERNAL(500, "内部服务器错误");

    public Integer code;
    public String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
