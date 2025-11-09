package com.hubianluanma.spiral_ai_manager.exception;

import lombok.Getter;

/**
 * @author huhailong
 * @version 1.0
 * @description: 信息校验失败异常
 * @date 2025/11/7 15:53
 */
@Getter
public class ValidationException extends RuntimeException{

    private final ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

}
