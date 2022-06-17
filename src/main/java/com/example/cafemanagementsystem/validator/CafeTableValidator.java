package com.example.cafemanagementsystem.validator;

import com.example.cafemanagementsystem.domain.entity.User;
import com.example.cafemanagementsystem.domain.enums.RoleType;

public class CafeTableValidator {

    public static boolean isUserWaiter(User user) {

        return (!user.getRoleType().equals(RoleType.WAITER));
    }

}
