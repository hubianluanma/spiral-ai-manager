package com.hubianluanma.spiral_ai_manager.security.dto;

/**
 * user register request body
 * @param username Used for user login.
 * @param password Encoded raw password.
 * @param email Email address used for sending activation mail.
 * @param nickname User-defined display name.
 */
public record UserCreateRequest(Long id, String username, String password, String email, String nickname) {}
