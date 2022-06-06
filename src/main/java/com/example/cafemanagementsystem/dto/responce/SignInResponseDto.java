package com.example.cafemanagementsystem.dto.responce;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(chain = true)
public class SignInResponseDto {

    private String token;
    private String type;

    public SignInResponseDto() {
    }

    public SignInResponseDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInResponseDto that = (SignInResponseDto) o;
        return Objects.equals(token, that.token) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type);
    }

    @Override
    public String toString() {
        return "SignInResponseDto{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
