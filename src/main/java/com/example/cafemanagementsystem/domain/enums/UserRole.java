package com.example.cafemanagementsystem.domain.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Set.of(Permission.ADMIN_READ,Permission.ADMIN_WRITE)),
    MANAGER(Set.of(Permission.MANAGER_READ,Permission.MANAGER_WRITE)),
    WAITER(Set.of(Permission.WAITER_READ,Permission.WAITER_WRITE));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {

        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
