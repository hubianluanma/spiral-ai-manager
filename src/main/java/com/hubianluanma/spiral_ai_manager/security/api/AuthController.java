package com.hubianluanma.spiral_ai_manager.security.api;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 21:25
 */

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.dto.LoginRequest;
import com.hubianluanma.spiral_ai_manager.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest body) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.username(), body.password())
        );
        // 如果未抛异常即认证成功
        String token = jwtService.generateToken(body.username());
        return ApiResponse.success(Map.of("token", token), "Logged in successfully");
    }

    public ApiResponse<Void> logout() {
        // 这里可以添加一些登出逻辑，比如记录日志等
        return ApiResponse.success(null, "Logged out successfully");
    }
}
