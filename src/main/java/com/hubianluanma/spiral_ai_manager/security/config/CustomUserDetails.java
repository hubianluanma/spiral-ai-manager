package com.hubianluanma.spiral_ai_manager.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 15:36
 */
public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;
    private final Set<String> roles;
    private final Set<String> permissions;

    public CustomUserDetails (String username, String password, boolean enabled, Set<String> roles, Set<String> permissions) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auths = roles.stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)
                .collect(java.util.stream.Collectors.toSet());
        auths.addAll(permissions.stream()
                .map(perm -> (GrantedAuthority) () -> perm)
                .collect(java.util.stream.Collectors.toSet()));
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Getters for roles and permissions
    public Set<String> getRoles() {
        return roles;
    }
    public Set<String> getPermissions() {
        return permissions;
    }
}
