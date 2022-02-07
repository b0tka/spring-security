package com.botka.security.springsecurity.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public enum UserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
