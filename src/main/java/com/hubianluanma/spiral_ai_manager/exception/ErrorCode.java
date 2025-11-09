package com.hubianluanma.spiral_ai_manager.exception;

/**
 * @author huhailong
 * @version 1.0
 * @description: error code and message
 * @date 2025/11/7 16:01
 */
public enum ErrorCode {
    // 成功
    SUCCESS(0, "OK"),

    // 用户/参数相关
    INVALID_USERNAME(1001, "用户名格式不正确"),
    INVALID_EMAIL(1002, "邮箱格式不正确"),
    INVALID_PASSWORD(1003, "密码不符合安全要求"),
    INVALID_NICKNAME(1004, "昵称格式不合法"),
    USER_ALREADY_EXISTS(1005, "用户已存在"),
    EMAIL_NOT_VERIFIED(1006, "邮箱未激活"),
    CONSTRAINT_USERNAME(1007, "用户名已存在"),
    CONSTRAINT_EMAIL(1008, "该邮箱已注册"),

    // 认证/权限
    UNAUTHORIZED(2001, "未认证，请先登录"),
    FORBIDDEN(2002, "无权限访问"),

    // 资源相关
    RESOURCE_NOT_FOUND(3001, "资源不存在"),
    RESOURCE_CONFLICT(3002, "资源冲突"),

    // 系统/第三方异常
    INTERNAL_ERROR(5001, "系统内部错误"),
    EXTERNAL_SERVICE_ERROR(5002, "第三方服务调用失败");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
