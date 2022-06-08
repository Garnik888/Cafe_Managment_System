package com.example.cafemanagementsystem.dto.responce;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(chain = true)
public class SignUpResponseDto {

    private String firstName;

    private String lastName;

    private String username;

    public SignUpResponseDto() {
    }

    public SignUpResponseDto(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpResponseDto that = (SignUpResponseDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username);
    }

    @Override
    public String toString() {
        return "SignUpResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
