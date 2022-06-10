package com.example.cafemanagementsystem.domain.enums;

import java.util.Locale;

public enum RoleType {
    ADMIN,
    MANAGER,
    WAITER;

    public static RoleType fromString(String value) {
        String upperValue = value.toUpperCase(Locale.US);

        for (RoleType roleType : values()) {
            if (roleType.name().equals(upperValue)) {
                return roleType;
            }
        }

        return null;
    }
}
