package com.hubianluanma.spiral_ai_manager.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 22:21
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(405)
                .body(Map.of(
                        "error", "Method Not Allowed",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOtherExceptions(Exception ex) throws Exception {
        // 如果是 Spring Security 的认证/权限异常，不处理， 让 Spring Security 自己处理
        if (ex instanceof org.springframework.security.core.AuthenticationException ||
            ex instanceof org.springframework.security.access.AccessDeniedException) {
            throw ex;
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Internal Server Error",
                        "message", ex.getMessage()
                ));
    }
}
