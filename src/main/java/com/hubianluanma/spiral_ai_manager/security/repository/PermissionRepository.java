package com.hubianluanma.spiral_ai_manager.security.repository;

import com.hubianluanma.spiral_ai_manager.security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 14:11
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByCode(String code);
}
