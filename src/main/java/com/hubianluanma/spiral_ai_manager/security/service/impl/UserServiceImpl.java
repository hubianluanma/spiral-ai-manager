package com.hubianluanma.spiral_ai_manager.security.service.impl;

import com.hubianluanma.spiral_ai_manager.security.model.User;
import com.hubianluanma.spiral_ai_manager.security.repository.UserRepository;
import com.hubianluanma.spiral_ai_manager.security.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/3 12:36
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll(Sort.by("createdAt"));
    }
}
