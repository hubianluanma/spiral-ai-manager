package com.hubianluanma.spiral_ai_manager.security.service.impl;

import com.hubianluanma.spiral_ai_manager.exception.ErrorCode;
import com.hubianluanma.spiral_ai_manager.exception.ValidationException;
import com.hubianluanma.spiral_ai_manager.security.dto.UserCreateRequest;
import com.hubianluanma.spiral_ai_manager.security.dto.UserSearchRequest;
import com.hubianluanma.spiral_ai_manager.security.enums.CreateUserType;
import com.hubianluanma.spiral_ai_manager.security.model.Role;
import com.hubianluanma.spiral_ai_manager.security.model.User;
import com.hubianluanma.spiral_ai_manager.security.repository.RoleRepository;
import com.hubianluanma.spiral_ai_manager.security.repository.UserRepository;
import com.hubianluanma.spiral_ai_manager.security.repository.UserSimpleProjection;
import com.hubianluanma.spiral_ai_manager.security.service.UserService;
import com.hubianluanma.spiral_ai_manager.utils.UserValidator;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.hubianluanma.spiral_ai_manager.common.ConstraintNames.USERS_USERNAME_UNIQUE;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/3 12:36
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<User> getUserList(UserSearchRequest searchRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(searchRequest.order()), searchRequest.sort());
        Pageable pageable = PageRequest.of(searchRequest.page(), searchRequest.size(), sort);
        Specification<User> spec = (root, query, cb) -> {
            Predicate p = cb.conjunction();
            if (searchRequest.username() != null && !searchRequest.username().isEmpty()) {
                p = cb.and(p, cb.like(root.get("username"), "%" + searchRequest.username() + "%"));
            }
            if (searchRequest.nickname() != null && !searchRequest.nickname().isEmpty()) {
                p = cb.and(p, cb.like(root.get("nickname"), "%" + searchRequest.nickname() + "%"));
            }
            if (searchRequest.email() != null && !searchRequest.email().isEmpty()) {
                p = cb.and(p, cb.equal(root.get("email"),  searchRequest.email()));
            }
            return p;
        };
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public List<UserSimpleProjection> simpleList() {
        return userRepository.findAllForSimple();
    }

    @Override
    public void createUser(UserCreateRequest userBody, CreateUserType createUserType) {
        switch (createUserType) {
            case SELF_REGISTERED -> createUserBySelf(userBody);
            case ADMIN_CREATED -> createUserByAdmin(userBody);
        }
    }

    @Override
    public void updateUser(UserCreateRequest userBody) {
        if (userBody.id() == null) {
            throw new ValidationException(ErrorCode.CONSTRAINT_USER_ID);
        }
        UserValidator.validateUser(userBody.username(), userBody.email(), userBody.nickname());
        User updateUser = userRepository.getReferenceById(userBody.id());
        updateUser.setUsername(userBody.username());
        updateUser.setNickname(userBody.nickname());
        updateUser.setEmail(userBody.email());  // TODO: 暂时实现修改，后期需要考虑更改后激活验证修改后的邮箱
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(List<Long> userIdList) {
        userRepository.deleteAllById(userIdList);
    }

    /**
     * User self registered
     */
    private void createUserBySelf(UserCreateRequest userBody) {
        // 插入DB
        insertUser(userBody);

        // TODO: 发送激活邮件
    }

    private void createUserByAdmin(UserCreateRequest userBody) {
        insertUser(userBody);

        // TODO: 发送通知邮件
    }

    private void insertUser(UserCreateRequest userBody) {
        try {
            // 首先进行用户数据的合法性校验
            UserValidator.validateUser(userBody.username(), userBody.email(), userBody.password(), userBody.nickname());
            // 校验邮箱唯一性校验（DB层面有激活的二次约束）
            boolean exists = userRepository.existsByEmailAndEmailVerifiedTrue(userBody.email());
            if (exists) {
                throw new ValidationException(ErrorCode.CONSTRAINT_EMAIL);
            }
            // 校验通过后插入数据库
            User user = new User();
            String encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userBody.password());
            user.setPassword(encodePassword);
            user.setNickname(userBody.nickname());
            user.setEmail(userBody.email());
            user.setUsername(userBody.username());
            // 查询普通用户角色
            Optional<Role> roleOptional = roleRepository.findByName("USER");
            roleOptional.ifPresent(role -> user.setRoles(Set.of(role)));
            userRepository.save(user);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {
                if (USERS_USERNAME_UNIQUE.equals(cve.getConstraintName())) {
                    throw new ValidationException(ErrorCode.CONSTRAINT_USERNAME);
                }
            }
            throw e;
        }
    }

}
