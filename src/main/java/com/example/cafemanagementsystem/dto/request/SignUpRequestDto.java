package com.example.cafemanagementsystem.dto.request;

import com.example.cafemanagementsystem.domain.enums.UserRole;
import lombok.Data;

@Data
public class SignUpRequestDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private  String email;

    private UserRole userRoleType;
}
