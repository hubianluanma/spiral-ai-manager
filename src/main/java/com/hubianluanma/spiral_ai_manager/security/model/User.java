package com.hubianluanma.spiral_ai_manager.security.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author huhailong
 * @version 1.0
 * @description: users
 * @date 2025/11/2 09:56
 */
@Data
@Entity
@Table(name = "users")
@SoftDelete
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 100)
    private String nickname;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(nullable = false)
    private boolean enabled = true;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // 多对多： 用户-角色关联
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
