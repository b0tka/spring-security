package com.botka.security.springsecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRoles {
    STUDENT(new HashSet<>(Collections.singletonList(
            UserPermissions.STUDENT_READ))),
    ADMIN(new HashSet<>(Arrays.asList(
            UserPermissions.ADMIN_READ,
            UserPermissions.ADMIN_WRITE,
            UserPermissions.STUDENT_READ,
            UserPermissions.STUDENT_WRITE)));

    private final HashSet<UserPermissions> userRoles;

    UserRoles(HashSet<UserPermissions> userRoles) {
        this.userRoles = userRoles;
    }

    public HashSet<UserPermissions> getUserRoles() {
        return userRoles;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getUserRoles().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
