package com.hubianluanma.spiral_ai_manager.security.api;

import com.hubianluanma.spiral_ai_manager.common.ApiResponse;
import com.hubianluanma.spiral_ai_manager.security.model.Permission;
import com.hubianluanma.spiral_ai_manager.security.repository.PermissionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/9 16:33
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    private final PermissionRepository permissionRepository;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostMapping
    public ApiResponse<Void> savePermission(@RequestBody Permission permission) {
        permissionRepository.save(permission);
        return ApiResponse.success();
    }

    @DeleteMapping
    public ApiResponse<Void> deletePermission(@RequestBody List<Long> ids) {
        permissionRepository.deleteAllById(ids);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<Permission>> findAll() {
        return ApiResponse.success(permissionRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
    }

}
