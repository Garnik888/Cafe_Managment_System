package com.example.cafemanagementsystem.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    MANAGER("manager"),
    WAITER("waiter");

    private final String permission;

    UserRole(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
