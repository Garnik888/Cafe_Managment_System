package com.example.cafemanagementsystem.dto.request;

import com.example.cafemanagementsystem.domain.enums.RoleType;


import java.util.Objects;

public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String userName;

    private  String email;
    private String password;

    private RoleType userRoleType;
    private Boolean active;

    public UserRequestDto() {
    }

    public UserRequestDto(String firstName, String lastName,
                          String userName, String email, String password,
                          RoleType userRoleType, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRoleType = userRoleType;
        this.active = active;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RoleType getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(RoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
