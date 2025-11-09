package com.hubianluanma.spiral_ai_manager.security.api;

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.dto.UserSearchRequest;
import com.hubianluanma.spiral_ai_manager.security.model.User;
import com.hubianluanma.spiral_ai_manager.security.repository.UserSimpleProjection;
import com.hubianluanma.spiral_ai_manager.security.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/3 12:34
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allList")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Page<User>> getAllUsers(UserSearchRequest request) {
        return ApiResponse.success(userService.getUserList(request));
    }

    @GetMapping("/simpleList")
    public ApiResponse<List<UserSimpleProjection>> simpleList() {
        return ApiResponse.success(userService.simpleList());
    }

    @PostMapping("/deleteByIds")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteUserById(@RequestBody List<Long> ids) {
        userService.deleteUser(ids);
        return ApiResponse.success();
    }
}
