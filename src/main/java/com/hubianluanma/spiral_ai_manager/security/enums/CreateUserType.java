package com.hubianluanma.spiral_ai_manager.security.enums;

/**
 * @author huhailong
 * @version 1.0
 * @description:
 * Enum representing the source or method of user creation.
 * Used to differentiate between user self-registration.
 * admin-created users, system imports, etc.
 * @date 2025/11/7 15:21
 */
public enum CreateUserType {

    /**
     * User registered themselves through the public registration endpoint.
     * Typically requires email verification or activation.
     */
    SELF_REGISTERED("User self-registration, requires activation"),

    /**
     * User was manually created by an administrator through the management console.
     * Usually considered active immediately.
     */
    ADMIN_CREATED("Created manually by an administrator"),

    /**
     * User data was imported automatically (e.g. from another system or migration).
     */
    SYSTEM_IMPORTED("Imported from external system or migration script");

    private final String description;

    CreateUserType(String description) {
        this.description = description;
    }

    /**
     * Returns a human-readable description of the user creation type.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the user requires activation based on the creation type.
     */
    public boolean requiresActivation() {
        return this == SELF_REGISTERED;
    }
}
