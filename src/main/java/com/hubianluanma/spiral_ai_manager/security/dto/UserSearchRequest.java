package com.hubianluanma.spiral_ai_manager.security.dto;

import org.springframework.data.domain.Pageable;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/8 22:07
 */
public record UserSearchRequest (String username, String nickname, String email, Integer page, Integer size, String sort, String order) {
    public UserSearchRequest {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sort == null) sort = "createdAt";
        if (order == null) order = "desc";
    }
}
