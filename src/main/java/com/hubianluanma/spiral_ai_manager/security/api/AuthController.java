package com.hubianluanma.spiral_ai_manager.security.api;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 21:25
 */

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.dto.LoginRequest;
import com.hubianluanma.spiral_ai_manager.security.dto.UserCreateRequest;
import com.hubianluanma.spiral_ai_manager.security.enums.CreateUserType;
import com.hubianluanma.spiral_ai_manager.security.jwt.JwtService;
import com.hubianluanma.spiral_ai_manager.security.service.UserService;
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
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
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

    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody UserCreateRequest userBody) {
        userService.createUser(userBody, CreateUserType.SELF_REGISTERED);
        return ApiResponse.success();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        // 这里可以添加一些登出逻辑，比如记录日志等
        return ApiResponse.success(null, "Logged out successfully");
    }
}
