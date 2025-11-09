package com.hubianluanma.spiral_ai_manager.utils;

import com.hubianluanma.spiral_ai_manager.exception.ErrorCode;
import com.hubianluanma.spiral_ai_manager.exception.ValidationException;

import java.util.regex.Pattern;

/**
 * @author huhailong
 * @version 1.0
 * @description: 用来校验用户信息的合法性工具类
 * @date 2025/11/7 15:49
 */
public class UserValidator {

    // 用户名：4~20个字符，只能包含字母、数字、下划线
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{4,20}$");

    // 邮箱：基础的 RFC5322 简化校验
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    // 密码：8~32个字符，必须包含大小写字母和数字，可包含特殊符号
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,32}$");

    public static boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidNickname(String nickname) {
        return nickname != null && !nickname.trim().isEmpty();
    }

    public static void validateUser(String username, String email, String password, String nickname) {
        if (!isValidUsername(username)) {
            throw new ValidationException(ErrorCode.INVALID_USERNAME);
        }
        if (!isValidEmail(email)) {
            throw new ValidationException(ErrorCode.INVALID_EMAIL);
        }
        if (!isValidPassword(password)) {
            throw new ValidationException(ErrorCode.INVALID_PASSWORD);
        }
        if (!isValidNickname(nickname)) {
            throw new ValidationException(ErrorCode.INVALID_NICKNAME);
        }
    }
}
