package com.hubianluanma.spiral_ai_manager.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    // 状态码
    private int code;

    // 消息
    private String message;

    // 数据
    private T data;

    // 无参构造函数
    public ApiResponse() {}

    // 全参构造函数
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter 和 Setter 方法

    // 静态方法用于快速构建成功的响应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data);
    }

    // 静态方法用于快速构建带有自定义消息的成功响应
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(200, message, data);
    }

    // 静态方法用于快速构建默认的成功响应
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "操作成功", null);
    }

    // 静态方法用于快速构建失败的响应
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // 静态方法用于快速构建带有自定义消息和数据的失败响应
    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    // 静态方法用于快速构建默认的失败响应
    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(500, "操作失败", null);
    }
}
