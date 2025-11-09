package com.hubianluanma.spiral_ai_manager.security.repository;

import com.hubianluanma.spiral_ai_manager.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 14:10
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    boolean existsByEmailAndEmailVerifiedTrue(String email);

    @Query("SELECT u.id AS id, u.username AS username, u.nickname AS nickname FROM User u")
    List<UserSimpleProjection> findAllForSimple();
}
