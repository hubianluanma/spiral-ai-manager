package com.hubianluanma.spiral_ai_manager.controller;

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
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Map<String, Object> result = new HashMap<>();
        result.put("username", userDetails.getUsername());
        result.put("roles", userDetails.getRoles());
        result.put("permissions", userDetails.getPermissions());
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "访问成功：Admin Endpoint";
    }

    // 只有权限为 '用户查看' 可以访问
    @PreAuthorize("hasAuthority('用户查看')")
    @GetMapping("/user/view")
    public String userViewEndpoint() {
        return "访问成功：用户查看 Endpoint";
    }

    // 只有权限为 '用户编辑' 可以访问
    @PreAuthorize("hasAuthority('用户编辑')")
    @PostMapping("/user/edit")
    public String userEditEndpoint() {
        return "访问成功：用户编辑 Endpoint";
    }

    // 所有人都能访问
    @GetMapping("/public")
    public String publicEndpoint() {
        return "访问成功：Public Endpoint";
    }
}
