package com.hubianluanma.spiral_ai_manager.security.api;

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.model.Role;
import com.hubianluanma.spiral_ai_manager.security.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/9 16:23
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public ApiResponse<Void> saveRole(@RequestBody Role role) {
        roleRepository.save(role);
        return ApiResponse.success();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteRole(@RequestBody List<Long> ids) {
        roleRepository.deleteAllById(ids);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<Role>> findAll() {
        return ApiResponse.success(roleRepository.findAll());
    }
}
