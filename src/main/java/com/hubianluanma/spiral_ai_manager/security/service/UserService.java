package com.hubianluanma.spiral_ai_manager.security.service;

import com.hubianluanma.spiral_ai_manager.security.dto.UserCreateRequest;
import com.hubianluanma.spiral_ai_manager.security.dto.UserSearchRequest;
import com.hubianluanma.spiral_ai_manager.security.enums.CreateUserType;
import com.hubianluanma.spiral_ai_manager.security.model.User;
import com.hubianluanma.spiral_ai_manager.security.repository.UserSimpleProjection;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/3 12:36
 */
public interface UserService {

    Page<User> getUserList(UserSearchRequest searchRequest);

    List<UserSimpleProjection> simpleList();

    void createUser(UserCreateRequest userBody, CreateUserType createUserType);

    void deleteUser(List<Long> userIdList);
}
