package com.example.cafemanagementsystem.dto.request;

import com.example.cafemanagementsystem.domain.enums.UserRole;

import java.util.Objects;

public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String userName;

    private  String email;
    private String password;

    private UserRole userRoleType;
    private Boolean active;

    public UserRequestDto() {
    }

    public UserRequestDto(String firstName, String lastName,
                          String userName, String email, String password,
                          UserRole userRoleType, Boolean active) {
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

    public UserRole getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(UserRole userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDto that = (UserRequestDto) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(userName, that.userName)
                && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, active);
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoleType=" + userRoleType +
                ", active=" + active +
                '}';
    }
}
