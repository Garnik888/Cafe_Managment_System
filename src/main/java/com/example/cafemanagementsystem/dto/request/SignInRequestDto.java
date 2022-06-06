package com.example.cafemanagementsystem.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Objects;


public class SignInRequestDto {
    @Email
    private String email;
    private String password;

    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignInRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInRequestDto that = (SignInRequestDto) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "SignInRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
