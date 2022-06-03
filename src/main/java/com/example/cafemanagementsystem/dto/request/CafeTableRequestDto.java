package com.example.cafemanagementsystem.dto.request;

import com.example.cafemanagementsystem.domain.entity.User;

import javax.persistence.*;
import java.util.Objects;

public class CafeTableRequestDto {

    private String tableName;
    private Boolean reserve;
    private User user;

    public CafeTableRequestDto() {
    }

    public CafeTableRequestDto(String tableName, Boolean reserve, User user) {
        this.tableName = tableName;
        this.reserve = reserve;
        this.user = user;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableRequestDto that = (CafeTableRequestDto) o;
        return Objects.equals(tableName, that.tableName) && Objects.equals(reserve, that.reserve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, reserve);
    }

    @Override
    public String toString() {
        return "CafeTableRequestDto{" +
                "tableName='" + tableName + '\'' +
                ", reserve=" + reserve +
                '}';
    }
}
