package com.example.cafemanagementsystem.dto.request;



import com.example.cafemanagementsystem.domain.enums.RoleType;

import java.util.Objects;

public class SignUpRequestDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;


    private RoleType userRoleType;

    public SignUpRequestDto() {
    }

    public SignUpRequestDto(String firstName,
                            String lastName,
                            String username,
                            String password,
                            RoleType userRoleType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userRoleType = userRoleType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(RoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpRequestDto that = (SignUpRequestDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && userRoleType == that.userRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, userRoleType);
    }
}
