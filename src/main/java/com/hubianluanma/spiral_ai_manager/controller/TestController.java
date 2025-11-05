package com.hubianluanma.spiral_ai_manager.controller;

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.config.CustomUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/1 23:36
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/me")
    public ApiResponse<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Map<String, Object> result = new HashMap<>();
        result.put("username", userDetails.getUsername());
        result.put("roles", userDetails.getRoles());
        result.put("permissions", userDetails.getPermissions());
        return ApiResponse.success(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ApiResponse<String> adminEndpoint() {
        return ApiResponse.success("访问成功：Admin Endpoint");
    }

    // 只有权限为 '用户查看' 可以访问
    @PreAuthorize("hasAuthority('用户查看')")
    @GetMapping("/user/view")
    public ApiResponse<String> userViewEndpoint() {
        return ApiResponse.success("访问成功：用户查看 Endpoint");
    }

    // 只有权限为 '用户编辑' 可以访问
    @PreAuthorize("hasAuthority('用户编辑')")
    @PostMapping("/user/edit")
    public ApiResponse<String> userEditEndpoint() {
        return ApiResponse.success("访问成功：用户编辑 Endpoint");
    }

    // 所有人都能访问
    @GetMapping("/public")
    public ApiResponse<String> publicEndpoint() {
        return ApiResponse.success("访问成功：Public Endpoint");
    }
}
