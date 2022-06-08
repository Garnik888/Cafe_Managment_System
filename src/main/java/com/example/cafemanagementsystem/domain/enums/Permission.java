package com.example.cafemanagementsystem.domain.enums;


public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),

    MANAGER_READ("manager:read"),

    MANAGER_WRITE("manager:write"),

    WAITER_READ("admin:read"),

    WAITER_WRITE("admin:write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
