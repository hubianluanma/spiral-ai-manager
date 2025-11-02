package com.hubianluanma.spiral_ai_manager.security.api;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 21:25
 */

import com.hubianluanma.spiral_ai_manager.security.dto.LoginRequest;
import com.hubianluanma.spiral_ai_manager.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest body) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.username(), body.password())
        );
        // 如果未抛异常即认证成功
        String token = jwtService.generateToken(body.username());
        return ResponseEntity.ok(Map.of("token", token));
    }

    public ResponseEntity<Map<String, String>> logout() {
        // 这里可以添加一些登出逻辑，比如记录日志等
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}
